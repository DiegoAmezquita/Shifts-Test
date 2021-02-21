package com.diego.shifts.domain.services

import com.diego.shifts.domain.data.ShiftRequest
import com.diego.shifts.domain.data.ShiftResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShiftService {

  @GET("shifts")
  suspend fun getShifts(): List<ShiftResponse>

  @POST("shift/start")
  suspend fun startShift(@Body request: ShiftRequest): String

  @POST("shift/end")
  suspend fun endShift(@Body request: ShiftRequest): String
}

