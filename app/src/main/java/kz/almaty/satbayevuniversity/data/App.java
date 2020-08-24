package kz.almaty.satbayevuniversity.data;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.room.Room;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import kz.almaty.satbayevuniversity.ui.LoginActivity;

public class App extends Application {

    public static App instance;
    private static Context mContext;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
        mContext = getApplicationContext();
        OneSignal.Builder builder = OneSignal.startInit(this);
        builder.inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
        builder.unsubscribeWhenNotificationsAreDisabled(true);
        builder.setNotificationOpenedHandler(new NotificationHandler());
        builder.init();
//        createPushNotification("test notification");

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

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleHelper.onAttach(base, "ru"));
//    }
    class NotificationHandler implements OneSignal.NotificationOpenedHandler{

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            SharedPreferences.Editor editor = getSharedPreferences("one_signal_notification_handler",Context.MODE_PRIVATE).edit();
            JSONObject data = result.notification.payload.additionalData;
            if(data !=null && data.has("typeId")){
                editor.putInt("typeId",data.optInt("typeId"));
                editor.commit();
            }
            Intent i = new Intent(getContext(), LoginActivity.class);
            i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }


    }
    public void createPushNotification(String text){
        String UUID = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
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