package com.diego.deputy.di.modules

import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.EndShift
import com.diego.shifts.contract.GetShifts
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
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
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

}


@Module
@InstallIn(SingletonComponent::class)
class UseCasesProvideModule {
  @Provides
  @MapDateToHumanReadableFormat
  fun providesMapStringDateToHumanReadableDateUseCase(): UseCase<String, String> {
    val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

    return MapStringDateToHumanReadableDateUseCase(serverFormat, outputFormat)
  }
}

