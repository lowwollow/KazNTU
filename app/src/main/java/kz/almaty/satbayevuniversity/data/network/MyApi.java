package kz.almaty.satbayevuniversity.data.network;

import java.util.List;
import java.util.Map;

import kz.almaty.satbayevuniversity.data.User;
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;
import kz.almaty.satbayevuniversity.data.entity.grade.attestation.Attestation;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.ResponseTranscript;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.Semesters1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;
import kz.almaty.satbayevuniversity.data.entity.notification.PushNotification;
import kz.almaty.satbayevuniversity.data.entity.schedule.Exam;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.entity.schedule.Student;
import kz.almaty.satbayevuniversity.data.entity.umkd.File;
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;
import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDiscipline;
import kz.almaty.satbayevuniversity.ui.umkd.estimateteacher.InstructorBody;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDisciplineGroup;
import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDisciplineGroup;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    @POST("token")
    Call<User> onLogin(@Body RequestBody requestBody);

    @GET("api/User/Photo")
    Call<ResponseBody> updatePhoto();

    @GET("api/Journal")
    Call<List<ResponseJournal>> updateJournal();

    @GET("api/Schedule")
    Call<List<Schedule>> updateSchedule();

    @GET("api/Attestation")
    Call<List<Attestation>> updateAttestation();

    @GET("api/iup")
    Call<DeferredDisciplineGroup1> updateDeferedDiscipline();

    @GET("api/iup")
    Call<ChosenDisciplineGroup1> updateChosenDiscipline();

    @GET("api/Transcript")
    Call<ResponseTranscript> updateTranscript();

    @GET("api/ExamSchedule")
    Call<List<Exam>> updateExam();

    @GET("api/News/Short")
    Call<List<Notification>> updateNotification();

    @GET("api/Instructor")
    Call<List<Umkd>> updateInstructor();

    @GET("api/File/Course")
    Call<List<File>> updateFileCourse(@Query("courseCode") String courseCode, @Query("instructorID") String instructorID);

    @GET("/api/File/Download")
    Call<ResponseBody> downloadFileCourse(@Query("fileID") String courseCode);

    @GET("api/schedule/students")
    Call<List<Student>> getStudentList(@Query("classid") Integer clasid, @Query("language") String language);

    @GET("api/notification/all")
    Call<List<PushNotification>> getPushNotificationList();

    @POST("api/Notification/Read")
    Call<ResponseBody> removePushNotification(@Query("pushId") int pushId);

    @POST("api/Notification/Register")
    Call<ResponseBody> registerPlayerId(@Query("playerId") String playerId, @Query("device") String device, @Query("appversion") String appversion);

    @POST("/api/Complaint/Save")
    Call<ResponseBody> sendComplaint(@Body Map<String, String> body);

    @POST("/api/instructorrating")
    Call<ResponseBody>sendRating(@Body Map<String, Object> body);

    @GET("/api/instructorrating/{instructorId}")
    Call<List<InstructorBody>> getRating(@Path("instructorId") int instructorId);
}
