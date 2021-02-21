package com.diego.deputy.di.modules

import com.diego.shifts.domain.services.ShiftService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

  @Provides
  fun provideShiftsService(retrofit: Retrofit): ShiftService {
    return retrofit.create(ShiftService::class.java)
  }
}