package com.example.testapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.databinding.CardservicesliderBinding
import com.example.testapp.mehedi.getServiceList.Data
import com.example.testapp.mehedi.getServiceList.GetServiceModel

class ServiceCardAdapter (var datalist:List<Data>,var context: Context):
    RecyclerView.Adapter<ServiceCardAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: CardservicesliderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = CardservicesliderBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.TvServiceName.text = datalist[position].name

        Glide.with(context).load(datalist[position].image).into(holder.binding.ImgIconid)




    }
}