package kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeferedDisciplineGroup1 {

    @SerializedName("deferedDisciplines")
    List<DeferedDiscipline1> deferedDiscipline1List;

    public List<DeferedDiscipline1> getIndividualPlanList() {
        return deferedDiscipline1List;
    }

    public void setIndividualPlanList(List<DeferedDiscipline1> deferedDiscipline1List) {
        this.deferedDiscipline1List = deferedDiscipline1List;
    }

}
