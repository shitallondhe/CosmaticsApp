package com.example.cosmaticshop.data.model

import android.os.Parcel
import android.os.Parcelable
import com.example.cosmaticshop.utils.Constants.Companion.DEFAULT_INT
import com.example.cosmaticshop.utils.Constants.Companion.DEFAULT_STRING
import com.google.gson.annotations.SerializedName

/**
 * Data class which holds data related to the Make up products
 */
data class MakeUpProductsModel(

    @SerializedName("id")
    val id: Int? = DEFAULT_INT,

    @SerializedName("brand")
    var brand: String? = DEFAULT_STRING,

    @SerializedName("name")
    var name: String? = DEFAULT_STRING,

    @SerializedName("price")
    var price: String? = DEFAULT_STRING,


    @SerializedName("price_sign")
    var priceSign: String? = DEFAULT_STRING,


    @SerializedName("currency")
    var currency: String? = DEFAULT_STRING,


    @SerializedName("image_link")
    var imageLink: String? = null,


    @SerializedName("product_link")
    var productLink: String? = DEFAULT_STRING,


    @SerializedName("website_link")
    var websiteLink: String? = DEFAULT_STRING,


    @SerializedName("description")
    var description: String? = DEFAULT_STRING,


    @SerializedName("rating")
    var rating: Any? = null,


    @SerializedName("category")
    var category: String? = DEFAULT_STRING,


    @SerializedName("product_type")
    var productType: String? = null,


    @SerializedName("tag_list")
    var tagList: List<String>? = null,


    @SerializedName("created_at")
    var createdAt: String? = DEFAULT_STRING,


    @SerializedName("updated_at")
    var updatedAt: String? = DEFAULT_STRING,


    @SerializedName("product_api_url")
    var productApiUrl: String? = DEFAULT_STRING,


    @SerializedName("api_featured_image")
    var apiFeaturedImage: String? = DEFAULT_STRING,

    @SerializedName("product_colors")
    var productColors: List<ProductColor>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()


    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(brand)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(priceSign)
        parcel.writeString(currency)
        parcel.writeString(imageLink)
        parcel.writeString(productLink)
        parcel.writeString(websiteLink)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeString(productType)
        parcel.writeStringList(tagList)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(productApiUrl)
        parcel.writeString(apiFeaturedImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MakeUpProductsModel> {
        override fun createFromParcel(parcel: Parcel): MakeUpProductsModel {
            return MakeUpProductsModel(parcel)
        }

        override fun newArray(size: Int): Array<MakeUpProductsModel?> {
            return arrayOfNulls(size)
        }
    }
}