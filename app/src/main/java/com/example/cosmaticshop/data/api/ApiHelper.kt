package com.example.cosmaticshop.data.api

import com.example.cosmaticshop.data.model.MakeUpProductsModel

import retrofit2.Response

/**
 * Helper interface which returns responses from the APIs
 */
interface ApiHelper {
    // returns the list of make up products
    suspend fun getMakeUpProducts(): Response<List<MakeUpProductsModel>>

    // returns the list of make up products brand wise
    suspend fun getBrandWiseList(name: String): Response<List<MakeUpProductsModel>>

}