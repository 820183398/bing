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

import com.nleaves.study.model.User;
import com.nleaves.study.utils.StrUtils;


/**
 */
public class UserHelper {

    private static UserHelper mInstance;
    private User user;
    private  Context context;
    private UserHelper() {
    }
    public static UserHelper getInstance() {
        if (null == mInstance) {
            mInstance = new UserHelper();
        }
        return mInstance;
    }

    public void init(Context context) {
       this.context=context.getApplicationContext();
    }

    public void signOut() {

        PreferencesHelper.signOut(context);
        user=null;

    }

    public User getUser() {
        if(user==null){
            user=PreferencesHelper.getUser(context);
        }
        return user;
    }



    public boolean isHaveUser() {
       User user=getUser();
        if(user!=null&& !StrUtils.isBlank(user.getUser_id())){
            return true ;
        }else {
            return false ;
        }
    }

    public void setUser(User user) {
        this.user=user;
        PreferencesHelper.setUser(context,user);
    }



    public void saveUser() {
        PreferencesHelper.setUser(context,user);
    }
}
