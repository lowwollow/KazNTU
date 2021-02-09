package kz.almaty.satbayevuniversity.data;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import org.json.JSONException;
import org.json.JSONObject;
import kz.almaty.satbayevuniversity.ui.LoginActivity;

public class App extends Application {

    public static App instance;
    private static Context mContext;

    private AppDatabase database;
    private  String API_key = "321bf830-687a-4da9-825e-656b760e5bba";
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
                .fallbackToDestructiveMigration()
                .build();
        mContext = getApplicationContext();
        //createPushNotification("TEST");
        OneSignal.Builder builder = OneSignal.startInit(this);
        builder.inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
        builder.unsubscribeWhenNotificationsAreDisabled(true);
        builder.setNotificationOpenedHandler(new NotificationHandler());
        builder.init();
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key).build();
        YandexMetrica.activate(getApplicationContext(), config);
        YandexMetrica.enableActivityAutoTracking(this);
    }

    public static App getInstance() {
        return instance;
    }
    public static Context getContext() {
        return mContext;
    }
    public AppDatabase getDatabase() {
        return database;
    }

    class NotificationHandler implements OneSignal.NotificationOpenedHandler{

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            SharedPreferences.Editor editor = getSharedPreferences("one_signal_notification_handler", Context.MODE_PRIVATE).edit();
            JSONObject data = result.notification.payload.additionalData;
            if(data != null && data.has("typeId")){
                editor.putInt("typeId",data.optInt("typeId"));
                editor.apply();
            }
            Intent i = new Intent(getContext(), LoginActivity.class);
            i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }

    }
    public void createPushNotification(String text){
        String UUID = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
        Log.d("TEST", "createPushNotification: " + UUID);
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"+text+"'}, 'include_player_ids': ['"+UUID+"']}"),
                    new OneSignal.PostNotificationResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Log.i("OneSignalExample", "postNotification Success: " + response.toString());
                        }

                        @Override
                        public void onFailure(JSONObject response) {
                            Log.e("OneSignalExample", "postNotification Failure: " + response.toString());
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}