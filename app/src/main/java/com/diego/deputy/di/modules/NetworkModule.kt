package com.diego.deputy.di.modules

import com.diego.deputy.BuildConfig
import com.diego.deputy.other.AuthInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  fun providesOkHttpClientBuilder(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
  }

  @Provides
  @Singleton
  fun providesMoshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  @Singleton
  fun providesAuthInterceptor(): Interceptor {
    return AuthInterceptor("8354336224c63279aadd00a9621757ef4fdf31fc  -")
  }

  @Provides
  @Singleton
  fun providesOkHttpClient(
    builder: OkHttpClient.Builder,
    interceptor: Interceptor
  ): OkHttpClient {
    return builder
      .addInterceptor(interceptor)
      .build()
  }

  @Provides
  @Singleton
  fun providesRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
  ): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(BuildConfig.SERVER_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
  }
}