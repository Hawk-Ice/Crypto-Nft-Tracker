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
import com.example.cryptonfttracker.model.Nft
import com.example.cryptonfttracker.model.Watchlist
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import kotlin.random.Random


class WatchlistAdapter(private val dataSet: List<Watchlist>) :
    RecyclerView.Adapter<WatchlistAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nftFullName: TextView = itemView.findViewById(R.id.nft_fullname)
        val nftFloorValue: TextView = itemView.findViewById(R.id.nft_floorvalue)
        val nftPriceAction: TextView = itemView.findViewById(R.id.nft_priceaction)
        val nftDayVolume: TextView = itemView.findViewById(R.id.nft_dayvolume)
        val nftImage : ImageView = itemView.findViewById(R.id.image)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.watchlist_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val data= dataSet[position]


        viewHolder.nftFullName.text = data.getNftName()
        viewHolder.nftFloorValue.text = data.getFloorPriceFormatted().toString()
        viewHolder.nftPriceAction.text = String.format("%.2f", Random.nextDouble(0.1, 100.0 )).toDouble().toString()+"%"
        viewHolder.nftDayVolume.text = String.format("%.2f", Random.nextDouble(0.1, 50.0)).toDouble().toString()+"k"
        Picasso.get()
            .load(data.image)
            .resize(100,100)
            .onlyScaleDown()
            .into(viewHolder.nftImage)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.count()

}