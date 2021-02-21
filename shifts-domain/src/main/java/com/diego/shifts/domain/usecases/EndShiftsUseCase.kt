package com.diego.shifts.domain.usecases

import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.Location
import com.diego.shifts.domain.repositories.ShiftRepository
import javax.inject.Inject

class EndShiftsUseCase @Inject constructor(
  private val repository: ShiftRepository,
) : AsyncUseCase<Unit, String> {

  override suspend fun execute(input: Unit): String {
    return repository.endShift("2021-02-11T09:00:00+00:00", Location("52.7514", "52.7514"))
  }
}