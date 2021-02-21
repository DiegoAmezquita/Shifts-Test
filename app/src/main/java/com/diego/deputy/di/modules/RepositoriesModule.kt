package com.diego.deputy.di.modules

import com.diego.shifts.domain.repositories.ShiftRepository
import com.diego.shifts.domain.repositories.ShiftRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

  @Binds
  abstract fun bindsShiftRepository(shiftRepository: ShiftRepositoryImpl): ShiftRepository

}

