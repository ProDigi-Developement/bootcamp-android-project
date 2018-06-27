package crats.mvcbaseproject.controller;

import java.util.ArrayList;

import crats.mvcbaseproject.model.News;

public interface INewsApi {
    void fetchSuccess(ArrayList<News> list);
    void fetchFailure(String errorMessage);
}
