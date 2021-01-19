package kz.almaty.satbayevuniversity.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.ImageDialog;

public  class Utils {

    public static String parseDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat2.format(date1);
    }

    public static IdAndTitle getIdAndTitleFromListById(List<IdAndTitle> list, int id){
        for(IdAndTitle idAndTitle : list){
            if(idAndTitle.getId() == id){
                return idAndTitle;
            }
        }
        return null;
    }

    public static  void openFullImage(Bitmap bitmap, Context context){
        ImageDialog imageDialog = new ImageDialog(bitmap);
        imageDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"imageDialog");
    }

}
