package com.diego.shifts.domain.repositories

import com.diego.shifts.domain.services.ShiftService

class ShiftRepository(private val service: ShiftService) {

  suspend fun getShifts() = service.getShifts()
}