/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nleaves.study.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.nleaves.study.model.User;


/**
 */
public class PreferencesHelper {

    private static final String USER_PREFERENCES = "userPreferences";
    private static final String PREFERENCE_USER_ID = USER_PREFERENCES + ".user_id";
    private static final String PREFERENCE_USER_NAME = USER_PREFERENCES + ".user_name";

    private PreferencesHelper() {
        //no instance
    }

    /**
     */
    public static void setUser(Context context, User user) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCE_USER_ID, user.getUser_id());
        editor.putString(PREFERENCE_USER_NAME, user.getUser_name());
        editor.apply();
    }

    public static User getUser(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        final String user_id = preferences.getString(PREFERENCE_USER_ID, null);
        final String user_name = preferences.getString(PREFERENCE_USER_NAME, null);
        return new User(user_id, user_name);
    }


    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCE_USER_ID);
        editor.remove(PREFERENCE_USER_NAME);
        editor.apply();
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }
}
