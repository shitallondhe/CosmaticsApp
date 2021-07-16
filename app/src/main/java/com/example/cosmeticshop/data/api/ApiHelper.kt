package com.example.cosmeticshop.data.api

import com.example.cosmeticshop.data.model.MakeUpProductsModel

import retrofit2.Response


interface ApiHelper {
    suspend fun getMakeUpProducts(): Response<List<MakeUpProductsModel>>

    suspend fun getBrandWiseList(name: String): Response<List<MakeUpProductsModel>>

}