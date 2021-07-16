package com.example.cosmeticshop.ui.producttype.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.databinding.ActivityProductTypeBinding
import com.example.cosmeticshop.ui.productdetails.view.ProductDetailActivity
import com.example.cosmeticshop.ui.producttype.adapter.ProductTypeAdapter
import com.example.cosmeticshop.ui.producttype.listners.ProductTypeInterface
import com.example.cosmeticshop.ui.producttype.viewmodel.ProductTypeViewModel
import com.example.cosmeticshop.utils.Constants.Companion.BRAND_NAME
import com.example.cosmeticshop.utils.Constants.Companion.MAKE_UP_MODEL
import com.example.cosmeticshop.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductTypeActivity : AppCompatActivity(), ProductTypeInterface {
    lateinit var binding: ActivityProductTypeBinding
    private var productTypeInterface: ProductTypeInterface? = null
    private var productTypeAdapter: ProductTypeAdapter? = null
    var brandName: String = ""
    private val productTypeViewModel: ProductTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productTypeInterface = this
        brandName = intent?.getStringExtra(BRAND_NAME).toString()
        binding.tvBrandName.text = brandName

        setupUI()
        setupObserver(brandName)

        binding.btnRetry.setOnClickListener {
            setupObserver(brandName)
        }

    }

    private fun setupObserver(brandName: String?) {
        productTypeViewModel.fetchBrandWiseDetails(brandName).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { makeUpList -> renderList(makeUpList) }
                    showRecyclerView()
                }
                Status.LOADING -> {
                    showProgressBar()
                }
                Status.ERROR -> {
                    //Handle Error
                    showRetryImage(it.message)

                }
            }
        })

    }

    private fun renderList(makeUpList: List<MakeUpProductsModel>) {
        productTypeAdapter?.addData(makeUpList)
        productTypeAdapter?.notifyDataSetChanged()
    }

    private fun showRecyclerView() {
        binding.progressBarLoading.visibility = View.GONE
        binding.recyclerViewProductList.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBarLoading.visibility = View.VISIBLE
        binding.recyclerViewProductList.visibility = View.GONE
        binding.btnRetry.visibility = View.GONE

    }

    private fun showRetryImage(message: String?) {
        binding.progressBarLoading.visibility = View.GONE
        binding.recyclerViewProductList.visibility = View.GONE
        binding.btnRetry.visibility = View.VISIBLE

    }

    private fun setupUI() {
        binding.recyclerViewProductList.layoutManager = LinearLayoutManager(this)
        productTypeAdapter = ProductTypeAdapter(arrayListOf(), productTypeInterface)
        binding.recyclerViewProductList.adapter = productTypeAdapter
    }


    override fun clickOnProductType(makeUpProductsModel: MakeUpProductsModel) {
        Intent().apply {
            intent = Intent(this@ProductTypeActivity, ProductDetailActivity::class.java)
            intent.putExtra(MAKE_UP_MODEL, makeUpProductsModel)
            startActivity(intent)
        }
    }
}