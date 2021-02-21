package com.diego.shifts.domain.usecases

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.text.DateFormat
import java.text.ParseException
import java.util.Date
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class MapStringDateToHumanReadableDateUseCaseTest {

  @MockK
  private lateinit var inputDateFormat: DateFormat

  @MockK
  private lateinit var outputDateFormat: DateFormat

  private lateinit var useCase: MapStringDateToHumanReadableDateUseCase

  @BeforeEach
  fun setup() {
    useCase = MapStringDateToHumanReadableDateUseCase(inputDateFormat, outputDateFormat)
  }

  @Test
  fun `map dates correctly`() {
    val input = "server date"
    val inputDate = mockk<Date>()
    val output = "local date formatted"
    every { inputDateFormat.parse(input) } returns inputDate
    every { outputDateFormat.format(inputDate) } returns output

    val result = useCase.execute(input)
    assertEquals(output, result)
  }

  @Test
  fun `return empty string if mapping fails with ParseException`() {
    every { inputDateFormat.parse(any()) } throws mockk<ParseException>()
    every { outputDateFormat.format(any()) } throws mockk<ParseException>()

    val result = useCase.execute("date")
    assertEquals("", result)
  }

  @Test
  fun `return empty string if mapping fails with other type of exception`() {
    every { inputDateFormat.parse(any()) } throws Exception()
    every { outputDateFormat.format(any()) } throws Exception()

    assertThrows<Exception> { useCase.execute("date") }
  }
}