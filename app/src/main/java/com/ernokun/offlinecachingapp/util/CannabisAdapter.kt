package com.ernokun.offlinecachingapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ernokun.offlinecachingapp.data.model.Cannabis
import com.ernokun.offlinecachingapp.databinding.ItemCannabisBinding

class CannabisAdapter() : ListAdapter<Cannabis, CannabisAdapter.CannabisViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CannabisViewHolder {
        val binding = ItemCannabisBinding.inflate(LayoutInflater.from(parent.context))
        return CannabisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CannabisViewHolder, position: Int) {
        val cannabisItem: Cannabis = getItem(position)
        holder.bind(cannabisItem)
    }

    inner class CannabisViewHolder(private val binding: ItemCannabisBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cannabis: Cannabis) {
            binding.apply {
                cannabinoid.text = cannabis.cannabinoid
                category.text = cannabis.category
                healthBenefit.text = cannabis.healthBenefit
                medicalUse.text = cannabis.medicalUse
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Cannabis>() {
        override fun areItemsTheSame(oldItem: Cannabis, newItem: Cannabis): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cannabis, newItem: Cannabis): Boolean {
            return oldItem.cannabinoid == newItem.cannabinoid
                    && oldItem.category == newItem.category
                    && oldItem.healthBenefit == newItem.healthBenefit
                    && oldItem.medicalUse == newItem.medicalUse
        }
    }
}
