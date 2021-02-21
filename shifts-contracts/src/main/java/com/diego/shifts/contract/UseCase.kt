package com.diego.shifts.contract

interface UseCase<T, R> {
  fun execute(input: T): R
}

interface AsyncUseCase<T, R> {
  suspend fun execute(input: T): R
}