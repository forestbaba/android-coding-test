package com.forestsoftware.mytest.rest;

import android.util.Log;

import com.forestsoftware.mytest.util.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static Retrofit retrofit = null;

    public static OkHttpClient getHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();

                                return chain.proceed(request);
                            }


                        })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        try {
                            Response response = chain.proceed(chain.request());
                            if (!response.isSuccessful()) {
                                Log.e("tag", "Failure central - response code: " + response.code());
                                Log.e("tag", "central server error handling");


                                switch (response.code()) {
                                    case 401:

                                        Log.wtf("===========401401401401===========","401 error");

                                        break;
                                    case 403:

                                        Log.wtf("===========403===========","403 error");

                                        break;
                                    default:
                                        Log.e("tag?>?>?||<<<<", "Log error or do something else with error code:" + response.code());

                                        break;

                                }
                            }
                            return response;
                        } catch (IOException e)
                        {
                            Log.e("tag", e.getMessage(), e);
                            Log.e("tag", "central network error handling");

                            throw e;

                        }
                    }
                })
                .build();
        return okClient;

    }


    public static Retrofit getClient() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .client(getHeader())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
