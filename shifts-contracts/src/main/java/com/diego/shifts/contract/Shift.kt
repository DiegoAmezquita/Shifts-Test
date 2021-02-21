package com.diego.shifts.contract

import com.diego.core.data.Location

data class Shift(
  val id: Int,
  val startDate: String,
  val endDate: String,
  val startLocation: Location,
  val endLocation: Location,
  val image: String
)
