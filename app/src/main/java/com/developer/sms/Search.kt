package com.developer.sms

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.sms.databinding.FragmentSearchBinding
import models_date.LocaleHelper

class Search : Fragment() {
    lateinit var localeHelper: Context
    lateinit var binding: FragmentSearchBinding
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
        binding = FragmentSearchBinding.inflate(layoutInflater)
        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        language(localeHelper.resources)




        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.titleSearch.text = resources.getString(R.string.title_search)
    }

    companion object {

        fun newInstance(param1: String) = Search().apply {
            arguments = Bundle().apply {
                putString("key", param1)
            }
        }
    }
}