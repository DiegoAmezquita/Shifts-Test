package com.diego.shifts.domain.repositories

import com.diego.core.data.Location
import com.diego.shifts.domain.data.ShiftRequest
import com.diego.shifts.domain.data.ShiftResponse
import com.diego.shifts.domain.services.ShiftService
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class ShiftRepositoryTest {

  @MockK
  private lateinit var service: ShiftService

  private lateinit var repository: ShiftRepositoryImpl

  @BeforeEach
  fun setup() {
    repository = ShiftRepositoryImpl(service)
  }

  @Test
  fun `return shifts correctly`() {
    val shifts = listOf<ShiftResponse>(mockk())
    coEvery { service.getShifts() } returns shifts

    runBlocking {
      val result = repository.getShifts()
      assertEquals(shifts, result)
    }
  }

  @Test
  fun `send correct info to start shift service`() {
    val time = "time"
    val latitude = "0.12124"
    val longitude = "-1.12124"
    val location = Location(latitude, longitude)
    val request = ShiftRequest(time, latitude, longitude)
    val message = "OK"
    coEvery { service.startShift(request) } returns message

    runBlocking {
      val result = repository.startShift(time, location)
      assertEquals(message, result)
    }
  }

  @Test
  fun `send correct info to end shift service`() {
    val time = "time"
    val latitude = "0.12124"
    val longitude = "-1.12124"
    val location = Location(latitude, longitude)
    val request = ShiftRequest(time, latitude, longitude)
    val message = "OK"
    coEvery { service.endShift(request) } returns message

    runBlocking {
      val result = repository.endShift(time, location)
      assertEquals(message, result)
    }
  }
}
