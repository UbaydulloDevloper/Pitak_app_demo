package com.developer.sms

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.developer.sms.databinding.FragmentLanguageBinding
import models_date.Cache
import models_date.LocaleHelper


class Language : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    private lateinit var localeHelper: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLanguageBinding.inflate(layoutInflater)
        var language = ""


        binding.languageRussian.setOnClickListener {
            localeHelper = LocaleHelper.setLocale(binding.root.context, "ru")
            language(localeHelper.resources)

            language = "ru"
            binding.checkmarkRussian.visibility = View.VISIBLE
            binding.languageRussianTitle.setTextColor(Color.BLUE)
            binding.languageRussian.setBackgroundColor(Color.parseColor("#F2F2FF"))

            binding.checkmarkUzb.visibility = View.INVISIBLE
            binding.languageUzbTitle.setTextColor(Color.BLACK)
            binding.languageUzb.background = null

            binding.checkmarkEnglish.visibility = View.INVISIBLE
            binding.languageEnglishTitle.setTextColor(Color.BLACK)
            binding.languageEnglish.background = null

        }

        binding.languageUzb.setOnClickListener {
            localeHelper = LocaleHelper.setLocale(binding.root.context, "uz")
            language(localeHelper.resources)
            language = "uz"
            binding.checkmarkRussian.visibility = View.INVISIBLE
            binding.languageRussianTitle.setTextColor(Color.BLACK)
            binding.languageRussian.background = null

            binding.checkmarkUzb.visibility = View.VISIBLE
            binding.languageUzbTitle.setTextColor(Color.BLUE)
            binding.languageUzb.setBackgroundColor(Color.parseColor("#F2F2FF"))

            binding.checkmarkEnglish.visibility = View.INVISIBLE
            binding.languageEnglishTitle.setTextColor(Color.BLACK)
            binding.languageEnglish.background = null

        }

        binding.languageEnglish.setOnClickListener {
            localeHelper = LocaleHelper.setLocale(binding.root.context, "en")
            language(localeHelper.resources)
            language = "en"
            binding.checkmarkRussian.visibility = View.INVISIBLE
            binding.languageRussianTitle.setTextColor(Color.BLACK)
            binding.languageRussian.background = null

            binding.checkmarkUzb.visibility = View.INVISIBLE
            binding.languageUzbTitle.setTextColor(Color.BLACK)
            binding.languageUzb.background = null

            binding.checkmarkEnglish.visibility = View.VISIBLE
            binding.languageEnglishTitle.setTextColor(Color.BLUE)
            binding.languageEnglish.setBackgroundColor(Color.parseColor("#F2F2FF"))


        }

        binding.clickLanguage.setOnClickListener {
            if (language != "") {
                Cache().MySharedPreferencesLanguage(binding.root.context, language)
                findNavController().navigate(R.id.onBoarding)
            } else {
                Toast.makeText(binding.root.context, "Tilni tanlamadingiz", Toast.LENGTH_SHORT)
                    .show()
            }

        }



        return binding.root
    }

    private fun language(resources: Resources) {
        binding.titleLanguage.text = resources.getString(R.string.Title_language)
        binding.languageRussianTitle.text = resources.getString(R.string.language_ru_t)
        binding.languageRussianAbout.text = resources.getString(R.string.language_ru)
        binding.languageUzbTitle.text = resources.getString(R.string.language_uzb_t)
        binding.languageUzbAbout.text = resources.getString(R.string.language_uzb)
        binding.languageEnglishTitle.text = resources.getString(R.string.language_eng_t)
        binding.languageEnglishAbout.text = resources.getString(R.string.language_eng)
        binding.clickLanguage.text = resources.getString(R.string.language_click_btn)
    }
}