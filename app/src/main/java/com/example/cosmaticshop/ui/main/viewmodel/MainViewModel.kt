package com.example.cosmaticshop.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmaticshop.data.model.MakeUpProductsModel
import com.example.cosmaticshop.data.repository.MainRepository
import com.example.cosmaticshop.utils.NetworkHelper
import com.example.cosmaticshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


/**
 * View model of the main screen
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val makeUpProductList = MutableLiveData<Resource<List<MakeUpProductsModel>>>()

    /**
     * Returns the make up products
     */
    fun fetchMakeUpProducts(): LiveData<Resource<List<MakeUpProductsModel>>> {
        try {
            if (networkHelper.isNetworkConnected()) {
                viewModelScope.launch {
                    makeUpProductList.postValue(Resource.loading(null))
                    mainRepository.getMakeUpProducts().let {
                        if (it.isSuccessful) {
                            makeUpProductList.postValue(Resource.success(it.body()))
                            Log.e("Product", it.body().toString())
                        } else makeUpProductList.postValue(
                            Resource.error(
                                it.errorBody().toString(),
                                null
                            )
                        )
                    }

                }
            } else {
                makeUpProductList.postValue(Resource.error("Check internet connection", null))
            }
        }catch(e: IOException){
            makeUpProductList.postValue(Resource.error("Time Out", null))

        }
        return makeUpProductList
    }
}