package com.example.pr_test.ui

import android.content.Context
import android.content.SharedPreferences

private const val SHARE_NAME = "settings"
class SharedService(context: Context) {

    private lateinit var myShared: SharedPreferences
    private val prefSetting = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefSetting.edit()


    fun writeToShare(boolean: Boolean) {
        myShared = prefSetting
        editor = prefSetting.edit()
        editor.putBoolean(SHARE_NAME, boolean)
        editor.apply()
    }

    fun readFromShared(): Boolean {
        myShared = prefSetting
        return myShared.getBoolean(SHARE_NAME, true)
    }
}