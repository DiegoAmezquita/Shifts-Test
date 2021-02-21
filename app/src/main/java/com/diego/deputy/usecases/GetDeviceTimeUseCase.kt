package com.diego.deputy.usecases

import com.diego.shifts.contract.UseCase
import java.text.DateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetFormattedDeviceTimeUseCase @Inject constructor(
  private val calendar: Calendar,
  private val outputFormat: DateFormat
) : UseCase<Unit, String> {

  override fun execute(input: Unit): String {
    return outputFormat.format(calendar.time)
  }
}