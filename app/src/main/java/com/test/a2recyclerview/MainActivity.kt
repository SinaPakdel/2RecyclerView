package com.test.a2recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.a2recyclerview.databinding.ActivityMainBinding
import com.test.a2recyclerview.databinding.ItemSelectedBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatGroupMainAdapter: ChatGroupMainAdapter
    private lateinit var chatGroupChipsAdapter: ChatGroupChipsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fakeContactList = listOf(
            Contact(1, "John Doe", "1234567890"),
            Contact(2, "Jane Smith", "9876543210"),
            Contact(3, "Alice Johnson", "5555555555"),
            Contact(4, "Bob Williams", "9999999999")
        )
        chatGroupChipsAdapter= ChatGroupChipsAdapter { contact: Contact, i: Int ->
            chatGroupMainAdapter.itemRemove(contact)
            chatGroupChipsAdapter.notifyDataSetChanged()
            chatGroupMainAdapter.notifyDataSetChanged()

        }
        chatGroupMainAdapter =
            ChatGroupMainAdapter { contacts: List<Contact>, i: Int ->
                chatGroupChipsAdapter.submitList(contacts)
                chatGroupChipsAdapter.notifyDataSetChanged()
            }
        chatGroupMainAdapter.submitList(fakeContactList)
        binding.recyclerViewSelected.adapter=chatGroupChipsAdapter
        binding.recyclerViewMain.adapter = chatGroupMainAdapter
    }
}