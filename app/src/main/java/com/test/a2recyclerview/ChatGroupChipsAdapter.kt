package com.test.a2recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.a2recyclerview.databinding.ItemSelectedBinding

class ChatGroupChipsAdapter(
    private val onItemClosed: (Contact, Int) -> Unit
) :
    ListAdapter<Contact, ChatGroupChipsAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Contact?>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
            oldItem == newItem
    }) {
    inner class ViewHolder(private val binding: ItemSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Contact?) {
            with(binding) {
                chipContact.text = item?.name
                chipContact.isCheckable = false
                chipContact.setOnCloseIconClickListener {
                    item?.let { contact -> onItemClosed.invoke(contact, adapterPosition) }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemSelectedBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}