package com.study.kotlin.wan.util

import android.content.Context
import android.content.SharedPreferences
import com.study.kotlin.wan.constant.Constant
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * TODO 了解委托属性
 */
class PreferenceUtil<T>(private val name: String, private val default: T) : ReadWriteProperty<Any?, T> {

    companion object {
        lateinit var preference: SharedPreferences

        fun setContext(context: Context) {
            preference = context.getSharedPreferences(context.packageName + Constant.SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
        }

        fun clear() {
            preference.edit().clear().apply()
        }
    }


    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(name, value)

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)

    private fun <U> findPreference(name: String, default: U): U = with(preference) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(preference.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}