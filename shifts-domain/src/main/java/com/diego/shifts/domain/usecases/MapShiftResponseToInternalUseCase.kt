package com.diego.shifts.domain.usecases

import com.diego.shifts.contract.Location
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import javax.inject.Inject

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