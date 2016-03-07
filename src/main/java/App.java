import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("plants", Plant.all());
      model.put("template","templates/index.vtl");
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

  }
}
