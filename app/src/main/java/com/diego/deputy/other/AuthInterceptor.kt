package com.diego.deputy.other

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

private const val AUTHORIZATION = "Authorization"
private const val DEPUTY = "Deputy"

class AuthInterceptor @Inject constructor(
  private val token: String
) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val newRequest = chain.request().newBuilder()
      .addHeader(AUTHORIZATION, "$DEPUTY $token")
      .build()

    return chain.proceed(newRequest)
  }
}
