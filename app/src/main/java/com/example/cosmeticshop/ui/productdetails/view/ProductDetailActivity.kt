package com.example.cosmeticshop.ui.productdetails.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.cosmeticshop.R
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.databinding.ActivityProductDetailBinding
import com.example.cosmeticshop.ui.weblink.WebLinkActivity
import com.example.cosmeticshop.utils.Constants
import com.example.cosmeticshop.utils.Constants.Companion.WEB_LINK
import java.util.*

/**
 * ProductDetailActivity
 */
class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private  var makeUpProductsModel: MakeUpProductsModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeUpProductsModel = intent.getParcelableExtra(Constants.MAKE_UP_MODEL)
        binding.tvBrandName.text = makeUpProductsModel?.name
        Glide.with(binding.ivProduct.context!!)
            .load(makeUpProductsModel?.imageLink)
            .placeholder(R.drawable.ic_loading_dots)
            .error(R.drawable.ic_no_image)
            .into((binding.ivProduct))
        binding.tvDescription.text = makeUpProductsModel?.description
        val priceSign = makeUpProductsModel?.priceSign
        val productPrice =makeUpProductsModel?.price

        binding.tvPrice.text = ("$priceSign $productPrice")
        /**
         * Handles the click on the product type
         */
        binding.txtShowMore.setOnClickListener {
            Intent().apply {
                intent = Intent(this@ProductDetailActivity, WebLinkActivity::class.java)
                intent.putExtra(WEB_LINK, makeUpProductsModel?.websiteLink)
                startActivity(intent)
            }

        }

    }
}