package com.diego.shifts.domain.repositories

import com.diego.core.data.Location
import com.diego.shifts.domain.data.ShiftResponse

interface ShiftRepository {

  suspend fun getShifts(): List<ShiftResponse>

  suspend fun startShift(time: String, location: Location): String

  suspend fun endShift(time: String, location: Location): String
}