package com.example.cosmeticshop.data.api

import com.example.cosmeticshop.data.model.MakeUpProductsModel
import retrofit2.Response
import javax.inject.Inject

/**
 * API Helper class which returns responses from the APIs
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMakeUpProducts(): Response<List<MakeUpProductsModel>> =
        apiService.getMakeUpProducts()

    override suspend fun getBrandWiseList(name: String): Response<List<MakeUpProductsModel>> =
        apiService.getBrandWiseItems(name)
}