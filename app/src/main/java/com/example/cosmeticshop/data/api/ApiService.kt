package com.example.cosmeticshop.data.api

import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.utils.Constants.Companion.GET_BRAND_ITEMS
import com.example.cosmeticshop.utils.Constants.Companion.GET_MAKEUP_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API Service interface which returns responses from the APIs
 */
interface ApiService {

    //MakeUp Product List
    @GET(GET_MAKEUP_PRODUCTS)
    suspend fun getMakeUpProducts(): Response<List<MakeUpProductsModel>>

    //MakeUp Product List Based on Brand
    @GET(GET_BRAND_ITEMS)
    suspend fun getBrandWiseItems(@Query("brand") brand: String): Response<List<MakeUpProductsModel>>
}