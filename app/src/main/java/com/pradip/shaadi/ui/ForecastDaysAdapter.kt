package com.pradip.shaadi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pradip.shaadi.dataModel.CandidateDetailsDataModel
import com.pradip.shaadi.dataModel.ResultsItemDataModel
import com.pradip.weatherapp.R
import com.pradip.weatherapp.databinding.ItemForecastviewBinding


class ForecastDaysAdapter : RecyclerView.Adapter<ForecastDaysAdapter.ViewHolder>() {

    private var items: List<ResultsItemDataModel>? = null


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

        fun bind(item: ResultsItemDataModel) {
            binding.resultsItem = item
            binding.executePendingBindings()
//            binding.itemTextView.text = getDayNameFromDate(item.date)
//            binding.itemTextViewValue.text = binding.root.context.getString(
//                R.string.string_forecast,
//                item.day.avgtempC.roundToInt().toString()
//            )

        }


    }

    fun updateData(items: List<ResultsItemDataModel>) {
        this.items = items
        notifyDataSetChanged()
    }
}