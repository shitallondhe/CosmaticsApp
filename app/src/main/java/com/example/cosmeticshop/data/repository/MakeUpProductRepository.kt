package com.example.cosmeticshop.data.repository

import com.example.cosmeticshop.data.api.ApiHelper
import javax.inject.Inject

class MakeUpProductRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getMakeUpProducts() = apiHelper.getMakeUpProducts()

    suspend fun getMakeupItemList(brandName: String?) = apiHelper.getBrandWiseList(brandName.toString())
}