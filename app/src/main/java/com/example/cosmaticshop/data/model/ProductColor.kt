package com.example.cosmaticshop.data.model

import com.example.cosmaticshop.utils.Constants
import com.google.gson.annotations.SerializedName

/**
 * Data class which holds data related to the Product Colours
 */
data class ProductColor(
    @SerializedName("hex_value")
    var hexValue: String? = Constants.DEFAULT_STRING,
    @SerializedName("colour_name")
    var colourName: String? = Constants.DEFAULT_STRING
)