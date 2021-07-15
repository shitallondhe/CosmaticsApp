package com.example.cosmaticshop.ui.productdetails.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.cosmaticshop.R
import com.example.cosmaticshop.data.model.MakeUpProductsModel
import com.example.cosmaticshop.databinding.ActivityProductDetailBinding
import com.example.cosmaticshop.ui.weblink.WebLinkActivity
import com.example.cosmaticshop.utils.Constants
import com.example.cosmaticshop.utils.Constants.Companion.WEB_LINK
import java.util.*

/**
 * ProductDetailActivity
 */
class ProductDetailActivity : AppCompatActivity() {
    var binding: ActivityProductDetailBinding? = null
    var makeUpProductsModel: MakeUpProductsModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        makeUpProductsModel = intent.getParcelableExtra(Constants.MAKE_UP_MODEL)
        binding?.txtBrandName?.text = makeUpProductsModel?.name?.capitalize(Locale.ROOT)
        Glide.with(binding?.imgProduct?.context!!)
            .load(makeUpProductsModel?.imageLink)
            .placeholder(R.drawable.ic_loading_dots)
            .error(R.drawable.ic_no_image)
            .into((binding!!.imgProduct))
        binding?.txtDescription?.text = makeUpProductsModel?.description
        binding?.txtPrice?.text = makeUpProductsModel?.priceSign+" "+makeUpProductsModel?.price
        /**
         * Handles the click on the product type
         */
        binding?.txtShowMore?.setOnClickListener {
            Intent().apply {
                intent =  Intent(this@ProductDetailActivity,WebLinkActivity::class.java)
                intent.putExtra(WEB_LINK, makeUpProductsModel?.websiteLink)
                startActivity(intent)
            }

        }




    }
}