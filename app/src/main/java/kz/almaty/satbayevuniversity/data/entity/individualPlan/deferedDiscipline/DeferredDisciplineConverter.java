package kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDiscipline1;

public class DeferredDisciplineConverter implements Serializable {
    Gson gson = new Gson();

    @TypeConverter
    public String fromDeferredDisciplineList(List<DeferredDiscipline1> deferredDiscipline1s) {
        if (deferredDiscipline1s == null) {
            return null;
        }
        Type type = new TypeToken<List<DeferredDiscipline1>>() {}.getType();
        String json = gson.toJson(deferredDiscipline1s, type);
        return json;
    }

    @TypeConverter
    public List<DeferredDiscipline1> toDeferredDisciplineList(String deferredDiscipilineItemString) {
        if (deferredDiscipilineItemString == null) {
            return null;
        }
        Type type = new TypeToken<List<DeferredDiscipline1>>() {}.getType();
        List<DeferredDiscipline1> deferredDisciplines = gson.fromJson(deferredDiscipilineItemString, type);
        return deferredDisciplines;
    }
}
