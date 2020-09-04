package kz.almaty.satbayevuniversity.data.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.utils.Storage;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdmissionRetrofit {
    private static Retrofit retrofit1;
    private static Retrofit retrofit2;
    private static final String BASE_URL = "http://api.satbayev.university/";
    private static final String BASE_URL2 = "http://kb.satbayev.university/";

    public static AdmissionApi getApi(int url){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit1 == null){
            retrofit1 = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient(1))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        if(retrofit2 == null){
            retrofit2 = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL2)
                    .client(getOkHttpClient(2))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return (url == 1 ? retrofit1.create(AdmissionApi.class) : retrofit2.create(AdmissionApi.class));
    }

    private static OkHttpClient getOkHttpClient(int url){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("language", Context.MODE_PRIVATE);
                    String language = sharedPreferences.getString("language","ru");

                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Cookie",url == 1 ? String.format("_culture=%s",language) : Storage.getInstance().getCookies())
                            .build();
                    return chain.proceed(newRequest);
                })
                .callTimeout(10, TimeUnit.SECONDS)
                .readTimeout(11, TimeUnit.SECONDS)
                .connectTimeout(11, TimeUnit.SECONDS)
                .build();
        return client;
    }
}
