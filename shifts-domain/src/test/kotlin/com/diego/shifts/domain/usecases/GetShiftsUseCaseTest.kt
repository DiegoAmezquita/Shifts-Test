package com.diego.shifts.domain.usecases

import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.repositories.ShiftRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class GetShiftsUseCaseTest {

  @MockK
  private lateinit var repository: ShiftRepository

  @MockK
  private lateinit var mapShiftResponseToInternalUseCase: UseCase<ShiftResponse, Shift>

  private lateinit var useCase: GetShiftsUseCase

  @BeforeEach
  fun setup() {
    useCase = GetShiftsUseCase(repository, mapShiftResponseToInternalUseCase)
  }

  @Test
  fun `return shifts correctly`() {
    val shiftMock = mockk<Shift>()
    val shiftResponseMock = mockk<ShiftResponse>()
    val shifts = listOf(shiftResponseMock)
    coEvery { repository.getShifts() } returns shifts
    every { mapShiftResponseToInternalUseCase.execute(any()) } returns shiftMock

    runBlocking {
      val result = useCase.execute(Unit)
      assertEquals(listOf(shiftMock), result)
    }
  }
}