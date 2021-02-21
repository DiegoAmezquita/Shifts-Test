package com.diego.shifts.contract

data class Shift(
  val id: Int,
  val startDate: String,
  val endDate: String,
  val startLocation: Location,
  val endLocation: Location,
  val image: String
)

data class Location(
  val latitude: String,
  val longitude: String
)