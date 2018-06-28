package crats.mvcbaseproject.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import crats.mvcbaseproject.model.User;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class UserController implements IUserApi {


    private static UserController instance = null;
    private ArrayList<User> userList = new ArrayList<User>();

    private IUserApi iUserApi = null;
    private IUserController iUserController = null;

    private final String url = "https://randomuser.me/api/?results=25";
    private RequestQueue requestQueue = null;

    private UserController() {
        // Nothing
    }
    public void setupUserController(IUserController delegateHandler, Context context){
        this.iUserApi = this;
        this.iUserController = delegateHandler;
        requestQueue = newRequestQueue(context);
    }

    public static UserController shared() {
        if (instance == null){
            instance = new UserController();
        }

        return instance;
    }

    public ArrayList<User> getUserList(){
        return userList;
    }

    public void fetchList() {
        requestQueue.add(fetchUserRequest());
        requestQueue.start();
    }


    private User convertJSONToUserObject(JSONObject object) throws JSONException {
        String gender = object.getString("gender");
        String firstName = object.getString("firstName");
        String lastName = object.getString("lastName");
        String dob = object.getString("dob");
        int age = object.getInt("age");


        return new User(gender,firstName,lastName,dob,age);
    }

    private JsonObjectRequest fetchUserRequest() {
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET,
                        url,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // To avoid create variable inside of loops
                                ArrayList<User> returnList = new ArrayList<User>();
                                JSONArray jsonArray = null;
                                JSONObject jsonObject = null;

                                try {
                                    jsonArray = response.getJSONArray("results");

                                    for (int i = 0; i <jsonArray.length(); i++) {
                                        try {
                                            jsonObject = jsonArray.getJSONObject(i);

                                            returnList.add(convertJSONToUserObject(jsonObject));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            iUserApi.fetchFailure("JSON read failure");
                                            break;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            iUserApi.fetchFailure("Unknown failure");
                                            break;
                                        }
                                    }
                                    iUserApi.fetchSuccess(returnList);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    iUserApi.fetchFailure("JSON read failure");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    iUserApi.fetchFailure("Unknown failure");
                                }
                            }

                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // On error response, implement callback for it too
                                Log.e("API error", "Server ERROR: " + error.getMessage());
                                iUserApi.fetchFailure(error.getMessage());
                            }
                        });


        return jsonObjectRequest;
    }



    @Override
    public void fetchSuccess(ArrayList<User> list)
    {
        this.userList = list;
        iUserController.fetchSuccess();
    }

    @Override
    public void fetchFailure(String errorMessage) {
        iUserController.fetchFailure(errorMessage);
    }
}
