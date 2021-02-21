package com.diego.shifts.domain.usecases

import com.diego.core.data.Location
import com.diego.shifts.contract.AsyncUseCase
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
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class StartShiftsUseCaseTest {

  @MockK
  private lateinit var repository: ShiftRepository

  @MockK
  private lateinit var getLocationUseCase: AsyncUseCase<Unit, Location>

  @MockK
  private lateinit var getFormattedDeviceTime: UseCase<Unit, String>

  private lateinit var useCase: StartShiftsUseCase

  @BeforeEach
  fun setup() {
    useCase = StartShiftsUseCase(repository, getLocationUseCase, getFormattedDeviceTime)
  }

  @Test
  fun `return start shifts message correctly`() {
    val location = mockk<Location>()
    val time = "time"

    val response = "OK"

    coEvery { getLocationUseCase.execute(Unit) } returns location
    every { getFormattedDeviceTime.execute(Unit) } returns time

    coEvery { repository.startShift(time, location) } returns response

    runBlocking {
      val result = useCase.execute(Unit)
      assertEquals(response, result)
    }
  }

  @Test
  fun `return start shift passes exception`() {
    val time = "time"
    coEvery { getLocationUseCase.execute(Unit) } throws Exception()
    every { getFormattedDeviceTime.execute(Unit) } returns time

    runBlocking {
      assertThrows<Exception> { useCase.execute(Unit) }
    }
  }
}