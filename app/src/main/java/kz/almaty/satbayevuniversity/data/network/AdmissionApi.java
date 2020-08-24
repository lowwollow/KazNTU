package kz.almaty.satbayevuniversity.data.network;

import java.util.List;
import java.util.Set;

import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.LoginResponse;
import kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master.AchievementInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.additional_info_master.AdditionalInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.choosing_education_master.SpecialityChoise;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info.EducationInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info_master.EducationInfoMaster;
import kz.almaty.satbayevuniversity.data.entity.admission.email_verification.Data;
import kz.almaty.satbayevuniversity.data.entity.admission.email_verification.VerifyEmail;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.LaborActivity;
import kz.almaty.satbayevuniversity.data.entity.admission.registration.Account;
import kz.almaty.satbayevuniversity.data.entity.admission.main_info.MainInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.medical_info.MedicalInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.registration.Registration;
import kz.almaty.satbayevuniversity.data.entity.admission.residence_info.ResidenceInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.Speciality;
import kz.almaty.satbayevuniversity.data.entity.admission.University;
import kz.almaty.satbayevuniversity.data.entity.admission.User;
import kz.almaty.satbayevuniversity.data.entity.admission.UserInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.status_info.EntrantStatusInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.status_info_master.MasterStatusInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AdmissionApi {
    //Email Verification
    @POST("/Entrants/VerifyEmail")
    Call<VerifyEmail> verifyEmailBachelor(@Body Data data);
    @POST("/Masters/VerifyEmail")
    Call<VerifyEmail> verifyEmailMaster(@Body Data data);
    @POST("/Doctors/VerifyEmail")
    Call<VerifyEmail> verifyEmailDoctor(@Body Data data);

    //registration
    @POST("/Entrants/Register")
    Call<Account> registerBachelor(@Body Registration registration);
    @POST("/Masters/Register")
    Call<Account> registerMaster(@Body Registration registration);
    @POST("/Doctors/Register")
    Call<Account> registerDoctor(@Body Registration registration);

    //Login
    @POST("/account/login")
    Call<LoginResponse> login(@Body User user);

    //GetMainInfo
    @GET("/Entrants/GetMainInfo")
    Call<MainInfo> getBachelorMainInfo();
    @GET("/Masters/GetMainInfo")
    Call<MainInfo> getMasterMainInfo();
    @GET("/Doctors/GetMainInfo")
    Call<MainInfo> getDoctorMainInfo();

    //SaveMainInfo
    @Multipart
    @POST("/Entrants/SaveMainInfo")
    Call<Account> saveBachelorMainInfo(@Body MainInfo mainInfo);
    @POST("/Masters/SaveMainInfo")
    Call<Account> saveMasterMainInfo(@Body MainInfo mainInfo);
    @POST("/Doctors/SaveMainInfo")
    Call<Account> saveDoctorMainInfo(@Body MainInfo mainInfo);

    //Download Images
    @GET("/ImagePreview/DownloadImageDocument/")
    Call<ResponseBody> getPhoto();
    @GET("/ImagePreview/DownloadImageDocument/")
    Call<ResponseBody> getDocumentImage(@Query("documentID")String documentID);

    //Medical Info
    @GET("/Entrants/GetEntrantMedInfo")
    Call<MedicalInfo> getMedicalInfo();
    @POST("/Entrants/SaveEntrantMedInfo")
    Call<ResponseBody> saveMedicalInfo(@Body MedicalInfo medicalInfo);

    //AdditionalInfo
    @GET("Masters/GetAdditionalInfo")
    Call<AdditionalInfo> getAdditionalInfo();
    @POST("Masters/SaveAdditionalInfo")
    Call<ResponseBody> saveAdditionalInfo(@Body AdditionalInfo additionalInfo);

    //PlaceToLive
    @GET("Entrants/GetPlaceToLive")
    Call<ResidenceInfo> getResidenceInfo();
    @POST("Entrants/SavePlaceToLive")
    Call<ResponseBody> saveResidenceInfo(@Body ResidenceInfo residenceInfo);

    //EducationInfo
    @GET("/Entrants/GetEducationInfo")
    Call<EducationInfo> getEducationInfo();
    @POST("/Entrants/SaveEducationInfo")
    Call<ResponseBody> saveEducationInfo(@Body EducationInfo educationInfo);

    //Labor Activity
    @GET("/Masters/GetProfessionalExperience")
    Call<LaborActivity> getLaborActivity();
    @POST("/Masters/SaveProfessionalExperience")
    Call<ResponseBody> saveLaborActivity(@Body LaborActivity laborActivity);

    @GET("/api/EntrantStatuses")
    Call<EntrantStatusInfo> getBachelorStatusInfo();

    @GET("/Masters/GetSpecialityChoice")
    Call<SpecialityChoise> getSpecialityChoise();


    @GET("/Masters/GetAchievements")
    Call<AchievementInfo> getAchievementInfo();
    @POST("/Masters/SaveAchievements")
    Call<ResponseBody> saveAchievementInfo(@Body AchievementInfo achievementInfo);

    @GET("/Masters/GetEducationInfo")
    Call<EducationInfoMaster> getEducationInfoMaster();

    @GET("/api/MasterStatuses")
    Call<MasterStatusInfo> getMasterStatusInfo();

    @GET("api/ref/Edu_CitizenCategories/ru")
    Call<List<IdAndTitle>> getCitizenCategory();

    @GET("api/ref/Edu_Countries/kz")
    Call<List<IdAndTitle>> getCountries();

    @GET("api/ref/GetNationalitiesTableView/ru")
    Call<List<IdAndTitle>> getNationalities();

    @GET("api/ref/Edu_MaritalStatuses/ru")
    Call<List<IdAndTitle>> getMaritalStatuses();

    @GET("api/ref/Edu_UserDocumentTypes/ru?filter=1|2")
    Call<List<IdAndTitle>> getDocumentTypes();

    @GET("api/ref/Edu_DocumentIssueOrgs/ru")
    Call<List<IdAndTitle>> getDocumentIssueOrgs();

    @GET("api/ref/GetAlmatyDistrictTableView/ru")
    Call<List<IdAndTitle>> getAlmatyDistricts();

    @GET("Entrants/GetRegions")
    Call<List<IdAndTitle>> getRegions();

    @GET("Entrants/GetPlaceByParentID")
    Call<List<IdAndTitle>> getPlaceByParentId(@Query("id") String id);

    @GET("api/ref/Edu_HostelPrivelege/ru")
    Call<List<IdAndTitle>> getHostelPriveleges();

    @GET("api/ref/Edu_Languages/ru")
    Call<List<IdAndTitle>> getEduLanguages(@Query("filter")String filter);

    @GET("api/ref/Edu_EducationDocumentSubTypes/ru")
    Call<List<IdAndTitle>> getEduDocumentTypes();

    @GET("api/ref/Edu_HearAboutTypes/ru")
    Call<List<IdAndTitle>> getHearAboutTypes();

    @GET("api/ref/GetLocalitiesTableView/ru")
    Call<List<IdAndTitle>> getLocalities();

    @GET("api/ref/Edu_DisabilityStatuses/ru")
    Call<List<IdAndTitle>> getDisabilityStatuses();

    @GET("api/ref/GetGroupEducationalPrograms")
    Call<List<IdAndTitle>> getGroupEducationalPrograms(@Query("levelID") String levelID);

    @GET("/api/ref/GetSpecialitiesAndEducationalPrograms")
    Call<List<Speciality>> getSpecialitiesByGroupEducationalProgram(@Query("levelID") String levelID, @Query("groupEducationalPrograms") String groupEducationalProgram);

    @GET("api/ref/Edu_MasterProgramTypes/ru")
    Call<List<IdAndTitle>> getMasterProgramType();

    @GET("/api/toefltypes")
    Call<List<IdAndTitle>> getToeflTypes();

    @GET("api/ref/Edu_EducationPaymentTypes/ru")
    Call<List<IdAndTitle>> getEduPaymentTypes(@Query("filter")String filter);

    @GET("api/ref/GetUnivercities")
    Call<Set<University>> getUniversities(@Query("specialityID") String specialityID);

    @GET("/api/user/getuserinfo")
    Call<UserInfo> getUserInfo();

    @GET("api/ref/GetSpecialities")
    Call<List<Speciality>> getSpecialitiesBySchoolId(@Query("levelID")String levelID,@Query("schoolID") String schoolID);

    @GET("api/ref/Edu_ResearchDirections/ru")
    Call<List<IdAndTitle>> getResearchDirections();

    @GET("api/ref/Edu_GrantTypes/ru")
    Call<List<IdAndTitle>> getGrantTypes(@Query("filter")String filter);

    @GET("/api/schools/ru")
    Call<List<IdAndTitle>> getSchools(@Query("localityId")String localityId,@Query("schoolTypeId")String schoolTypeId,@Query("IsCitySettlementType")Boolean IsCitySettlementType);

}
