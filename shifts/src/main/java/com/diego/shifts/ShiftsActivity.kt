package com.diego.shifts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.diego.shifts.databinding.ActivityShiftsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShiftsActivity : AppCompatActivity() {

  private lateinit var binding: ActivityShiftsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(
      this, R.layout.activity_shifts
    )
    setContentView(binding.root)
  }
}