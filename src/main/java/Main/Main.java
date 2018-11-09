package Main;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main {
	
	private static final String SERVICE_URL = "http://ec2-35-166-113-35.us-west-2.compute.amazonaws.com";
	
	public static void main(String[] args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(SERVICE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		APITarefas service = retrofit.create(APITarefas.class);
		
		//createNewTask(service, "Testing Post with Json");		
		
		consumeTaskById(service, 1320);
		
		//updateTask(service, 1321, "Task Updated");
		
		//deleteTaskById(service, 1324);
		
		//consumeAllTasks(service);
		
	}
	
	public static void createNewTask(APITarefas service, String description) throws IOException{
		Task task = new Task();
		task.setDescription(description);
		Call<Task> call = service.includeTask(task);
		Response<Task> response = call.execute();
		Task body = response.body();
		
		Gson gson = new Gson();
		
		System.out.println("Created [POST] : ");
		System.out.println(gson.toJson(body));
	}
	
	public static void consumeTaskById(APITarefas service, int id) throws IOException{
		Call<Task> getTaskById = service.getTaskById(id);
		Response<Task> response = getTaskById.execute();
		Task body = response.body();
		
		Gson gson = new Gson();
		
		System.out.println("\nTask [GET] : ");
		System.out.println(gson.toJson(body));
	}
	
	public static void updateTask(APITarefas service, int id, String description) throws IOException{
		Task task = new Task();
		task.setDescription(description);
		task.setStatus(false);
		Call<Void> updateTaskById = service.updateTask(id, task);
		Response<Void> response = updateTaskById.execute();
		
		System.out.println("\nHTTP Code [Put] : " + response.code());
	}
	
	public static void deleteTaskById(APITarefas service, int id) throws IOException{
		Call<Void> deleteTaskById = service.deleteTaskById(id);
		Response<Void> response = deleteTaskById.execute();
		System.out.println("\nHTTP Code [DELETE] : " + response.code());
	}
	
	public static void consumeAllTasks(APITarefas service) throws IOException {
		Call<List<Task>> getAllTasks = service.getAllTasks();
		Response<List<Task>> response = getAllTasks.execute();
		List<Task> allTask = response.body();
		
		Gson gson = new Gson();
		
		System.out.println("\nList [GET] : ");
		System.out.println(gson.toJson(allTask));
	}
	
}
