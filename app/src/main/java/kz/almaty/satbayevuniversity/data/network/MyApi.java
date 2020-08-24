package kz.almaty.satbayevuniversity.data.network;

import java.util.List;
import java.util.Map;

import kz.almaty.satbayevuniversity.data.entity.AccountEntity;
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;
import kz.almaty.satbayevuniversity.data.entity.grade.attestation.Attestation;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.ResponseTranscript;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;
import kz.almaty.satbayevuniversity.data.entity.notification.PushNotification;
import kz.almaty.satbayevuniversity.data.entity.schedule.Exam;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.entity.schedule.Student;
import kz.almaty.satbayevuniversity.data.entity.umkd.File;
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApi {

    @POST("token")
    Call<AccountEntity> onLogin(@Body RequestBody requestBody);

    @GET("api/User/Photo")
    Call<ResponseBody> updatePhoto();

    @GET("api/Journal")
    Call<List<ResponseJournal>> updateJournal();

    @GET("api/Schedule")
    Call<List<Schedule>> updateSchedule();

    @GET("api/Attestation")
    Call<List<Attestation>> updateAttestation();

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

    @POST("/api/Complaint/Save")
    Call<ResponseBody> sendComplaint(@Body Map<String, String> body);

    @GET("api/schedule/students")
    Call<List<Student>> getStudentList(@Query("classid") Integer clasid, @Query("language") String language);

    @GET("api/notification/all")
    Call<List<PushNotification>> getPushNotificationList();

    @POST("api/Notification/Read")
    Call<ResponseBody> removePushNotification(@Query("pushId") int pushId);

    @POST("api/Notification/Register")
    Call<ResponseBody> registerPlayerId(@Query("playerId") String playerId, @Query("device") String device, @Query("appversion") String appversion);
}