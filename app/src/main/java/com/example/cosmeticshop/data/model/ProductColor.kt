package com.example.cosmeticshop.data.model

import com.example.cosmeticshop.utils.Constants
import com.google.gson.annotations.SerializedName

data class ProductColor(
    @SerializedName("hex_value")
    var hexValue: String? = Constants.DEFAULT_STRING,
    @SerializedName("colour_name")
    var colourName: String? = Constants.DEFAULT_STRING
)