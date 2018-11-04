package com.example.mrrs.schoolhelper.service;
import com.example.mrrs.schoolhelper.model.Attendance;
import com.example.mrrs.schoolhelper.model.LocationMap;
import com.example.mrrs.schoolhelper.model.Login;
import com.example.mrrs.schoolhelper.model.Student;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("student.php")
    Call<List<Student>> GetDataInfoStudent();
    @GET("location.php")
    Call<List<LocationMap>> GetDataLocation();
    @GET("attendance.php")
    Call<List<Attendance>> GetDataAttendance();

    @FormUrlEncoded
    @POST("login.php")
    Call<List<Login>> Login(@Field("user") String user, @Field("pass") String pass);
}
