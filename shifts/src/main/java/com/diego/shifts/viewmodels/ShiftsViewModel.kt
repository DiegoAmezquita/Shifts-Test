package com.diego.shifts.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.GetShifts
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.data.ShiftUiModel
import com.diego.shifts.others.MapShiftToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ShiftsViewModel @Inject constructor(
  @GetShifts private val getShiftsUseCase: AsyncUseCase<Unit, List<Shift>?>,
  @MapShiftToUiModel private val mapShiftToUiModelUseCase: UseCase<Shift, ShiftUiModel>
) : ViewModel() {

  val data = liveData(Dispatchers.IO) {
    try {
      val shifts = getShiftsUseCase.execute(Unit)?.map { mapShiftToUiModelUseCase.execute(it) }
      emit(shifts)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}