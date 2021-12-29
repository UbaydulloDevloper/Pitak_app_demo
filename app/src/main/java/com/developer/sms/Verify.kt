package com.developer.sms

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.developer.sms.databinding.FragmentVerifyBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import models_date.LocaleHelper
import models_date.SingIn
import models_date.Cache
import java.util.concurrent.TimeUnit

class Verify : Fragment() {
    lateinit var binding: FragmentVerifyBinding
    lateinit var localeHelper: Context
    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    var next: Boolean? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(layoutInflater)
        onResume()
        val language = Cache().lodeLanguage(binding.root.context)
        val number = arguments?.getString("number")

        localeHelper = LocaleHelper.setLocale(binding.root.context, language)
        language(localeHelper.resources)
        auth = FirebaseAuth.getInstance()

        sendVerificationCode(number!!)

        binding.clickCleanVerify.setOnClickListener {
            binding.codePhoneVerify.text = null
            onResume()
        }

        binding.clickMessageVerify.setOnClickListener {
            if (next == true) {
                findNavController().navigate(R.id.SOMA)
            }
        }

        binding.resetCode.setOnClickListener {
            sendVerificationCode(number)
        }
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        if (binding.codePhoneVerify.text.isEmpty()) {
            binding.clickCleanVerify.visibility = View.GONE
            binding.line1.setBackgroundColor(Color.parseColor("#CCCCCC"))
            binding.clickMessageVerify.setBackgroundResource(R.drawable.background_button_alpha)
        } else {
            binding.clickCleanVerify.visibility = View.GONE
        }
        val text = binding.codePhoneVerify.text
        binding.codePhoneVerify.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.clickCleanVerify.visibility = View.VISIBLE
                binding.line1.setBackgroundColor(Color.BLUE)
                binding.clickMessageVerify.setBackgroundResource(R.drawable.background_button)
            }
        })

        if (text.isNotEmpty()) {
            binding.clickCleanVerify.visibility = View.VISIBLE
            binding.line1.setBackgroundColor(Color.BLUE)
            binding.clickMessageVerify.setBackgroundColor(R.color.primary_Blue)
        } else {
            binding.clickCleanVerify.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.clickMessageVerify.text = resources.getString(R.string.click_verify)
        binding.resetCode.text = resources.getString(R.string.reset_verify)
        binding.aboutAuth.text = resources.getString(R.string.about_verify)
        binding.codePhoneVerify.hint = resources.getString(R.string.code_verify)
        binding.textViewVerify.text = resources.getString(R.string.title_verify)

    }


    private fun sendVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity() as Activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            // Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            //  Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            // Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(binding.root.context, "Muvaffaqiyatli", Toast.LENGTH_SHORT)
                        .show()
                    Cache().MySharedPreferencesSIgnIn(binding.root.context, "true")
                } else {

                    // Sign in failed, display a message and update the UI
                    // Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // Toast.makeText(this, "Muvaffaqiyatsiz!!!", Toast.LENGTH_SHORT).show()
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(
                            binding.root.context,
                            "Kod xato kiritildi",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    // Update UI
                }
            }
    }
}