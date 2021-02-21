package com.diego.deputy.usecases

import com.diego.core.PermissionException
import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.Location
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetDeviceLocationUseCase @Inject constructor(
  private val fusedLocationClient: FusedLocationProviderClient
) : AsyncUseCase<Unit, Location> {

  override suspend fun execute(input: Unit): Location {
    return suspendCoroutine { continuation ->
      fusedLocationClient.lastLocation.addOnSuccessListener {
        continuation.resume(Location(it.latitude.toString(), it.longitude.toString()))
      }.addOnFailureListener {
        val exception = if (it is SecurityException) {
          PermissionException
        } else {
          it
        }
        continuation.resumeWith(Result.failure(exception))
      }
    }
  }
}