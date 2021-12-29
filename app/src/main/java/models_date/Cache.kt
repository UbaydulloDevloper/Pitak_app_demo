package models_date

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class Cache {
    private lateinit var preferens: SharedPreferences

    @SuppressLint("CommitPrefEdits")
    fun MySharedPreferencesSIgnIn(context: Context, signIn: String) {
        preferens = context.getSharedPreferences(NAME, MODE)
        val editor = preferens.edit()
        editor.putString("key", signIn)
        editor.apply()

    }
    fun MySharedPreferencesLanguage(context: Context, language: String) {
        preferens = context.getSharedPreferences(NAME1, MODE)
        val editor = preferens.edit()
        editor.putString("key", language)
        editor.apply()
    }

    fun lodedate(context: Context): String {
        preferens = context.getSharedPreferences(NAME, MODE)
        val text = preferens.getString("key", "")
        return text!!
    }
    fun lodeLanguage(context: Context):String{
        preferens = context.getSharedPreferences(NAME1, MODE)
        val language = preferens.getString("key", "")
        return language!!
    }

    companion object {
        private const val NAME = "CacheSignIn"
        private const val NAME1 = "CacheLanguage"
        private const val MODE = Context.MODE_PRIVATE
    }
}