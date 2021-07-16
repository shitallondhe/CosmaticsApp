package com.example.cosmeticshop.data.api

import com.example.cosmeticshop.data.model.MakeUpProductsModel
import retrofit2.Response
import javax.inject.Inject


class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMakeUpProducts(): Response<List<MakeUpProductsModel>> =
        apiService.getMakeUpProducts()

    override suspend fun getBrandWiseList(name: String): Response<List<MakeUpProductsModel>> =
        apiService.getBrandWiseItems(name)
}