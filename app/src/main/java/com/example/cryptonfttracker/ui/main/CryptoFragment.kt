package com.example.cryptonfttracker.ui.main

import android.content.ContentValues
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
import com.example.cryptonfttracker.adapters.CryptoAdapter
import com.example.cryptonfttracker.databinding.CryptoFragmentBinding
import com.example.cryptonfttracker.model.Crypto
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class CryptoFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private var _binding: CryptoFragmentBinding? = null
    private val binding get() = _binding!!

    private fun fetchData(recyclerView: RecyclerView){
        // current CoinGecko url to send GET request
        val url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=php"

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

                val testArrayJson: Array<Crypto> = Gson().fromJson(body, Array<Crypto>::class.java)
                val data:List<Crypto> = testArrayJson.toList();
                Log.d("TAG", "TAG " +data[0].id)

                val adapter: RecyclerView.Adapter<*> = CryptoAdapter(data)

                activity?.runOnUiThread {
                    recyclerView.adapter = adapter;
                    recyclerView.setHasFixedSize(true);
                }


            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mahalaga ang view model for state persistence when navigating thru different tabs


        // Create new instance of page view model for this specific fragment
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            // The argument here is the page index i guess?
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // this is just binding. but which view?
        _binding = CryptoFragmentBinding.inflate(inflater, container, false)
        val root = binding.root
        // recyclerview
        val cryptoRecyclerView: RecyclerView = binding.cryptoRecyclerview
        cryptoRecyclerView.layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)
        pageViewModel.cryptoList.observe(viewLifecycleOwner, Observer(){
            // we repopulate and notifydata set changed on the set adapter i guess?
        })

        fetchData(cryptoRecyclerView)

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
        fun newInstance(sectionNumber: Int): CryptoFragment {
            return CryptoFragment().apply {
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