package crats.mvcbaseproject.controller;

import java.util.ArrayList;

import crats.mvcbaseproject.model.User;

public class UserController implements IUserApi {
    @Override
    public void fetchSuccess(ArrayList<User> list) {

    }

    @Override
    public void fetchFailure(String errorMessage) {

    }
}
