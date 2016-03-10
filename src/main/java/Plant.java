import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Plant {
  private String name;
  private int id;

  public Plant(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherPlant){
    if (!(otherPlant instanceof Plant)) {
      return false;
    } else {
      Plant newPlant = (Plant) otherPlant;
      return this.getName().equals(newPlant.getName());
    }
  }

  //GETTER//
  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  //SETTER
  public void setName(String newName) {
    this.name = newName;
  }

  //CREATE//
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO plants (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  //READ//
  public static List<Plant> all(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM plants";
      return con.createQuery(sql)
        .executeAndFetch(Plant.class);
    }
  }

  public static Plant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT *FROM plants WHERE id=:id";
      Plant plant = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Plant.class);
      return plant;
    }
  }

  public List<Task> getTasks() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT tasks.* FROM plants JOIN tasks_plants ON (tasks_plants.plant_id = plants.id) JOIN tasks ON (tasks.id = tasks_plants.task_id) WHERE plant_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Task.class);
      }
  }

  //UPDATE//
  public void update(String newPlantName) {
    this.name = newPlantName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE plants SET name =:name WHERE id =:id";
      con.createQuery(sql)
      .addParameter("name", newPlantName)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }  

  public void addTask(Task task) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks_plants (plant_id, task_id) VALUES (:plant_id, :task_id);";
      con.createQuery(sql)
        .addParameter("plant_id", this.getId())
        .addParameter("task_id", task.getId())
        .executeUpdate();
    }
  }

  //DELETE//
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM plants WHERE id = :id; DELETE FROM tasks_plants WHERE plant_id =:id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
}
