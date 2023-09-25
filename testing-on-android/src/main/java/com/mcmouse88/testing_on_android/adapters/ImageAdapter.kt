package com.mcmouse88.testing_on_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mcmouse88.testing_on_android.databinding.ItemImageBinding
import javax.inject.Inject

class ImageAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    private var onItemClickListener: ((String) -> Unit)? = null

    var images: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            binding = ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        holder.binding.apply {
            glide.load(url).into(ivShoppingImage)
            root.setOnClickListener {
                onItemClickListener?.invoke(url)
            }
        }
    }

    override fun getItemCount(): Int = images.size

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    class ImageViewHolder(
        val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root)
}