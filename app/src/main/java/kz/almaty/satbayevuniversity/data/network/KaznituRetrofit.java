package kz.almaty.satbayevuniversity.data.network;

import android.content.SharedPreferences;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.utils.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class KaznituRetrofit {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://ssomobile.satbayev.university/";
    private SharedPreferences sPref;

    public static MyApi getApi(){

        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + App.getContext().getSharedPreferences("MyPref", MODE_PRIVATE).getString("MyToken",""))
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .callTimeout(10, TimeUnit.SECONDS)
                .readTimeout(11, TimeUnit.SECONDS)
                .connectTimeout(11, TimeUnit.SECONDS)
                .build();

        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(MyApi.class);
    }
}
