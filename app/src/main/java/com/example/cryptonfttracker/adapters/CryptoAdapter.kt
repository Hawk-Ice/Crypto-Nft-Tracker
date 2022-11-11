package com.example.cryptonfttracker.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptonfttracker.R
import com.example.cryptonfttracker.model.Crypto
import com.squareup.picasso.Picasso
import kotlin.random.Random


class CryptoAdapter(private val dataSet: List<Crypto>) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cryptoFullName: TextView = itemView.findViewById(R.id.cryptoFullName)
        val cryptoShortName: TextView = itemView.findViewById(R.id.cryptoShortName)
        val cryptoValue: TextView = itemView.findViewById(R.id.cryptoValue)
        val priceActionPercentage: TextView = itemView.findViewById(R.id.priceActionPercentage)
        val cryptoImage : ImageView = itemView.findViewById(R.id.cryptoImage)
        val priceChart : ImageView = itemView.findViewById(R.id.priceChart)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crypto_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val data= dataSet[position]
        Picasso.get().load(data.image).into(viewHolder.cryptoImage)

        viewHolder.cryptoFullName.text = data.name
        viewHolder.cryptoShortName.text = data.symbol.toString().uppercase()
        viewHolder.cryptoValue.text = data.currentPrice
        viewHolder.priceActionPercentage.text = data.dayChangeString()

//        var drawableList: MutableList<Int> = mutableListOf()
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)
//        drawableList.add(R.drawable.price1)

        viewHolder.priceChart.setBackgroundResource(data.fetchRandomGraph())

        if (data.dayChangeString().contains('+')) {
            viewHolder.priceActionPercentage.setTextColor(Color.parseColor("#039E0A"))

        } else {
            viewHolder.priceActionPercentage.setTextColor(Color.parseColor("#F44336"))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.count()

}