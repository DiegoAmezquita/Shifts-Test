package com.diego.shifts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.diego.shifts.databinding.ActivityShiftsBinding
import com.diego.shifts.viewmodels.ShiftsViewModel
import dagger.hilt.android.AndroidEntryPoint

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
  }
}