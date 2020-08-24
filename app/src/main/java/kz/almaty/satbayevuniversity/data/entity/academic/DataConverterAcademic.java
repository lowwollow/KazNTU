package kz.almaty.satbayevuniversity.data.entity.academic;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverterAcademic implements Serializable {
    Gson gson = new Gson();

    @TypeConverter
    public String fromDatesItemList(List<DatesItem> datesItems) {
        if (datesItems == null) {
            return (null);
        }
        Type type = new TypeToken<List<DatesItem>>() {}.getType();
        String json = gson.toJson(datesItems, type);
        return json;
    }

    @TypeConverter
    public List<DatesItem> toDatesItemList(String datesItemsString) {
        if (datesItemsString == null) {
            return (null);
        }
        Type type = new TypeToken<List<DatesItem>>() {}.getType();
        List<DatesItem> datesItems = gson.fromJson(datesItemsString, type);
        return datesItems;
    }
}
