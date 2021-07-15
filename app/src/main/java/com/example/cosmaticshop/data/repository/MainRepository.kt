package com.example.cosmaticshop.data.repository

import com.example.cosmaticshop.data.api.ApiHelper
import javax.inject.Inject

/**
 * MainRepository class which is responsible to construct the API Helper
 */
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getMakeUpProducts() = apiHelper.getMakeUpProducts()

    suspend fun getMakeupItemList(brandName: String?) =
        apiHelper.getBrandWiseList(brandName.toString())
}