package com.example.myapplication.viewmodel

import android.nfc.Tag
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.api.RetrofitHelper
import com.example.myapplication.databinding.FragmentApplicationBinding
import com.example.myapplication.model.ApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel() : ViewModel() {

    private val KID_ID = "378"

    private val appListLiveData: MutableLiveData<ApiData> = MutableLiveData()

    fun getApplicationDataObserver(): MutableLiveData<ApiData> {
        return appListLiveData
    }

    fun makeApiCall(binding: FragmentApplicationBinding) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val retroinstance = RetrofitHelper.getInstance().create(ApiInterface::class.java)
                val response = retroinstance.getApplicationData(KID_ID)
                appListLiveData.postValue(response.body()!!)
            } catch (e: Exception) {
                // Handle errors (e.g., show error message)
                e.printStackTrace()
                print(e)
                showError(binding);
            } finally {
                // Hide loading indicator when the API call is completed
                withContext(Dispatchers.Main) {
                    binding.loadingIndicator.visibility = View.GONE
                }

            }

        }
    }
    fun showError(binding: FragmentApplicationBinding){

        binding.errorTextView.text = "Some Error Occurred.\n Click to retry."
        binding.errorTextView.visibility = View.VISIBLE

        // Optionally, you can hide the loading indicator if it's still visible
        binding.loadingIndicator.visibility = View.GONE
    }
}
