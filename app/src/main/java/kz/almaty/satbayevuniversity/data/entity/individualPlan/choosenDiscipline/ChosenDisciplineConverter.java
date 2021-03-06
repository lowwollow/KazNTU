package kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.grade.transcript.CoursesItem;

public class ChosenDisciplineConverter implements Serializable {
    Gson gson = new Gson();

    @TypeConverter
    public String fromChosenDisciplineList(List<ChosenDiscipline1> deferredDisciplines) {
        if (deferredDisciplines == null) {
            return null;
        }
        Type type = new TypeToken<List<ChosenDiscipline1>>() {}.getType();
        String json = gson.toJson(deferredDisciplines, type);
        return json;
    }

    @TypeConverter
    public List<ChosenDiscipline1> toChosenDisciplineList(String deferredDiscipilineItemString) {
        if (deferredDiscipilineItemString == null) {
            return null;
        }
        Type type = new TypeToken<List<ChosenDiscipline1>>() {}.getType();
        List<ChosenDiscipline1> chosenDisciplines = gson.fromJson(deferredDiscipilineItemString, type);
        return chosenDisciplines;
    }
}
