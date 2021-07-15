package com.example.cosmaticshop.utils

class Constants {
    companion object {
        //Base Url
        const val BASE_URL = "http://makeup-api.herokuapp.com/api/"

        //Api End Points
        const val GET_MAKEUP_PRODUCTS = "v1/products.json"
        const val GET_BRAND_ITEMS = "v1/products.json?brand={brand)"

        //App Constants
        const val DEFAULT_STRING = " "
        const val DEFAULT_INT = 0

        //intent Constants
        const val BRAND_NAME = "brand_name"
        const val MAKE_UP_MODEL = "make_up_model"
        const val WEB_LINK = "web_link"

    }
}