package com.diego.shifts.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diego.shifts.adapters.ShiftsAdapter
import com.diego.shifts.data.ShiftUiModel

class ShiftsRecyclerView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

  private val shiftsAdapter = ShiftsAdapter()

  init {
    layoutManager = LinearLayoutManager(context)
    addItemDecoration(DividerItemDecoration(context, VERTICAL))
    adapter = shiftsAdapter
  }

  fun setShifts(shifts: List<ShiftUiModel>?) {
    if (shifts != null) {
      shiftsAdapter.setShifts(shifts)
    }
  }
}