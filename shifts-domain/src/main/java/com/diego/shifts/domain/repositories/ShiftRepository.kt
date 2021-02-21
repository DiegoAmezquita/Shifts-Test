package com.diego.shifts.domain.repositories

import com.diego.shifts.domain.services.ShiftService
import javax.inject.Inject

class ShiftRepository @Inject constructor(private val service: ShiftService) {
  suspend fun getShifts() = service.getShifts()
}