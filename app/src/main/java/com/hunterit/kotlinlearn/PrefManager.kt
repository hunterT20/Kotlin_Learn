package com.hunterit.kotlinlearn

import android.content.Context
import android.content.SharedPreferences

class PrefManager {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var _context: Context? = null

    var PRIVATE_MODE: Int = 0

    private val PREF_NAME: String = "androidhive-welcome"

    private val IS_FIRST_TIME_LAUNCH: String = "IsFirstTimeLaunch"

    constructor(context: Context){
        this._context = context
        pref = _context!!.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setFirstTimeLaunch (isFirtTime: Boolean){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirtTime)
        editor.commit()
    }

    fun isFirtTimeLauch(): Boolean{
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }
}