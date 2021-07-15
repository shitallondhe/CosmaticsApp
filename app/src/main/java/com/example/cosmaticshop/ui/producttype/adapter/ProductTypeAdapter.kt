package com.example.cosmaticshop.ui.producttype.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cosmaticshop.R
import com.example.cosmaticshop.data.model.MakeUpProductsModel
import com.example.cosmaticshop.databinding.ItemProductTypeBinding
import com.example.cosmaticshop.ui.producttype.listners.ProductTypeInterface

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
                txtProductType.text = makeup.productType?.capitalize()
                txtProductDesc.text = makeup.description
                //number of line you want to short
                txtProductDesc.setShowingLine(4)

                txtProductDesc.addShowMoreText("Continue")
                txtProductDesc.addShowLessText("Less")

                txtProductDesc.setShowMoreColor(Color.RED) // or other color
                txtProductDesc.setShowLessTextColor(Color.RED)

                val imgLink = makeup.imageLink
                Glide.with(imgProduct.context)
                    .load(imgLink)
                    .placeholder(R.drawable.ic_loading_dots)
                    .error(R.drawable.ic_no_image)
                    .into(imgProduct)
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