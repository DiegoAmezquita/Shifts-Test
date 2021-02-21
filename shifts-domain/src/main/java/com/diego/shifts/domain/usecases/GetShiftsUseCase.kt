package com.diego.shifts.domain.usecases

import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.Location
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.repositories.ShiftRepository
import javax.inject.Inject

class GetShiftsUseCase @Inject constructor(
  private val repository: ShiftRepository,
  private val mapShiftResponseToInternalUseCase: UseCase<ShiftResponse, Shift>
) : AsyncUseCase<Unit, List<Shift>> {

  override suspend fun execute(input: Unit): List<Shift> {
    return repository.getShifts().map {
      mapShiftResponseToInternalUseCase.execute(it)
    }
  }
}

class MapShiftResponseToInternalUseCase @Inject constructor() : UseCase<ShiftResponse, Shift> {

  override fun execute(input: ShiftResponse): Shift {
    return Shift(
      id = input.id,
      startDate = input.start,
      endDate = input.end,
      startLocation = Location(input.startLatitude, input.startLongitude),
      endLocation = Location(input.endLatitude, input.endLongitude),
      image = input.image
    )
  }
}