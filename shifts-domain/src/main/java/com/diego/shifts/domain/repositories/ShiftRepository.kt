package com.diego.shifts.domain.repositories

import com.diego.shifts.contract.Location
import com.diego.shifts.domain.data.ShiftRequest
import com.diego.shifts.domain.services.ShiftService
import javax.inject.Inject

class ShiftRepository @Inject constructor(private val service: ShiftService) {

  suspend fun getShifts() = service.getShifts()

  suspend fun startShift(time: String, location: Location): String {
    val startShiftRequest = ShiftRequest(time, location.latitude, location.longitude)
    return service.startShift(startShiftRequest)
  }

  suspend fun endShift(time: String, location: Location): String {
    val startShiftRequest = ShiftRequest(time, location.latitude, location.longitude)
    return service.endShift(startShiftRequest)
  }
}