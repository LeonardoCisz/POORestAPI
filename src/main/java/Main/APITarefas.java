package Main;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APITarefas {

	@Headers("Student:1144002233")
	@GET("api/tasks")
	Call<List<Task>> getAllTasks();
	
	@Headers("Student:1144002233")
	@POST("api/tasks")
	Call<Task> includeTask(@Body Task body);
	
	@Headers("Student:1144002233")
	@GET("api/tasks/{id}")
	Call<Task> getTaskById(@Path("id") Integer id);
	
	@Headers("Student:1144002233")
	@PUT("api/tasks/{id}")
	Call<Void> updateTask(@Path("id") Integer id, @Body Task task);
	
	@Headers("Student:1144002233")
	@DELETE("api/tasks/{id}")
	Call<Void> deleteTaskById(@Path("id") Integer id);
	
}