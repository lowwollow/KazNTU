//package kz.almaty.satbayevuniversity.data.network;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Response;
//
//public class NetworkConnectionInterceptor implements Interceptor {
//    private Context context;
//
//    public NetworkConnectionInterceptor(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        return null;
//        if (!isInternetAvailable())
//            throw NoInternetException("Отсутствует интернет соединение");
//        return chain.proceed(chain.request());
//    }
//
//    private Boolean isInternetAvailable(){
//
//
//        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        return conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() != NetworkInfo.State.DISCONNECTED
//                && conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() != NetworkInfo.State.DISCONNECTED;
//
//    }
//}
