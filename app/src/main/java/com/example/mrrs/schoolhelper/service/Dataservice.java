package com.example.mrrs.schoolhelper.service;
import com.example.mrrs.schoolhelper.model.Student;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface Dataservice {
    @GET("student.php")
    Call<List<Student>> GetDataInfoStudent();
}
