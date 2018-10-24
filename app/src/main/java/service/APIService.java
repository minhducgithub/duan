package service;

public class APIService {
    private static String base_url = "http://192.168.1.71/SchoolHelper/Server/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
