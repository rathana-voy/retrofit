package com.rathana.retrofit_demo.data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String BASE_URL_2 = "http://110.74.194.124:15011";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient okHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

    }

    public static <S> S create(Class<S> service) {
        //interceptor/security
        return builder
                .client(okHttpClient())
                .build().create(service);
    }


}
