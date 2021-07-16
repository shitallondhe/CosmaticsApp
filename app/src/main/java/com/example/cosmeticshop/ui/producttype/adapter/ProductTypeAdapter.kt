package com.example.cosmeticshop.ui.producttype.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cosmeticshop.R
import com.example.cosmeticshop.data.model.MakeUpProductsModel
import com.example.cosmeticshop.databinding.ItemProductTypeBinding
import com.example.cosmeticshop.ui.producttype.listners.ProductTypeInterface

/**
 * Product Type Adapter
 */
class ProductTypeAdapter(
    private val makeupList: ArrayList<MakeUpProductsModel>,
    var productTypeInterface: ProductTypeInterface? = null
) : RecyclerView.Adapter<ProductTypeAdapter.DataViewHolder>() {

    inner class DataViewHolder(var binding: ItemProductTypeBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @SuppressLint("DefaultLocale")
        fun bind(makeup: MakeUpProductsModel) {

            binding.apply {
                tvProductName.text = makeup.productType
                tvProductDescription.text = makeup.description
                //number of line you want to short
                tvProductDescription.setShowingLine(4)

                tvProductDescription.addShowMoreText("Continue")
                tvProductDescription.addShowLessText("Less")

                tvProductDescription.setShowMoreColor(Color.RED) // or other color
                tvProductDescription.setShowLessTextColor(Color.RED)

                val imgLink = makeup.imageLink
                Glide.with(ivProduct.context)
                    .load(imgLink)
                    .placeholder(R.drawable.ic_loading_dots)
                    .error(R.drawable.ic_no_image)
                    .into(ivProduct)
            }

            binding.cardContainer.setOnClickListener {
                productTypeInterface?.clickOnProductType(makeup)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemProductTypeBinding>(
                inflater,
                R.layout.item_product_type,
                parent,
                false
            )

        return DataViewHolder(binding)

    }

    override fun getItemCount(): Int = makeupList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(makeupList[position])

    fun addData(list: List<MakeUpProductsModel>) {
        makeupList.addAll(list)
    }
}