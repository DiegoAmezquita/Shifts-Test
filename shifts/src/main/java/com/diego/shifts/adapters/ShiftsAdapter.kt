package com.diego.shifts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.shifts.R
import com.diego.shifts.data.ShiftUiModel
import com.diego.shifts.databinding.RecyclerShiftItemBinding

class ShiftsAdapter : RecyclerView.Adapter<ShiftsAdapter.ShiftViewHolder>() {

  private val shifts = mutableListOf<ShiftUiModel>()

  fun setShifts(shifts: List<ShiftUiModel>) {
    notifyChanges(this.shifts, shifts)
    this.shifts.clear()
    this.shifts.addAll(shifts)
  }

  private fun notifyChanges(oldData: List<ShiftUiModel>, newData: List<ShiftUiModel>) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
      override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
      }

      override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
      }

      override fun getOldListSize() = oldData.size
      override fun getNewListSize() = newData.size
    })
    diff.dispatchUpdatesTo(this)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.recycler_shift_item, parent, false)
    return ShiftViewHolder(view)
  }

  override fun getItemCount(): Int {
    return shifts.size
  }

  override fun onBindViewHolder(holder: ShiftViewHolder, position: Int) {
    holder.bind(shifts[position])
  }

  inner class ShiftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RecyclerShiftItemBinding.bind(itemView)

    fun bind(shift: ShiftUiModel) {
      binding.shift = shift
    }
  }
}