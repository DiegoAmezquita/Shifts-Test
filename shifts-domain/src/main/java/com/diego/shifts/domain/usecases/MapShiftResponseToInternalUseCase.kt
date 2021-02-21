package com.diego.shifts.domain.usecases

import com.diego.core.data.Location
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.other.MapDateToHumanReadableFormat
import javax.inject.Inject

class MapShiftResponseToInternalUseCase @Inject constructor(
  @MapDateToHumanReadableFormat private val mapDateUseCase: UseCase<String, String>
) : UseCase<ShiftResponse, Shift> {

  override fun execute(input: ShiftResponse): Shift {
    return Shift(
      id = input.id,
      startDate = mapDateUseCase.execute(input.start),
      endDate = mapDateUseCase.execute(input.end),
      startLocation = Location(input.startLatitude, input.startLongitude),
      endLocation = Location(input.endLatitude, input.endLongitude),
      image = input.image
    )
  }
}