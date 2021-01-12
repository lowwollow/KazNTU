package kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeferredDisciplineGroup1 {

    @SerializedName("deferedDisciplines")
    List<DeferredDiscipline1> deferredDiscipline1List;

    public List<DeferredDiscipline1> getIndividualPlanList() {
        return deferredDiscipline1List;
    }

    public void setIndividualPlanList(List<DeferredDiscipline1> deferredDiscipline1List) {
        this.deferredDiscipline1List = deferredDiscipline1List;
    }

}
