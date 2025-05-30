package com.akshay.todo.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object ThemePreferenceManager {
    private const val PREF_NAME = "user_preferences"
    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)

    suspend fun saveThemePreference(context: Context, isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_THEME_KEY] = isDark
        }
    }

    fun getThemePreference(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { prefs -> prefs[DARK_THEME_KEY] == true }
    }
}