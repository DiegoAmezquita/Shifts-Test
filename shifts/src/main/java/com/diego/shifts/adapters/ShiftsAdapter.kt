package com.diego.shifts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diego.shifts.R
import com.diego.shifts.data.ShiftUiModel
import com.diego.shifts.databinding.RecyclerShiftItemBinding

class ShiftsAdapter(val onClickListener: OnClickListener) :
  RecyclerView.Adapter<ShiftsAdapter.ShiftViewHolder>() {

  interface OnClickListener {
    fun onShiftClick(shift: ShiftUiModel)
  }

  private val shifts = mutableListOf<ShiftUiModel>()

  fun setShifts(orders: List<ShiftUiModel>) {
    this.shifts.clear()
    this.shifts.addAll(orders)
    notifyDataSetChanged()
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

    init {
      itemView.setOnClickListener {
        onClickListener.onShiftClick(requireNotNull(binding.shift))
      }
    }

    fun bind(shift: ShiftUiModel) {
      binding.shift = shift
    }
  }
}