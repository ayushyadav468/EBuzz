package com.example.ayushyadav.ebuzz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient INSTANCE;

    private ApiCall apiCall;

    private ApiClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall = retrofit.create(ApiCall.class);
    }

    public static ApiClient getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public ApiCall getApiCall() {
        return apiCall;
    }

}
