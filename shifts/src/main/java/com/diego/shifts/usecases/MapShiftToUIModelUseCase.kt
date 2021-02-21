package com.diego.shifts.usecases

import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.data.ShiftUiModel
import javax.inject.Inject

class MapShiftToUIModelUseCase @Inject constructor() : UseCase<Shift, ShiftUiModel> {

  override fun execute(input: Shift): ShiftUiModel {
    return ShiftUiModel(
      id = input.id.toString(),
      startDate = input.startDate,
      endDate = input.endDate,
      latitude = input.startLocation.latitude,
      longitude = input.startLocation.longitude,
      image = input.image
    )
  }
}