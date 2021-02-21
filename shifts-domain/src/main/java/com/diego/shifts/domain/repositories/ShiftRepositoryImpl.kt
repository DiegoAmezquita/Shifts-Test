package com.diego.shifts.domain.repositories

import com.diego.core.data.Location
import com.diego.shifts.domain.data.ShiftRequest
import com.diego.shifts.domain.services.ShiftService
import javax.inject.Inject

class ShiftRepositoryImpl @Inject constructor(private val service: ShiftService) : ShiftRepository {

  override suspend fun getShifts() = service.getShifts()

  override suspend fun startShift(time: String, location: Location): String {
    val startShiftRequest = ShiftRequest(time, location.latitude, location.longitude)
    return service.startShift(startShiftRequest)
  }

  override suspend fun endShift(time: String, location: Location): String {
    val startShiftRequest = ShiftRequest(time, location.latitude, location.longitude)
    return service.endShift(startShiftRequest)
  }
}