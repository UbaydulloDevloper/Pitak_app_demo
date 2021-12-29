package com.developer.sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.developer.sms.databinding.ActivityMainBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.fragment_empty) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.home)
        if (SendSms(binding.root.context).signIn) {
            //graph.addArgument("argument", NavArgument)
            graph.startDestination = R.id.SOMA
            //or
        } else {
            graph.startDestination = R.id.splash
        }
        navHostFragment.navController.graph = graph
        lifecycleScope.launchWhenResumed {
            findNavController(R.id.fragment_empty).navigate(R.id.action_splash_to_SOMA)
        }*/

    }
    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragment_empty).navigateUp()
    }

}