package kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class DeferredDisciplineGroup1 implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;

    @SerializedName("deferedDisciplines")
    @TypeConverters({DeferredDisciplineConverter.class})
    List<DeferredDiscipline1> deferredDiscipline1List;

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public List<DeferredDiscipline1> getDeferredDiscipline1List() {
        return deferredDiscipline1List;
    }

    public void setDeferredDiscipline1List(List<DeferredDiscipline1> deferredDiscipline1List) {
        this.deferredDiscipline1List = deferredDiscipline1List;
    }

    public List<DeferredDiscipline1> getIndividualPlanList() {
        return deferredDiscipline1List;
    }

    public void setIndividualPlanList(List<DeferredDiscipline1> deferredDiscipline1List) {
        this.deferredDiscipline1List = deferredDiscipline1List;
    }

}
