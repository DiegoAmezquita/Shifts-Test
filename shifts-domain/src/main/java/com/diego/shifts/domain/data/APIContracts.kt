package com.diego.shifts.domain.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShiftResponse(
  val id: Int,
  val start: String,
  val end: String,
  val startLatitude: String,
  val startLongitude: String,
  val endLatitude: String,
  val endLongitude: String,
  val image: String
)

@JsonClass(generateAdapter = true)
data class ShiftRequest(
  val time: String,
  val latitude: String,
  val longitude: String
)