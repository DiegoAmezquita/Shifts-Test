package com.diego.shifts.domain.usecases

import com.diego.shifts.contract.UseCase
import java.text.DateFormat
import java.text.ParseException
import javax.inject.Inject

class MapStringDateToHumanReadableDateUseCase @Inject constructor(
  private val inputDateFormat: DateFormat,
  private val outputDateFormat: DateFormat
) : UseCase<String, String> {

  override fun execute(input: String): String {
    return try {
      val date = inputDateFormat.parse(input)
      outputDateFormat.format(date)
    } catch (e: ParseException) {
      ""
    }
  }
}

