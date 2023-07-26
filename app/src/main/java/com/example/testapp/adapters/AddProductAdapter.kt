package com.example.testapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.AddproductscardBinding
import com.example.testapp.databinding.CardservicesliderBinding
import com.example.testapp.mehedi.addproduct.AddProData
import com.example.testapp.mehedi.getproductlist.getProductData
import kotlin.math.log


class AddProductAdapter(var datalist:List<getProductData>, var context: Context):
    RecyclerView.Adapter<AddProductAdapter.MyViewHolder>() {
    var onItemclick:((getProductData) -> Unit)?=null
    class MyViewHolder(var binding: AddproductscardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = AddproductscardBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.TvPrName .text = datalist[position].name
        holder.binding.TvSrVal.text = datalist[position].price

        try{
            //var imgaes  =

            if (datalist[position].images !=null) {
//            //Glide.with(context).load(datalist[position].image).into(holder.binding.ImgProuct )
                Glide.with(context).load(datalist[position].images!![0].image).into(holder.binding.ImgProuct)
            }
            else {
                holder.binding.ImgProuct.setImageResource(R.drawable.catimg3)

            }
            Log.i("AdapterLog","${datalist[position].images}")
        }catch (e:Exception){

        }

        holder.itemView.setOnClickListener{
            onItemclick?.invoke(datalist[position])
        }







    }
}


