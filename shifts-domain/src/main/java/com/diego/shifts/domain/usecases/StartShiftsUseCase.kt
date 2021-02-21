package com.diego.shifts.domain.usecases

import com.diego.core.qualifiers.GetFormattedDeviceTime
import com.diego.core.qualifiers.GetLastKnownLocation
import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.Location
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.repositories.ShiftRepository
import javax.inject.Inject

class StartShiftsUseCase @Inject constructor(
  private val repository: ShiftRepository,
  @GetLastKnownLocation private val getLocationUseCase: AsyncUseCase<Unit, Location>,
  @GetFormattedDeviceTime private val getFormattedDeviceTime: UseCase<Unit, String>
) : AsyncUseCase<Unit, String> {

  override suspend fun execute(input: Unit): String {
    return repository.startShift(
      getFormattedDeviceTime.execute(Unit),
      getLocationUseCase.execute(Unit)
    )
  }
}