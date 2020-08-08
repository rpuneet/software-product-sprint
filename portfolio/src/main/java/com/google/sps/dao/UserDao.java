package com.google.sps.dao;

import com.google.appengine.api.users.User;

public class UserDao implements IUserDao{
    User user;

    public UserDao(User user) {
        this.user = user;
    }

    @Override
    public String getNickName() {
        return user.getNickname();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }
}
