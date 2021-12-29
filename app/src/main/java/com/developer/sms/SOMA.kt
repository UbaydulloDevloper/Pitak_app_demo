package com.developer.sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.sms.databinding.FragmentSOMABinding
import models_date.Cache

class SOMA : Fragment() {

    lateinit var binding: FragmentSOMABinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSOMABinding.inflate(layoutInflater)
        val language = Cache().lodeLanguage(binding.root.context)
        childFragmentManager.beginTransaction().replace(
            R.id.fragment_child, Search.newInstance(language)
        ).commit()

        binding.search.setOnClickListener {
            DefaultIcon()
            binding.search.setImageResource(R.drawable.ic_search_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Search
                    .newInstance(language)
            ).commit()


        }
        binding.orders.setOnClickListener {
            DefaultIcon()
            binding.orders.setImageResource(R.drawable.ic_location_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Orders
                    .newInstance(language)
            ).commit()

        }
        binding.message.setOnClickListener {
            DefaultIcon()
            binding.message.setImageResource(R.drawable.ic_message_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Messages
                    .newInstance(language)
            ).commit()
        }
        binding.account.setOnClickListener {
            DefaultIcon()
            binding.account.setImageResource(R.drawable.ic_account)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Account
                    .newInstance(language)
            ).commit()
        }
        return binding.root
    }

    fun DefaultIcon() {
        binding.search.setImageResource(R.drawable.ic_search_gray)
        binding.orders.setImageResource(R.drawable.ic_location_gray)
        binding.message.setImageResource(R.drawable.ic_message_gray)
        binding.account.setImageResource(R.drawable.ic_account_gray)

    }
}