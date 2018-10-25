package com.example.mrrs.schoolhelper.service;

public class APIService {
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(APIConnect.HOST_URL).create(Dataservice.class);
    }
}
