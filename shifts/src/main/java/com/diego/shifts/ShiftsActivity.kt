package com.diego.shifts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.diego.shifts.databinding.ActivityShiftsBinding
import com.diego.shifts.viewmodels.ShiftNews
import com.diego.shifts.viewmodels.ShiftsViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val LOCATION_PERMISSION_REQUEST_CODE = 985

@AndroidEntryPoint
class ShiftsActivity : AppCompatActivity() {

  private val viewModel: ShiftsViewModel by viewModels()

  private lateinit var binding: ActivityShiftsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(
      this, R.layout.activity_shifts
    )
    setContentView(binding.root)

    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    observeViewModel()
  }

  private fun observeViewModel() {
    viewModel.news.observe(this, {
      when (it) {
        is ShiftNews.MessageNews -> {
          Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        ShiftNews.RequestPermissionNews -> requestPermission()
      }
    })
  }

  private fun requestPermission() {
    ActivityCompat.requestPermissions(
      this,
      arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
      LOCATION_PERMISSION_REQUEST_CODE
    )
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    if (requestCode == LOCATION_PERMISSION_REQUEST_CODE &&
      grantResults.isNotEmpty() &&
      grantResults[0] == PackageManager.PERMISSION_GRANTED
    ) {
      Toast.makeText(this, R.string.shift_permission_granted, Toast.LENGTH_SHORT).show()
    } else {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
  }
}