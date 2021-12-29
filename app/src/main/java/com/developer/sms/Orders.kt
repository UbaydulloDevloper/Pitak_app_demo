package com.developer.sms

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.sms.databinding.FragmentOrdersBinding
import models_date.LocaleHelper

class Orders : Fragment() {
    lateinit var binding: FragmentOrdersBinding
    var language = ""
    lateinit var localeHelper: Context
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
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        language(localeHelper.resources)




        return binding.root
    }
    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.titleOrders.text = resources.getString(R.string.title_orders)
    }

    companion object {

        fun newInstance(language:String) = Orders().apply {
            arguments = Bundle().apply {
                putString("key", language)
            }
        }
    }
}