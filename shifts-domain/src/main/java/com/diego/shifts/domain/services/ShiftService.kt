package com.diego.shifts.domain.services

import com.diego.shifts.domain.data.ShiftResponse
import retrofit2.http.GET

interface ShiftService {

  @GET("shifts")
  suspend fun getShifts(): List<ShiftResponse>
}

