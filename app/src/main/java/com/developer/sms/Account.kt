package com.developer.sms

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.sms.databinding.FragmentAccountBinding
import models_date.LocaleHelper

class Account : Fragment() {
    lateinit var binding: FragmentAccountBinding
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
        binding = FragmentAccountBinding.inflate(layoutInflater)
        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        language(localeHelper.resources)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.titleAccount.text = resources.getString(R.string.title_account)
    }

    companion object {
        fun newInstance(language: String) = Account().apply {
            arguments = Bundle().apply {
                putString("key", language)
            }
        }
    }
}