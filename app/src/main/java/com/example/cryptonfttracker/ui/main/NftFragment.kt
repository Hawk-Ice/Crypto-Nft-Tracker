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
import com.example.cryptonfttracker.databinding.NftFragmentBinding
import com.example.cryptonfttracker.model.Crypto
import com.example.cryptonfttracker.model.Nft
import com.google.gson.Gson
import okhttp3.*
import okhttp3.internal.immutableListOf
import java.io.IOException

class NftFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: NftFragmentBinding? = null
    private val binding get() = _binding!!

    private fun fetchData(recyclerView: RecyclerView){
        // current CoinGecko url to send GET request
        val url = "https://api-mainnet.magiceden.dev/v2/collections?offset=0&limit=100"

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

//                val testArrayJson: Array<Crypto> = Gson().fromJson(body, Array<Crypto>::class.java)
//                val data:List<Crypto> = testArrayJson.toList();

//                val nft: Nft = Gson().fromJson(body, Nft::class.java)
//                Log.d(TAG, "NFT CONTENTS"+ nft)
//
//                val nftList:MutableList<Nft> = mutableListOf()
//                nftList.add(nft)
//
//                Log.d(TAG, "NFT Array"+ nftList.toString())

                val testArrayJson: Array<Nft> = Gson().fromJson(body, Array<Nft>::class.java)
                val data:List<Nft> = testArrayJson.toList();


                val adapter: RecyclerView.Adapter<*> = NftAdapter(data)

                activity?.runOnUiThread {
                    recyclerView.adapter = adapter;
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
        _binding = NftFragmentBinding.inflate(inflater, container, false)
        val root = binding.root

        val nftRecyclerView: RecyclerView = binding.nftRecyclerview
        nftRecyclerView.layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)
        pageViewModel.nftList.observe(viewLifecycleOwner, Observer(){
            // we repopulate and notifydata set changed on the set adapter i guess?
        })

        fetchData(nftRecyclerView)
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
        fun newInstance(sectionNumber: Int): NftFragment {
            return NftFragment().apply {
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