package com.example.cryptonfttracker.ui.main

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptonfttracker.adapters.NftAdapter
import com.example.cryptonfttracker.adapters.WatchlistAdapter
import com.example.cryptonfttracker.databinding.NftFragmentBinding
import com.example.cryptonfttracker.databinding.WatchlistFragmentBinding
import com.example.cryptonfttracker.model.Crypto
import com.example.cryptonfttracker.model.Nft
import com.example.cryptonfttracker.model.Watchlist
import com.google.gson.Gson
import okhttp3.*
import okhttp3.internal.immutableListOf
import java.io.IOException

class WatchlistFragment : Fragment() {

    // initialize global List for multiple calls

    private lateinit var pageViewModel: PageViewModel
    private var _binding: WatchlistFragmentBinding? = null
    private val binding get() = _binding!!
    private fun fetchAnotherData(recyclerView: RecyclerView, watchlist:MutableList<Watchlist>){



        // current CoinGecko url to send GET request
        val url = "https://api-mainnet.magiceden.dev/v2/collections/degods/stats"
        // OkHttp
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                // successful GET request
                Log.i(ContentValues.TAG, "GET request successful.")

                // converts response into string
                val body = response.body?.string()


                val item: Watchlist = Gson().fromJson(body, Watchlist::class.java)

                item.image = "https://solanart.io/_next/image?url=https%3A%2F%2Fres.cloudinary.com%2Fdemo%2Fimage%2Ffetch%2Fh_500%2Fhttps%3A%2F%2Fmetadata.degods.com%2Fg%2F9365-dead.png&w=1920&q=75"
                watchlist.add(item)
                Log.d(TAG, "NFT CONTENTS"+ item)




                Log.d(TAG, "NFT Array"+ watchlist.toString())


//                "https://solanart.io/_next/image?url=https%3A%2F%2Fres.cloudinary.com%2Fdemo%2Fimage%2Ffetch%2Fh_500%2Fhttps%3A%2F%2Fmetadata.degods.com%2Fg%2F9365-dead.png&w=1920&q=75"

                val adapter: RecyclerView.Adapter<*> = WatchlistAdapter(watchlist)

                activity?.runOnUiThread {
                    recyclerView.adapter = adapter;
                    recyclerView.adapter!!.notifyDataSetChanged()
                    recyclerView.setHasFixedSize(true);
                }



            }
        })
    }
    private fun fetchData(recyclerView: RecyclerView, watchlist:MutableList<Watchlist>){


        // current CoinGecko url to send GET request
        val url = "https://api-mainnet.magiceden.dev/v2/collections/taiyo_robotics/stats"

        // OkHttp
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                // successful GET request
                Log.i(ContentValues.TAG, "GET request successful.")

                // converts response into string
                val body = response.body?.string()


                val item: Watchlist = Gson().fromJson(body, Watchlist::class.java)
                item.image = "https://solanart.io/_next/image?url=https%3A%2F%2Fres.cloudinary.com%2Fdemo%2Fimage%2Ffetch%2Fh_500%2Fhttps%3A%2F%2Fcdn.taiyorobotics.com%2Fimages%2F00f4d3a7-f9e3-442d-825a-1cbcf1c9b26f.png%3F1661266732410&w=1920&q=75"
                watchlist.add(item)
                Log.d(TAG, "NFT CONTENTS"+ item)




                Log.d(TAG, "NFT Array"+ watchlist.toString())



                val adapter: RecyclerView.Adapter<*> = WatchlistAdapter(watchlist)

                activity?.runOnUiThread {
                    recyclerView.adapter = adapter;
                    recyclerView.adapter!!.notifyDataSetChanged()
                    recyclerView.setHasFixedSize(true);
                }



            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Oncreate nftfragment", "SUCCESS")
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            // The argument here is the page index i guess?
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // this is just binding. but which view?
        _binding = WatchlistFragmentBinding.inflate(inflater, container, false)
        val root = binding.root

        val watchlistRecyclerView: RecyclerView = binding.watchlistRecyclerview
        watchlistRecyclerView.layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)
        pageViewModel.watchList.observe(viewLifecycleOwner, Observer(){
            // we repopulate and notifydata set changed on the set adapter i guess?
        })
        val watchlist:MutableList<Watchlist> = mutableListOf()
        fetchData(watchlistRecyclerView, watchlist)
        fetchAnotherData(watchlistRecyclerView, watchlist)
        return root
    }
    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): WatchlistFragment {
            return WatchlistFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}