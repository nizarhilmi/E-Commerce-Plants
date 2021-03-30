package com.epsilon.plants.Ui.Home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epsilon.plants.Data.MyData
import com.epsilon.plants.R
import com.epsilon.plants.Ui.detail.DetailActivity

class AdapterMain (private val listMyData: ArrayList<MyData>, val context: Context)  :
    RecyclerView.Adapter<AdapterMain.CardViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_produk, parent, false)
        return CardViewViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myData = listMyData[position]
        Glide.with(holder.itemView.context)
            .load(myData.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.tvName.text = myData.name
        holder.tvPrice.text = myData.price
        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
            moveWithObjectIntent.putExtra(DetailActivity.EXTRA_MYDATA, myData)
            context.startActivity(moveWithObjectIntent)

        }
    }
    override fun getItemCount(): Int {
        return listMyData.size
    }
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.product_photo)
        var tvName: TextView = itemView.findViewById(R.id.product_name)
        var tvPrice: TextView = itemView.findViewById(R.id.product_price)
    }
}
