package com.example.cosmaticshop.ui.main.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cosmaticshop.R
import com.example.cosmaticshop.data.model.MakeUpProductsModel
import com.example.cosmaticshop.databinding.ItemLayoutBinding

import com.example.cosmaticshop.ui.main.listners.MainInterface
import kotlin.collections.ArrayList

/**
 * Main Adapter class
 */
class MainAdapter(
    private val users: ArrayList<MakeUpProductsModel>,
    var mainInterface: MainInterface? = null
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    inner class DataViewHolder(var binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DefaultLocale")
        fun bind(user: MakeUpProductsModel) {
            binding.apply {
                txtBrandName.text = user.brand?.capitalize()
                val imgLink = user.imageLink
                Glide.with(imgAvtar.context)
                    .load(imgLink)
                    .placeholder(R.drawable.ic_loading_dots)
                    .error(R.drawable.ic_no_image)
                    .into(imgAvtar)
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

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<MakeUpProductsModel>) {
        users.addAll(list)
    }
}