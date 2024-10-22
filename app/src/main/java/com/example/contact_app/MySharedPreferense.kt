package com.example.contact_app

import android.content.ContentProviderOperation
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreferense {
    private const val NAME="catch_file_name"
    private const val MODE=Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences
    fun init(context: Context){
        preferences=context.getSharedPreferences(NAME, MODE)
    }
    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)-> Unit){
        val editor=edit()
        operation(editor)
        editor.apply()
    }
    var list:ArrayList<My_Contact>
        get() = gsonToList(preferences.getString("KeyList","[]")!!)
        set(value) = preferences.edit{
            it.putString("KeyList", listToGson(value))
        }

    private fun gsonToList(gsontString:String):ArrayList<My_Contact>{
        val list=ArrayList<My_Contact>()
        val type=object :TypeToken<ArrayList<My_Contact>>(){}.type
        list.addAll(Gson().fromJson(gsontString,type))
        return list
    }
    private fun listToGson(list:ArrayList<My_Contact>):String{
        return Gson().toJson(list)
    }
}
