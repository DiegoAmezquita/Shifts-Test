package com.diego.shifts.domain.usecases

import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.other.MapShiftResponseToInternal
import com.diego.shifts.domain.repositories.ShiftRepository
import javax.inject.Inject

class GetShiftsUseCase @Inject constructor(
  private val repository: ShiftRepository,
  @MapShiftResponseToInternal private val mapShiftResponseToInternalUseCase: UseCase<ShiftResponse, Shift>
) : AsyncUseCase<Unit, List<@JvmSuppressWildcards Shift>> {

  override suspend fun execute(input: Unit): List<Shift> {
    return repository.getShifts().map {
      mapShiftResponseToInternalUseCase.execute(it)
    }
  }
}