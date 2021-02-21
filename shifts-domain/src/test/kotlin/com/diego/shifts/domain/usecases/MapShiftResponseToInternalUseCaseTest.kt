package com.diego.shifts.domain.usecases

import com.diego.core.data.Location
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class MapShiftResponseToInternalUseCaseTest {

  @MockK
  private lateinit var mapDateUseCase: UseCase<String, String>

  private lateinit var useCase: MapShiftResponseToInternalUseCase

  companion object {
    @JvmStatic
    fun `map response to correct internal models`() = listOf(
      arrayOf(
        ShiftResponse(
          id = 1,
          start = "",
          end = "",
          startLatitude = "",
          startLongitude = "",
          endLatitude = "",
          endLongitude = "",
          image = ""
        ), Shift(
          id = 1,
          startDate = "date",
          endDate = "date",
          startLocation = Location("", ""),
          endLocation = Location("", ""),
          image = ""
        )
      ),arrayOf(
        ShiftResponse(
          id = 5,
          start = "",
          end = "",
          startLatitude = "0.00",
          startLongitude = "0.01",
          endLatitude = "1",
          endLongitude = "2",
          image = "NONE"
        ), Shift(
          id = 5,
          startDate = "date",
          endDate = "date",
          startLocation = Location("0.00", "0.01"),
          endLocation = Location("1", "2"),
          image = "NONE"
        )
      ),
    )
  }

  @BeforeEach
  fun setup() {

    every { mapDateUseCase.execute(any()) } returns "date"
    useCase = MapShiftResponseToInternalUseCase(mapDateUseCase)
  }

  @ParameterizedTest
  @MethodSource
  fun `map response to correct internal models`(response: ShiftResponse, excepted: Shift) {
    val result = useCase.execute(response)
    assertEquals(excepted, result)
  }
}