package com.example.cosmaticshop.ui.producttype.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmaticshop.data.model.MakeUpProductsModel
import com.example.cosmaticshop.databinding.ActivityProductTypeBinding
import com.example.cosmaticshop.ui.productdetails.view.ProductDetailActivity
import com.example.cosmaticshop.ui.producttype.adapter.ProductTypeAdapter
import com.example.cosmaticshop.ui.producttype.listners.ProductTypeInterface
import com.example.cosmaticshop.ui.producttype.viewmodel.ProductTypeViewModel
import com.example.cosmaticshop.utils.Constants.Companion.BRAND_NAME
import com.example.cosmaticshop.utils.Constants.Companion.MAKE_UP_MODEL
import com.example.cosmaticshop.utils.Status
import dagger.hilt.android.AndroidEntryPoint

/**
 * Product Type Activity
 */
@AndroidEntryPoint
class ProductTypeActivity : AppCompatActivity(), ProductTypeInterface {
    var binding: ActivityProductTypeBinding? = null
    var productTypeInterface: ProductTypeInterface? = null
    private var productTypeAdapter: ProductTypeAdapter? = null
    var brandName: String = ""
    private val productTypeViewModel: ProductTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductTypeBinding.inflate(getLayoutInflater())
        setContentView(binding?.getRoot())
        productTypeInterface = this
        brandName = intent?.getStringExtra(BRAND_NAME).toString()
        binding?.txtBrandName?.text = brandName

        setupUI()
        setupObserver(brandName)

        //Click listner on Retry Button
        binding?.btnRetry?.setOnClickListener {
            setupObserver(brandName)
        }

    }

    /**
     * Setups Observers
     */
    private fun setupObserver(brandName: String?) {
        productTypeViewModel.fetchBrandWiseDetails(brandName).observe(this,  {
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

    /**
     * Renders the list of make up products
     */
    private fun renderList(makeUpList: List<MakeUpProductsModel>) {
        productTypeAdapter?.addData(makeUpList)
        productTypeAdapter?.notifyDataSetChanged()
    }

    /**
     * RecyclerView Visibility Visible
     */
    private fun showRecyclerView() {
        binding?.progressBar?.visibility = View.GONE
        binding?.recyclerProductList?.visibility = View.VISIBLE
        binding?.btnRetry?.visibility = View.GONE
    }

    /**
     * ProgressBar Visibility Visible
     */
    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.recyclerProductList?.visibility = View.GONE
        binding?.btnRetry?.visibility = View.GONE

    }

    /**
     * RetryImage Visibility Visible
     */
    private fun showRetryImage(message: String?) {
        binding?.progressBar?.visibility = View.GONE
        binding?.recyclerProductList?.visibility = View.GONE
        binding?.btnRetry?.visibility = View.VISIBLE
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }



    /**
     * Setups the UI
     */
    private fun setupUI() {
        binding?.recyclerProductList?.layoutManager = LinearLayoutManager(this)
        productTypeAdapter = ProductTypeAdapter(arrayListOf(), productTypeInterface)
        binding?.recyclerProductList?.adapter = productTypeAdapter
    }

    /**
     * Handles the click on the product type
     */
    override fun clickOnProductType(makeUpProductsModel: MakeUpProductsModel) {
        Intent().apply {
            intent = Intent(this@ProductTypeActivity, ProductDetailActivity::class.java)
            intent.putExtra(MAKE_UP_MODEL, makeUpProductsModel)
            startActivity(intent)
        }
    }
}