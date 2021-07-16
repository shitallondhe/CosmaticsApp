package com.example.cosmeticshop.ui.producttype.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.data.repository.MainRepository
import com.example.cosmeticshop.utils.NetworkHelper
import com.example.cosmeticshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for the Product Type
 */
@HiltViewModel
class ProductTypeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val makeUpList = MutableLiveData<Resource<List<MakeUpProductsModel>>>()

    /**
     * Returns brand wise details of the product
     */
    fun fetchBrandWiseDetails(brandName: String?): LiveData<Resource<List<MakeUpProductsModel>>> {
        try {
            viewModelScope.launch {
                makeUpList.postValue(Resource.loading(null))
                if (networkHelper.isNetworkConnected()) {
                    mainRepository.getMakeupItemList(brandName).let {
                        if (it.isSuccessful) {
                            makeUpList.postValue(Resource.success(it.body()))
                            Log.e("Product", makeUpList.toString())
                        } else makeUpList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } else makeUpList.postValue(Resource.error("No internet connection", null))
            }

        }catch (e:Exception){
            makeUpList.postValue(Resource.error("No internet connection", null))
        }
        return makeUpList
    }
}