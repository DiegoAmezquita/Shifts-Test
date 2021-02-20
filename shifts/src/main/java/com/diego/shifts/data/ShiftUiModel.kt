package com.diego.shifts.data

data class ShiftUiModel(
  val id: String,
  val start: String,
  val end: String,
  val latitude: String,
  val longitude: String,
  val image: String
)