package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDiscipline;

public class DeferedDisciplineGroup {

    @SerializedName("deferedDisciplines")
    List<DeferedDiscipline> deferedDisciplineList;

    public List<DeferedDiscipline> getIndividualPlanList() {
        return deferedDisciplineList;
    }

    public void setIndividualPlanList(List<DeferedDiscipline> deferedDisciplineList) {
        this.deferedDisciplineList = deferedDisciplineList;
    }

}
