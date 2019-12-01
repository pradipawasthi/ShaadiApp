package com.pradip.weatherapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pradip.weatherapp.R
import com.pradip.weatherapp.common.getDayNameFromDate
import com.pradip.weatherapp.dataModel.ForecastdayDataModel
import com.pradip.weatherapp.databinding.ItemForecastviewBinding
import kotlin.math.roundToInt


class ForecastDaysAdapter : RecyclerView.Adapter<ForecastDaysAdapter.ViewHolder>() {

    var items: List<ForecastdayDataModel>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate<ItemForecastviewBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_forecastview, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items!![position])

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    class ViewHolder constructor(var binding: ItemForecastviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastdayDataModel) {
            binding.executePendingBindings()
            binding.itemTextView.text = getDayNameFromDate(item.date)
            binding.itemTextViewValue.text = binding.root.context.getString(
                R.string.string_forecast,
                item.day.avgtempC.roundToInt().toString()
            )

        }


    }

    fun updateData(items: List<ForecastdayDataModel>) {
        this.items = items
        notifyDataSetChanged()
    }
}