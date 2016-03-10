import java.util.*;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("plants", Plant.all());
      model.put("tasks", Task.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/plant", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("plantName");
      Plant newPlant = new Plant(name);
      newPlant.save();
      response.redirect("/");
      return null;
    });

    post("/task", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("taskDescription");
      Task task = new Task(description);
      task.save();
      response.redirect("/");
      return null;
    });

    get("/plant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Plant plant = Plant.find(id);
      model.put("plant", plant);
      model.put("tasks", Task.all());
      model.put("template", "templates/plants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/plant/:id", (request, response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int plantId = Integer.parseInt(request.queryParams("plantId"));
      int taskId = Integer.parseInt(request.queryParams("taskId"));
      Plant plant = Plant.find(plantId);
      Task task = Task.find(taskId);
      plant.addTask(task);
      response.redirect("/plant/" + plantId);
      return null;
    });

    post("/update/plant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int plantId = Integer.parseInt(request.params("plantId"));
      String plantName = request.queryParams("plantName");
      Plant plant = Plant.find(plantId);
      plant.update(plantName);
      response.redirect("/plant/" + plantId);
      return null;
    });
    
    get("/delete/plant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Plant plantRemove = Plant.find(Integer.parseInt(request.params(":id")));
      plantRemove.delete();
      model.put("plants", Plant.all());
      model.put("template", "templates/plants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/task/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Task task = Task.find(id);
      model.put("task", task);
      model.put("plants", Plant.all());
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/task/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.queryParams("taskId"));
      int plantId = Integer.parseInt(request.queryParams("plantId"));
      Task task = Task.find(taskId);
      Plant plant = Plant.find(plantId);
      task.addPlant(plant);
      response.redirect("/task/" + taskId);
      return null;
    });

    post("/update/task/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params("taskId"));
      Task task = Task.find(taskId);
      String taskDescription = request.queryParams("taskDescription");
      task.update(taskDescription);
      response.redirect("/task/" + taskId);
      return null;
    });

    get("/delete/task/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params(":id"));
      Task task = Task.find(taskId);
      task.delete();
      model.put("tasks", Task.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }
}

