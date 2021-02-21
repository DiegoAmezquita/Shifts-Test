package com.diego.deputy.di.modules

import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.GetShifts
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.other.MapShiftResponseToInternal
import com.diego.shifts.domain.usecases.GetShiftsUseCase
import com.diego.shifts.domain.usecases.MapShiftResponseToInternalUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCasesModule {

  @Binds
  @GetShifts
  abstract fun bindsGetShiftsUseCase(getShiftsUseCase: GetShiftsUseCase): AsyncUseCase<Unit, List<Shift>>

  @Binds
  @MapShiftResponseToInternal
  abstract fun bindsMapShiftResponseToInternalUseCase(
    mapShiftsResponseToInternal: MapShiftResponseToInternalUseCase
  ): UseCase<ShiftResponse, Shift>
}