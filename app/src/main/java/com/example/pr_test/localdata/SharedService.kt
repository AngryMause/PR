package com.example.pr_test.localdata

import android.content.Context
import android.content.SharedPreferences

private const val SHARE_NAME = "settings"
private const val HTTP_URL = "httpUrl"
private const val IS_FIRST_SIGN_IN = "isFirst"

class SharedService(context: Context) {
    private val prefSetting = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
    private var myShared: SharedPreferences = prefSetting
    private lateinit var editor: SharedPreferences.Editor

    fun writeToShare(value: Boolean) {
        editor = myShared.edit()
        editor.putBoolean("lol", value)
        editor.apply()
    }

    fun writeURLToShared(value: String) {
        editor = myShared.edit()
        editor.putString("url", value)
        editor.apply()
    }


    fun readFromSharedIsSign(defValue: Boolean): Boolean {
        myShared = prefSetting
        return myShared.getBoolean("lol", defValue)
    }

    fun readFromSharedUrl(defValue: String): String {
        myShared = prefSetting
        return myShared.getString("url", defValue)!!
    }

}