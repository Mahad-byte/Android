package com.example.bookingapp.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookingapp.R
import com.example.bookingapp.mvvm.Model

class Adapter(val context: Context, val hoteList: MutableList<Model>):
    RecyclerView.Adapter<Adapter.AdapterViewHolder>()
{
        inner class AdapterViewHolder(singleItem: View):
            RecyclerView.ViewHolder(singleItem)
        {
            //var card: CardView = singleItem.findViewById(R.id.card_view)
            var name: TextView = singleItem.findViewById(R.id.hotel_name)
            var price: TextView = singleItem.findViewById(R.id.hotel_price)
            var image: ImageView = singleItem.findViewById(R.id.hotel_image_adapter)
            var rating: TextView = singleItem.findViewById(R.id.hotel_rating)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.singleitem, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return hoteList.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        //TODO: See glide

        Glide.with(holder.image.context)
            .load(hoteList[position].image)
            .into(holder.image)
        holder.name.text = hoteList[position].name
        holder.price.text = hoteList[position].price.toString()
        holder.rating.text = hoteList[position].rating.toString()
    }
}