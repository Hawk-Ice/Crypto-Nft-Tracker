package com.example.cryptonfttracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptonfttracker.model.Crypto
import com.example.cryptonfttracker.model.Nft
import com.example.cryptonfttracker.model.Watchlist

class PageViewModel : ViewModel() {


    // View MODEL holds the data temporarily

    private val _index = MutableLiveData<Int>()


    // List of Crypto
    val cryptoList : MutableLiveData<List<Crypto>> by lazy {
         MutableLiveData<List<Crypto>>()
    }

    val nftList : MutableLiveData<List<Nft>> by lazy {
        MutableLiveData<List<Nft>>()
    }

    val watchList : MutableLiveData<List<Watchlist>> by lazy {
        MutableLiveData<List<Watchlist>>()
    }

//    val text: LiveData<String> = Transformations.map(_index) {
//        "Hello world from section: $it"
//    }




    // set the index
    fun setIndex(index: Int) {
        _index.value = index
    }

    // Sets the list
    fun setList(list: List<Crypto>){
        cryptoList.value = list
    }


}