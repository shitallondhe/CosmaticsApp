package com.example.cosmeticshop.data.repository

import com.example.cosmeticshop.data.api.ApiHelper
import javax.inject.Inject

/**
 * MainRepository class which is responsible to construct the API Helper
 */
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getMakeUpProducts() = apiHelper.getMakeUpProducts()

    suspend fun getMakeupItemList(brandName: String?) = apiHelper.getBrandWiseList(brandName.toString())
}