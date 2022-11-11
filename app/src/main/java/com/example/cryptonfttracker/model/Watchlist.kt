package com.example.cryptonfttracker.model

import com.google.gson.annotations.SerializedName

data class Watchlist(
    @SerializedName("symbol"       ) var symbol       : String? = null,
    @SerializedName("floorPrice"   ) var floorPrice   : Long? = null,
    @SerializedName("listedCount"  ) var listedCount  : Long? = null,
    @SerializedName("avgPrice24hr" ) var avgPrice24hr : Double? = null,
    @SerializedName("volumeAll"    ) var volumeAll    : Double? = null,
    @SerializedName("image"    ) var image    : String? = null
){

    fun getNftName():String {
        var name = symbol
        var newName = name?.replace("_", " ")
        return newName!!.uppercase()
    }



    var LAMPORTS_PER_SOL = 1000000000;
    fun getFloorPriceFormatted() = floorPrice?.div(LAMPORTS_PER_SOL)
    fun getAvgPrice24hrFormatted() = avgPrice24hr?.div(LAMPORTS_PER_SOL)
    fun getVolumeAllFormatted() = volumeAll?.div(LAMPORTS_PER_SOL)
}
