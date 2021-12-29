package com.developer.sms

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.developer.sms.databinding.FragmentSplashBinding
import models_date.Cache

class Splash : Fragment() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        var chach = Cache().lodedate(binding.root.context)
        Toast.makeText(binding.root.context, chach, Toast.LENGTH_LONG).show()
        val hendler = Handler()
        hendler.postDelayed(Runnable { //Do something after delay
            if (chach == "true") {
                findNavController().navigate(R.id.SOMA)
            } else {
                findNavController().navigate(R.id.language)
            }
        }, 3000)

        return binding.root
    }

    companion object {
        fun newInstance() =
            Splash()
    }
}