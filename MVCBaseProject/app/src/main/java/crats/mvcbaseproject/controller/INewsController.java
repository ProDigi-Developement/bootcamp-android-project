package crats.mvcbaseproject.controller;

public interface INewsController {
    void fetchSuccess();
    void fetchFailure(String errorMessage);
}
