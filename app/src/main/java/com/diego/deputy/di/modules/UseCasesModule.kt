package com.diego.deputy.di.modules

import android.content.Context
import com.diego.core.qualifiers.GetFormattedDeviceTime
import com.diego.core.qualifiers.GetLastKnownLocation
import com.diego.deputy.usecases.GetDeviceLocationUseCase
import com.diego.deputy.usecases.GetFormattedDeviceTimeUseCase
import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.EndShift
import com.diego.shifts.contract.GetShifts
import com.diego.core.data.Location
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.StartShift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.data.ShiftUiModel
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.other.MapDateToHumanReadableFormat
import com.diego.shifts.domain.other.MapShiftResponseToInternal
import com.diego.shifts.domain.usecases.EndShiftsUseCase
import com.diego.shifts.domain.usecases.GetShiftsUseCase
import com.diego.shifts.domain.usecases.MapShiftResponseToInternalUseCase
import com.diego.shifts.domain.usecases.MapStringDateToHumanReadableDateUseCase
import com.diego.shifts.domain.usecases.StartShiftsUseCase
import com.diego.shifts.others.MapShiftToUiModel
import com.diego.shifts.usecases.MapShiftToUIModelUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCasesModule {

  @Binds
  @GetShifts
  abstract fun bindsGetShiftsUseCase(getShiftsUseCase: GetShiftsUseCase): AsyncUseCase<Unit, List<Shift>>

  @Binds
  @StartShift
  abstract fun bindsStartShiftsUseCase(startShiftsUseCase: StartShiftsUseCase): AsyncUseCase<Unit, String>

  @Binds
  @EndShift
  abstract fun bindsEndShiftsUseCase(endShiftsUseCase: EndShiftsUseCase): AsyncUseCase<Unit, String>

  @Binds
  @MapShiftResponseToInternal
  abstract fun bindsMapShiftResponseToInternalUseCase(
    mapShiftsResponseToInternal: MapShiftResponseToInternalUseCase
  ): UseCase<ShiftResponse, Shift>

  @Binds
  @MapShiftToUiModel
  abstract fun bindsMapShiftToUIModelUseCase(
    mapShiftToUIModelUseCase: MapShiftToUIModelUseCase
  ): UseCase<Shift, ShiftUiModel>

  @Binds
  @GetLastKnownLocation
  abstract fun bindsGetDeviceLocationUseCase(
    getDeviceLocationUseCase: GetDeviceLocationUseCase
  ): AsyncUseCase<Unit, Location>
}

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesProvideModule {
  @Provides
  @MapDateToHumanReadableFormat
  fun providesMapStringDateToHumanReadableDateUseCase(): UseCase<String, String> {
    val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

    return MapStringDateToHumanReadableDateUseCase(serverFormat, outputFormat)
  }

  @Provides
  fun providesFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(context)
  }

  @Provides
  @GetFormattedDeviceTime
  fun providesGetFormattedDeviceTimeUseCase(): UseCase<Unit, String> {
    val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    return GetFormattedDeviceTimeUseCase(Calendar.getInstance(), serverFormat)
  }
}

