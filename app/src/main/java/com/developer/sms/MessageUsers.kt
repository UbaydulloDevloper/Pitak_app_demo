package com.developer.sms

import adapters.MessageAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developer.sms.databinding.FragmentMessageUsersBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import models_class.MessageUser
import models_class.Users
import models_date.LocaleHelper
import java.text.SimpleDateFormat
import java.util.*

class MessageUsers : Fragment() {
    lateinit var binding: FragmentMessageUsersBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var messageAdapter: MessageAdapter
    lateinit var reference: DatabaseReference
    lateinit var localeHelper: Context

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageUsersBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("massage")

        val user = arguments?.getSerializable("user") as Users
        val language = arguments?.getString("key")

        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        language(localeHelper.resources)

        binding.nameUsers.text = user.name
        binding.titleMessage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.sendBtn.setOnClickListener {
            val m = binding.text.text.toString()
            if (m != "") {
                val simpleDateFormat = SimpleDateFormat("HH:mm")
                val date = simpleDateFormat.format(Date())
                val massage = MessageUser(firebaseAuth.currentUser?.uid, user.idToken, date, m)

                val key = reference.push().key
                reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.idToken!!}/$key")
                    .setValue(massage)

                reference.child("${user.idToken}/messages/${firebaseAuth.currentUser?.uid}/$key")
                    .setValue(massage)

                binding.text.text = null

            }
            reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.idToken}")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<MessageUser>()
                        val children = snapshot.children
                        for (child in children) {
                            val value = child.getValue(MessageUser::class.java)
                            if (value != null) {
                                list.add(value)
                            }
                        }
                        messageAdapter = MessageAdapter(list, firebaseAuth.currentUser?.uid!!)
                        binding.recycleView.adapter = messageAdapter
                        binding.recycleView.scrollToPosition(list.size - 1);
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.text.hint = resources.getString(R.string.write_message)
    }

    companion object {
        fun newInstance() = MessageUsers()
    }
}