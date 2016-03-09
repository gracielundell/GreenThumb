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
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Plant newPlant = new Plant(name);
      newPlant.save();
      response.redirect("/");
      return null;
    });

    get("/plants/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Plant plant = Plant.find(Integer.parseInt(request.params(":id")));
      List<Task> tasks = plant.getTasks();
      model.put("plant", plant);
      model.put("tasks", tasks);
      model.put("template", "templates/plants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/success", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");
      int taskId = Integer.parseInt(request.queryParams("taskId"));
      Plant plant = Plant.find(taskId);
      Task newTask = new Task(description);
      newTask.save();
      plant.addTask(newTask);
      response.redirect("/plants/" + taskId);
      return null;
    });

    get("/delete/plant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Plant plantRemove = Plant.find(Integer.parseInt(request.params(":id")));
      plantRemove.delete();
      model.put("plants", Plant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/delete/task/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Plant plantSave = Plant.find(Integer.parseInt(request.params(":id")));
      Task plant = Task.find(Integer.parseInt(request.params(":id")));
      plant.delete();
      model.put("plant", plantSave);
      model.put("plants", Plant.all());
      model.put("tasks", Task.all());
      model.put("template", "templates/index.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String plantName = request.queryParams("name");
      Plant plant = new Plant(plantName);
      plant.save();
      model.put("plant", Plant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/plants/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("plant", Plant.find(Integer.parseInt(request.queryParams(":id"))));
      model.put("wateringSchedule", Water.find(Integer.parseInt(request.queryParams(":id"))));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
