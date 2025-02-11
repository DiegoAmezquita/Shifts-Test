package com.diego.deputy.di.modules

import com.diego.core.qualifiers.DispatcherIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

  @Provides
  @DispatcherIO
  fun providesIODispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
  }
}