package com.hunterit.kotlinlearn.util

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private var _context: Context? = context

    private var PRIVATE_MODE: Int = 0

    private val PREF_NAME: String = "androidhive-welcome"

    private val IS_FIRST_TIME_LAUNCH: String = "IsFirstTimeLaunch"

    fun setFirstTimeLaunch (isFirtTime: Boolean){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirtTime)
        editor.commit()
    }

    fun isFirtTimeLauch(): Boolean{
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    init {
        pref = _context!!.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }
}