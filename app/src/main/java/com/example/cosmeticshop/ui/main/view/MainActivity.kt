package com.example.cosmeticshop.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.databinding.ActivityMainBinding
import com.example.cosmeticshop.ui.main.adapter.MainAdapter
import com.example.cosmeticshop.ui.main.listners.MainInterface
import com.example.cosmeticshop.ui.main.viewmodel.MainViewModel
import com.example.cosmeticshop.ui.producttype.view.ProductTypeActivity
import com.example.cosmeticshop.utils.Constants.Companion.BRAND_NAME
import com.example.cosmeticshop.utils.Status
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainInterface {
    private val mainViewModel: MainViewModel by viewModels()
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

        //Click Listener on Retry Button
        binding.btnRetry.setOnClickListener {
            setupObserver()
        }

    }

    /**
     * Setups UI
     */
    private fun setupUI() {
        binding.recyclerViewList.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf(), mainInterface)
        binding.recyclerViewList.adapter = adapter

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
        binding.progressBar.visibility = View.GONE
        binding.recyclerViewList.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.GONE
    }

    /**
     * ProgressBar Visibility Visible
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerViewList.visibility = View.GONE
        binding.btnRetry.visibility = View.GONE

    }

    /**
     * RetryImage Visibility Visible
     */
    private fun showRetryImage(message: String?) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerViewList.visibility = View.GONE
        binding.btnRetry.visibility = View.VISIBLE

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
