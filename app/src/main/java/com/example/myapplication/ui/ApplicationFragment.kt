package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.FragmentApplicationBinding
import com.example.myapplication.model.App
import com.example.myapplication.viewmodel.MainActivityViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ApplicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ApplicationFragment : Fragment() {

    private lateinit var binding: FragmentApplicationBinding;
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentApplicationBinding.inflate(layoutInflater, container, false)

        setViewProperties()

        return binding.root;
    }


    private fun setViewProperties() {
        initViewModel()
        initRecyclerView()
        binding.errorTextView.setOnClickListener {
            makeAPICall();
        }


        // Set a listener for the search view
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerViewAdapter.filter(newText.orEmpty())
                return true
            }
        })

    }


    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        makeAPICall()
        viewModel.getApplicationDataObserver().observe(this, Observer {

            it?.let {
                recyclerViewAdapter.setUpdatedData(it.data.app_list as ArrayList<App>)
            } ?: kotlin.run {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun initRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)


        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    fun makeAPICall() {
        binding.loadingIndicator.visibility = View.VISIBLE
        viewModel.makeApiCall(binding)
    }



}