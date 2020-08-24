package kz.almaty.satbayevuniversity.ui.admission;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.Speciality;
import kz.almaty.satbayevuniversity.data.entity.admission.University;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Speciality>> specialityByEducationalProgramMutableLiveData;
    private MutableLiveData<List<Speciality>> specialityBySchoolIdMutableLiveData;
    private MutableLiveData<List<University>> univercitiesListMutableLiveData;

    public void  makeRetrofitRequest(Call<List<IdAndTitle>> call,MutableLiveData<List<IdAndTitle>> mutableLiveData){
        Callback<List<IdAndTitle>> callback = new Callback<List<IdAndTitle>>() {
            @Override
            public void onResponse(Call<List<IdAndTitle>> call, Response<List<IdAndTitle>> response) {
                if(response.isSuccessful()){
                    if(response.body() !=null){
                        mutableLiveData.setValue(response.body());
                    }
                }
            }
            @Override
            public void onFailure(Call<List<IdAndTitle>> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }


    public void getUnivercities(){
        AdmissionRetrofit.getApi(1).getUniversities("").enqueue(new Callback<Set<University>>() {
            @Override
            public void onResponse(Call<Set<University>> call, Response<Set<University>> response) {
                if(response.isSuccessful()){
                    List<University> universities = new ArrayList<>();
                    universities.addAll(response.body());
                    univercitiesListMutableLiveData.setValue(universities);
                }
            }

            @Override
            public void onFailure(Call<Set<University>> call, Throwable t) {
                Log.i("aibol","t = "+t.getMessage());
            }
        });
    }
    public void getSpecialitiesByGroupOfEducationalProgram(String levelID, String groupOfEducationalProgram){
        AdmissionRetrofit.getApi(1).getSpecialitiesByGroupEducationalProgram(levelID,groupOfEducationalProgram).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if(response.isSuccessful()){
                        specialityByEducationalProgramMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
            }
        });
    }
    public void getSpecialitiesBySchoolId(String levelID, String schoolId){
        AdmissionRetrofit.getApi(1).getSpecialitiesBySchoolId(levelID,schoolId).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                if(response.isSuccessful()){
                    specialityBySchoolIdMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
            }
        });
    }




    public MutableLiveData<List<Speciality>> getSpecialityByEducationalProgramMutableLiveData() {
        if(specialityByEducationalProgramMutableLiveData == null){
            specialityByEducationalProgramMutableLiveData = new MutableLiveData<>();
        }
        return specialityByEducationalProgramMutableLiveData;
    }

    public MutableLiveData<List<University>> getUnivercitiesListMutableLiveData() {
        if(univercitiesListMutableLiveData==null){
            univercitiesListMutableLiveData = new MutableLiveData<>();
        }
        return univercitiesListMutableLiveData;
    }

    public MutableLiveData<List<Speciality>> getSpecialityBySchoolIdMutableLiveData() {
        if(specialityBySchoolIdMutableLiveData==null){
            specialityBySchoolIdMutableLiveData = new MutableLiveData<>();
        }
        return specialityBySchoolIdMutableLiveData;
    }
}
