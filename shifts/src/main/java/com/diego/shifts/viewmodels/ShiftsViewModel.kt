package com.diego.shifts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.core.PermissionException
import com.diego.core.qualifiers.DispatcherIO
import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.EndShift
import com.diego.shifts.contract.GetShifts
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.StartShift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.data.ShiftUiModel
import com.diego.shifts.data.ShiftsUIModel
import com.diego.shifts.others.MapShiftToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShiftsViewModel @Inject constructor(
  @GetShifts private val getShiftsUseCase: AsyncUseCase<Unit, List<Shift>>,
  @StartShift private val startShiftUseCase: AsyncUseCase<Unit, String>,
  @EndShift private val endShiftUseCase: AsyncUseCase<Unit, String>,
  @MapShiftToUiModel private val mapShiftToUiModelUseCase: UseCase<Shift, ShiftUiModel>,
  @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

  private val _data = MutableLiveData(ShiftsUIModel())
  val data: LiveData<ShiftsUIModel> = _data

  private val _news = MutableLiveData<ShiftNews>()
  val news: LiveData<ShiftNews> = _news

  init {
    fetchData()
  }

  private fun fetchData() {
    viewModelScope.launch(dispatcherIO) {
      _data.postValue(_data.value?.copy(loading = true))

      val newValue = try {
        val shifts = getShiftsUseCase.execute(Unit).map { mapShiftToUiModelUseCase.execute(it) }
        ShiftsUIModel(shifts, false)
      } catch (e: Exception) {
        e.printStackTrace()
        _data.value?.copy(loading = false)
      }
      _data.postValue(newValue)
    }
  }

  fun startShift() {
    viewModelScope.launch(dispatcherIO) {
      try {
        _data.postValue(_data.value?.copy(loading = true))
        val message = startShiftUseCase.execute(Unit)
        _news.postValue(ShiftNews.MessageNews(message))
        fetchData()
      } catch (e: Exception) {
        handleException(e)
        _data.postValue(_data.value?.copy(loading = false))
      }
    }
  }

  fun endShift() {
    viewModelScope.launch(dispatcherIO) {
      try {
        _data.postValue(_data.value?.copy(loading = true))
        val message = endShiftUseCase.execute(Unit)
        _news.postValue(ShiftNews.MessageNews(message))
        fetchData()
      } catch (e: Exception) {
        // Could be improved
        handleException(e)
        _data.postValue(_data.value?.copy(loading = false))
      }
    }
  }

  private fun handleException(exception: Exception) {
    if (exception is PermissionException) {
      _news.postValue(ShiftNews.RequestPermissionNews)
    } else {
      _news.postValue(ShiftNews.MessageNews(exception.message.orEmpty()))
    }
  }
}


sealed class ShiftNews {
  data class MessageNews(val message: String) : ShiftNews()
  object RequestPermissionNews : ShiftNews()
}