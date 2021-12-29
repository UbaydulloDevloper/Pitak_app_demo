package com.developer.sms

import adapters.PagerListAdapter
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
import androidx.viewpager2.widget.ViewPager2
import com.developer.sms.databinding.FragmentOnBoardingBinding
import models_class.DataOnBoarding
import models_date.Cache
import models_date.LocaleHelper

class OnBoarding : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var localeHelper: Context
    private lateinit var pagerAdapter: PagerListAdapter
    private lateinit var list: ArrayList<DataOnBoarding>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        val language = Cache().lodeLanguage(binding.root.context)
        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        language(localeHelper.resources)
        listAdd(language)

        pagerAdapter = PagerListAdapter(list)

        binding.viewPager.adapter = pagerAdapter

        binding.onBoardSkip.setOnClickListener {
            binding.viewPager.currentItem = 3
            binding.onBoardSkip.visibility = View.INVISIBLE
            binding.onBoardNext.visibility = View.INVISIBLE
            binding.onBoardBegin.visibility = View.VISIBLE
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (binding.viewPager.currentItem == 2) {
                    binding.onBoardSkip.visibility = View.INVISIBLE
                    binding.onBoardNext.visibility = View.INVISIBLE
                    binding.onBoardBegin.visibility = View.VISIBLE
                } else {
                    binding.onBoardSkip.visibility = View.VISIBLE
                    binding.onBoardNext.visibility = View.VISIBLE
                    binding.onBoardBegin.visibility = View.GONE
                }
            }
        })
        binding.dotsIndicator.setViewPager(binding.viewPager)
        binding.onBoardNext.setOnClickListener {
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1

            if (binding.viewPager.currentItem == 2) {
                binding.onBoardSkip.visibility = View.INVISIBLE
                binding.onBoardNext.visibility = View.INVISIBLE
                binding.onBoardBegin.visibility = View.VISIBLE
            }
        }

        binding.onBoardBegin.setOnClickListener {
            findNavController().navigate(R.id.authentication)
        }


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun listAdd(count: String) {
        list = ArrayList()
        when (count) {
            "ru" -> {
                list.add(
                    DataOnBoarding(
                        R.drawable.llustration1,
                        "Легко найти водителя\n" +
                                "для поездки",
                        "Вам не обязательно ехать в питак чтобы найти подходящего вам водителя"
                    )
                )
                list.add(
                    DataOnBoarding(
                        R.drawable.illustration2, "Отправляйте почту\nбез хлопот",
                        "Водители сами придут по указанному адресу, заберут а затем доставят почту"
                    )
                )
                list.add(
                    DataOnBoarding(
                        R.drawable.illustration3, "Не тратьте зря драгоценное время",
                        "Вы можете не ехать в питак, потеряв\nв дороге кучу времени, денег и нервов"
                    )
                )
            }
            "uz" -> {
                list.add(
                    DataOnBoarding(
                        R.drawable.llustration1,
                        "Haydovchi topish oson\n" +
                                "sayohat uchun",
                        "Sizga mos keladigan haydovchini topish uchun pitakaga borish shart emas."
                    )
                )
                list.add(
                    DataOnBoarding(
                        R.drawable.illustration2, "Pochta yuborishda\n" +
                                "qiyinchilik yo'q",
                        "Haydovchilarning o'zlari ko'rsatilgan manzilga kelib, pochtani olib " +
                                "ketishadi va etkazib berishadi"
                    )
                )
                list.add(
                    DataOnBoarding(
                        R.drawable.illustration3, "Qimmatli vaqtni behuda sarflamang",
                        "Siz yutqazib pitakaga bormasligingiz mumkin\n" +
                                "yo'lda ko'p vaqt, pul va nervlar"
                    )
                )
            }
            "en" -> {
                list.add(
                    DataOnBoarding(
                        R.drawable.llustration1,
                        "Easily find a driver for your trip",
                        "You don't have to go to pitaka to find a driver that's right for you."
                    )
                )
                list.add(
                    DataOnBoarding(
                        R.drawable.illustration2, "Send mail\n" +
                                "no hassle",
                        "The drivers themselves will come to the specified address, pick up and then deliver the mail"
                    )
                )
                list.add(
                    DataOnBoarding(
                        R.drawable.illustration3, "Don't waste precious time",
                        "You may not go to pitaka by losing\n" +
                                "a lot of time, money and nerves on the road"
                    )
                )
            }
        }


    }


    private fun language(resources: Resources) {
        binding.onBoardSkip.text = resources.getString(R.string.skip_onboard)
        binding.onBoardNext.text = resources.getString(R.string.next_onboard)
        binding.onBoardBegin.text = resources.getString(R.string.click_onboard)
    }

}