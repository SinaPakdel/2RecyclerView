package com.test.a2recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.a2recyclerview.databinding.ItemContactBinding

class ChatGroupMainAdapter(
    private val onItemClick: (List<Contact>, Int) -> Unit
) :
    ListAdapter<Contact, ChatGroupMainAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
            oldItem == newItem
    }) {
    private val selectedItems = mutableListOf<Contact>()

    fun itemRemove(contact: Contact) {
        if (selectedItems.contains(contact)) selectedItems.remove(contact)
    }

    inner class ViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: Contact
        private var isSelected = false

        fun bind(contact: Contact) {
            item = contact
            isSelected = selectedItems.contains(item)
            binding.imgAdd.isVisible = isSelected

            binding.root.setOnClickListener {
                toggleSelection()
            }

            with(binding) {
                tvContactName.text = item.name
            }
        }

        private fun toggleSelection() {
            isSelected = !isSelected
            if (isSelected) selectedItems.add(item) else selectedItems.remove(item)
            binding.imgAdd.isVisible = isSelected
            onItemClick.invoke(selectedItems, adapterPosition)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}