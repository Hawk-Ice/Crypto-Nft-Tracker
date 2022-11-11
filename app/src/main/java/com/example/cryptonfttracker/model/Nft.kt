package com.example.cryptonfttracker.model

import com.google.gson.annotations.SerializedName

data class Nft(
    @SerializedName("symbol"      ) var symbol      : String?           = null,
    @SerializedName("name"        ) var name        : String?           = null,
    @SerializedName("description" ) var description : String?           = null,
    @SerializedName("image"       ) var image       : String?           = null,
    @SerializedName("twitter"     ) var twitter     : String?           = null,
    @SerializedName("discord"     ) var discord     : String?           = null,
    @SerializedName("website"     ) var website     : String?           = null,
    @SerializedName("isBadged"    ) var isBadged    : Boolean?          = null
){
//    var LAMPORTS_PER_SOL = 1000000000;

    fun getNftName():String {
        var name = symbol
        var newName = name?.replace("_", " ")
        return newName!!.uppercase()
    }

//    @SerializedName("symbol"       ) var symbol       : String? = null,
//    @SerializedName("floorPrice"   ) var floorPrice   : Long? = null,
//    @SerializedName("listedCount"  ) var listedCount  : Long? = null,
//    @SerializedName("avgPrice24hr" ) var avgPrice24hr : Double? = null,
//    @SerializedName("volumeAll"    ) var volumeAll    : Double? = null,

//    fun getFloorPriceFormatted() = floorPrice?.div(LAMPORTS_PER_SOL)
//    fun getAvgPrice24hrFormatted() = avgPrice24hr?.div(LAMPORTS_PER_SOL)
//    fun getVolumeAllFormatted() = volumeAll?.div(LAMPORTS_PER_SOL)

}
