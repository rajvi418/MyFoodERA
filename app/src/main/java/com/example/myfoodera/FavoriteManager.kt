package com.example.myfoodera

import android.content.Context

object FavoriteManager {

    private const val PREF_NAME = "favorites_pref"
    private const val KEY_FAVORITES = "favorites_list"

    fun addFavorite(context: Context, dishName: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(KEY_FAVORITES, mutableSetOf()) ?: mutableSetOf()
        val newSet = set.toMutableSet()
        newSet.add(dishName)
        prefs.edit().putStringSet(KEY_FAVORITES, newSet).apply()
    }

    fun removeFavorite(context: Context, dishName: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(KEY_FAVORITES, mutableSetOf()) ?: mutableSetOf()
        val newSet = set.toMutableSet()
        newSet.remove(dishName)
        prefs.edit().putStringSet(KEY_FAVORITES, newSet).apply()
    }

    fun getFavorites(context: Context): Set<String> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_FAVORITES, mutableSetOf()) ?: mutableSetOf()
    }

    fun isFavorite(context: Context, dishName: String): Boolean {
        return getFavorites(context).contains(dishName)
    }
}