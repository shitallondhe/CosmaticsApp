package com.example.cosmaticshop.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cosmaticshop.data.model.MakeUpProductsModel
import com.example.cosmaticshop.databinding.ActivityMainBinding
import com.example.cosmaticshop.ui.main.adapter.MainAdapter
import com.example.cosmaticshop.ui.main.listners.MainInterface
import com.example.cosmaticshop.ui.main.viewmodel.MainViewModel
import com.example.cosmaticshop.ui.producttype.view.ProductTypeActivity
import com.example.cosmaticshop.utils.Constants.Companion.BRAND_NAME
import com.example.cosmaticshop.utils.Status
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main Activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainInterface {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private var binding: ActivityMainBinding? = null
    var mainInterface: MainInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        mainInterface = this
        setupUI()
        setupObserver()

        //Click listner on Retry Button
        binding?.btnRetry?.setOnClickListener {
            setupObserver()
        }

    }

    /**
     * Setups UI
     */
    private fun setupUI() {
        binding?.recyclerView?.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf(), mainInterface)
        binding?.recyclerView?.adapter = adapter

    }

    /**
     * Setups observers
     */
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
                    //Handle Error
                    showRetryImage(it.message)


                }
            }
        })
    }

    /**
     * RecyclerView Visibility Visible
     */
    private fun showRecyclerView() {
        binding?.progressBar?.visibility = View.GONE
        binding?.recyclerView?.visibility = View.VISIBLE
        binding?.btnRetry?.visibility = View.GONE
    }

    /**
     * ProgressBar Visibility Visible
     */
    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.recyclerView?.visibility = View.GONE
        binding?.btnRetry?.visibility = View.GONE

    }

    /**
     * RetryImage Visibility Visible
     */
    private fun showRetryImage(message: String?) {
        binding?.progressBar?.visibility = View.GONE
        binding?.recyclerView?.visibility = View.GONE
        binding?.btnRetry?.visibility = View.VISIBLE

       // Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Renders the list of items
     */
    private fun renderList(users: List<MakeUpProductsModel>) {
        val data = users.distinctBy { it.brand }
        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }

    /**
     * Click listener
     */
    override fun clickOnBrand(brandName: String) {
        Intent().apply {
            intent = Intent(this@MainActivity, ProductTypeActivity::class.java)
            intent.putExtra(BRAND_NAME, brandName)
            startActivity(intent)
        }

    }


}
