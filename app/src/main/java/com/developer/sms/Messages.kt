package com.developer.sms

import adapters.RvUserMessageAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.developer.sms.databinding.FragmentMessagesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import models_class.Users
import models_date.LocaleHelper

class Messages : Fragment() {
    lateinit var binding: FragmentMessagesBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var reference: DatabaseReference
    lateinit var arrayList: ArrayList<Users>
    lateinit var rvUserMessageAdapter: RvUserMessageAdapter
    lateinit var localeHelper: Context


    var language = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            language = it.getString("key")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagesBinding.inflate(layoutInflater)
        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        firebaseDatabase = FirebaseDatabase.getInstance()
        language(localeHelper.resources)
        auth = FirebaseAuth.getInstance()

        val currentUsers = auth.currentUser

        reference = firebaseDatabase.getReference("Users")

        val email = currentUsers?.email
        val name = currentUsers?.displayName
        val image = currentUsers?.photoUrl.toString()
        val idtoken = currentUsers?.uid

        val user = Users(idtoken, name, image, email)


        reference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList = ArrayList()
                val children = snapshot.children
                val filterList = arrayListOf<Users>()
                for (child in children) {
                    val value = child.getValue(Users::class.java)
                    if (value != null && idtoken != value.idToken) {
                        arrayList.add(value)
                    }
                    if (value != null && value.idToken == idtoken) {
                        filterList.add(value)
                    }
                }

                if (filterList.isEmpty()) {
                    reference.child(idtoken!!).setValue(user)
                }

                rvUserMessageAdapter =
                    RvUserMessageAdapter(arrayList, object : RvUserMessageAdapter.Click {
                        override fun itemClick(user: Users) {
                             findNavController().navigate(R.id.messageUsers)
                        }
                    })
                binding.recycleViewUserMessage.adapter = rvUserMessageAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.titleMessages.text = resources.getString(R.string.title_messages)
    }

    companion object {

        fun newInstance(language: String) = Messages().apply {
            arguments = Bundle().apply {
                putString("key", language)
            }
        }
    }
}