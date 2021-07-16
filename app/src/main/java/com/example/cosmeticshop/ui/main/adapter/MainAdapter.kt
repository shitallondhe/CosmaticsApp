package com.example.cosmeticshop.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cosmeticshop.R
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.databinding.ItemLayoutBinding
import com.example.cosmeticshop.ui.main.listners.MainInterface
import kotlin.collections.ArrayList

/**
 * Main Adapter class
 */
class MainAdapter(
    private val makeUpList: ArrayList<MakeUpProductsModel>,
    var mainInterface: MainInterface? = null
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    inner class DataViewHolder(var binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DefaultLocale")
        fun bind(user: MakeUpProductsModel) {
            binding.apply {
                tvBrandName.text = user.brand
                val imgLink = user.imageLink
                Glide.with(ivProduct.context)
                    .load(imgLink)
                    .placeholder(R.drawable.ic_loading_dots)
                    .into(ivProduct)
            }

            binding.container.setOnClickListener {
                mainInterface?.clickOnBrand(user.brand.toString())

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemLayoutBinding>(
                inflater,
                R.layout.item_layout,
                parent,
                false
            )

        return DataViewHolder(binding)

    }

    override fun getItemCount(): Int = makeUpList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(makeUpList[position])

    fun addData(list: List<MakeUpProductsModel>) {
        makeUpList.addAll(list)
    }
}