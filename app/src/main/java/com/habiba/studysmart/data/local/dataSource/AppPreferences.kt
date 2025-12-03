package com.habiba.studysmart.data.local.dataSource

import android.content.SharedPreferences
import com.habiba.studysmart.common.strings.EMPTY_STRING
import com.habiba.studysmart.common.strings.USER_ID_PREFERENCE
import javax.inject.Inject
import androidx.core.content.edit

class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
):IAppPreference {
    // the splashscreen checking if the user is logged in or not
    override fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID_PREFERENCE, EMPTY_STRING)
    }

    // after login
    override fun putUserId(userId: String?) {
        sharedPreferences.edit { putString(USER_ID_PREFERENCE, userId) }
    }

    // after logout option
    override fun clearUserSession() {
        sharedPreferences.edit {
            remove(USER_ID_PREFERENCE)
        }

    }

}