package com.diego.shifts.data

data class ShiftsUIModel(
  val shifts : List<ShiftUiModel> = emptyList(),
  val loading : Boolean = false
)