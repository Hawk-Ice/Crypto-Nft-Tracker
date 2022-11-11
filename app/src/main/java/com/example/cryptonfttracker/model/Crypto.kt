package com.example.cryptonfttracker.model

import com.example.cryptonfttracker.R
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class Crypto(
    // all values initialized to null
    @SerializedName("id"                               ) var id                           : String? = null,
    @SerializedName("symbol"                           ) var symbol                       : String? = null,
    @SerializedName("name"                             ) var name                         : String? = null,
    @SerializedName("image"                            ) var image                        : String? = null,
    @SerializedName("current_price"                    ) var currentPrice                 : String?    = null,
    @SerializedName("market_cap"                       ) var marketCap                    : String?    = null,
    @SerializedName("market_cap_rank"                  ) var marketCapRank                : String?    = null,
    @SerializedName("fully_diluted_valuation"          ) var fullyDilutedValuation        : String?    = null,
    @SerializedName("total_volume"                     ) var totalVolume                  : String?    = null,
    @SerializedName("high_24h"                         ) var high24h                      : String?    = null,
    @SerializedName("low_24h"                          ) var low24h                       : String?    = null,
    @SerializedName("price_change_24h"                 ) var priceChange24h               : String? = null,
    @SerializedName("price_change_percentage_24h"      ) var priceChangePercentage24h     : Float? = null,
    @SerializedName("market_cap_change_24h"            ) var marketCapChange24h           : String? = null,
    @SerializedName("market_cap_change_percentage_24h" ) var marketCapChangePercentage24h : Float? = null,
    @SerializedName("circulating_supply"               ) var circulatingSupply            : String?    = null,
    @SerializedName("total_supply"                     ) var totalSupply                  : String?    = null,
    @SerializedName("max_supply"                       ) var maxSupply                    : String?    = null,
    @SerializedName("ath"                              ) var ath                          : String?    = null,
    @SerializedName("ath_change_percentage"            ) var athChangePercentage          : String? = null,
    @SerializedName("ath_date"                         ) var athDate                      : String? = null,
    @SerializedName("atl"                              ) var atl                          : String? = null,
    @SerializedName("atl_change_percentage"            ) var atlChangePercentage          : String? = null,
    @SerializedName("atl_date"                         ) var atlDate                      : String? = null,
//    @SerializedName("roi"                              ) var roi                          : String? = null,
    @SerializedName("last_updated"                     ) var lastUpdated                  : String? = null
) {
    fun fetchRandomGraph():Int{
        var drawableList: MutableList<Int> = mutableListOf()
        drawableList.add(R.drawable.price1)
        drawableList.add(R.drawable.price2)
        drawableList.add(R.drawable.price3)
        drawableList.add(R.drawable.price4)
        drawableList.add(R.drawable.price5)
        drawableList.add(R.drawable.price6)
        drawableList.add(R.drawable.price7)
        drawableList.add(R.drawable.price8)
        drawableList.add(R.drawable.price9)
        drawableList.add(R.drawable.price10)
        drawableList.add(R.drawable.price11)
        drawableList.add(R.drawable.price12)

        return drawableList[Random.nextInt(0,11)]
    }
    /**
     *  Formats the 24-hour price change information to a string
     *  in the users' locale and adds a '+' symbol if the
     *  change is positive (and '-' symbol if negative)
     *
     *  @return the 24-hour price change percentage as a String
     */
    fun dayChangeString(): String {

        // format change percent with two decimal places
        // and using LOCALE information (by default)

//        val changeStr = priceChangePercentage24h?.let { "%.2f".format(it.toDoubleOrNull()) }
        val changeStr = "%.2f".format(priceChangePercentage24h)

        return if (priceChangePercentage24h!! > 0) {
            "+$changeStr%"
        } else {
            "$changeStr%"
        }
    }

    /**
     * Formats the price information to a string
     * and adds a decimal or comma where needed.
     *
     * @return the current price as a String
     */
    fun priceString(): String {
        var priceString = currentPrice.toString()
        var length = priceString.length

//        val tempStr = "%.2f".format(current_price)
//
//        println("TEMP STRING: $tempStr")

        // API returns cents with only 1 int if ending in 0
        // example: 12,345.9 (not .90)
        // check to add extra zero(s) if needed

        // string already contains a period
        if (priceString.contains('.')) {
            // if the decimal is followed by two integers, do nothing
            if (priceString[length - 3] == '.') {
                // do nothing
            } else {
                // add a zero to the end
                priceString += "0"
            }
        } else {
            // string contains no period
            // adds extra zeros
            priceString += ".00"
        }

        // update value of length
        length = priceString.length

        var resultString = ""

        if (length > 6) {
            // over $1,000 (for example)
            resultString = "${priceString.subSequence(0, priceString.length - 6)},${
                priceString.subSequence(
                    priceString.length - 6,
                    priceString.length
                )
            }"

            val newLength = resultString.length

            if (length > 9) {
                // over $1,000,000 (for example)
                resultString =
                    "${resultString.subSequence(0, newLength - 10)},${
                        resultString.subSequence(
                            newLength - 10,
                            newLength
                        )
                    }"
            }

        }
        return resultString
    }
}