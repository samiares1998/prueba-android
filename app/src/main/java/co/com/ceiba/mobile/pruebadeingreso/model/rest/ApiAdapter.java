package co.com.ceiba.mobile.pruebadeingreso.model.rest;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static UsuariosApi API_SERVICE;

    public static UsuariosApi getApiService(){

        String baseUrl = Endpoints.URL_BASE;


        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(UsuariosApi.class);

        }




        return API_SERVICE;
    }


}
