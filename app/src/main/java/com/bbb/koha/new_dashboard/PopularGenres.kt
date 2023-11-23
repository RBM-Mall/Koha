package com.bbb.koha.new_dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bbb.koha.R
import com.bbb.koha.databinding.ArrivalsListItemBinding
import com.bbb.koha.databinding.PickpopularListItemBinding

class PopularGenres(var list:ArrayList<MovieResponse>): RecyclerView.Adapter<PopularGenres.MyViewHolder>() {

    class MyViewHolder(var binding: PickpopularListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularGenres.MyViewHolder {
        var binding = DataBindingUtil.inflate<PickpopularListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.pickpopular_list_item,parent,false)
        return PopularGenres.MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PopularGenres.MyViewHolder, position: Int) {
        var item = list[position]
        holder.itemView.context
        holder.binding.apply {
            tvTitle.text = item.Title
        }
    }


}