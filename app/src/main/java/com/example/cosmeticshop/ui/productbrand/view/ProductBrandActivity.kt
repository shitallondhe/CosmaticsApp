package com.example.cosmeticshop.ui.productbrand.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.databinding.ActivityMainBinding
import com.example.cosmeticshop.ui.productbrand.adapter.MainAdapter
import com.example.cosmeticshop.ui.productbrand.listners.MainInterface
import com.example.cosmeticshop.ui.productbrand.viewmodel.ProductBrandViewModel
import com.example.cosmeticshop.ui.producttype.view.ProductTypeActivity
import com.example.cosmeticshop.utils.Constants.Companion.BRAND_NAME
import com.example.cosmeticshop.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductBrandActivity : AppCompatActivity(), MainInterface {
    private val mainViewModel: ProductBrandViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding
    private var mainInterface: MainInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainInterface = this
        setupUI()
        setupObserver()
        binding.btnRetry.setOnClickListener {
            setupObserver()
        }

    }

    private fun setupUI() {
        binding.recyclerViewList.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf(), mainInterface)
        binding.recyclerViewList.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.fetchMakeUpProducts().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    showRecyclerView()
                    it.data?.let { users -> renderList(users) }
                }
                Status.LOADING -> {
                    showProgressBar()
                }
                Status.ERROR -> {

                    showRetryImage(it.message)

                }
            }
        })
    }

    private fun showRecyclerView() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerViewList.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerViewList.visibility = View.GONE
        binding.btnRetry.visibility = View.GONE

    }

    private fun showRetryImage(message: String?) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerViewList.visibility = View.GONE
        binding.btnRetry.visibility = View.VISIBLE

    }

    private fun renderList(users: List<MakeUpProductsModel>) {
        val data = users.distinctBy { it.brand }
        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }

    override fun clickOnBrand(brandName: String) {
        Intent().apply {
            intent = Intent(this@ProductBrandActivity, ProductTypeActivity::class.java)
            intent.putExtra(BRAND_NAME, brandName)
            startActivity(intent)
        }

    }


}
