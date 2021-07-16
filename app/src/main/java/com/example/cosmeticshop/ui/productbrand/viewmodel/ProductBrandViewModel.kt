package com.example.cosmeticshop.ui.productbrand.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.data.repository.MakeUpProductRepository
import com.example.cosmeticshop.utils.NetworkHelper
import com.example.cosmeticshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ProductBrandViewModel @Inject constructor(
    private val makeUpProductRepository: MakeUpProductRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val makeUpProductList = MutableLiveData<Resource<List<MakeUpProductsModel>>>()

    fun fetchMakeUpProducts(): LiveData<Resource<List<MakeUpProductsModel>>> {
        try {
            if (networkHelper.isNetworkConnected()) {
                viewModelScope.launch {
                    makeUpProductList.postValue(Resource.loading(null))
                    makeUpProductRepository.getMakeUpProducts().let {
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
        } catch (e: IOException) {
            makeUpProductList.postValue(Resource.error("Time Out", null))

        }
        return makeUpProductList
    }
}