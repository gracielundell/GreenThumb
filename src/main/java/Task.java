import java.util.List;
import org.sql2o.*;
import java.util.Date;
import java.time.LocalDateTime;


public class Task {
  private String description;
  private int id;
  private LocalDateTime createdDate;
  private LocalDateTime dueDate;

  public Task (String description) {
    this.description = description;
    this.createdDate = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object otherTask){
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getDescription().equals(newTask.getDescription());
    }
  }

  //GETTER METHODS//

  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
  }

    public LocalDateTime getCreatedDate() {
      return createdDate;
    }

    public LocalDateTime getDueDate() {
      return dueDate;
    }

  //SETTER METHODS//

  public void setDescription(String newDescription) {
    this.description = newDescription;
  }

  public void setDueDate(int year, int month, int day, int hour, int minute) {
    this.dueDate = this.dueDate.of(year, month, day, hour, minute);
  }

  //CREATE//
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks (description) VALUES (:description)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
    }
  }

  //READ//
  public static List<Task> all(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks";
      return con.createQuery(sql)
        .executeAndFetch(Task.class);
    }
  }

  public static Task find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT *FROM tasks WHERE id=:id";
      Task task = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Task.class);
      return task;
    }
  }

  public List<Plant> getPlants() {
    try(Connection con = DB.sql2o.open()){
       String sql = "SELECT plants.* FROM tasks JOIN tasks_plants ON (tasks.id = tasks_plants.task_id) JOIN plants ON (tasks_plants.plant_id = plants.id) WHERE task_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Plant.class);
      }
  }

  public static List<Task> findByDescription(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks WHERE description=:description";
      return con.createQuery(sql)
        .addParameter("description", description)
        .executeAndFetch(Task.class);
    }
  }

  //UPDATE//
  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET description =:description WHERE id=:id";
        con.createQuery(sql)
        .addParameter("description", this.description)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void addPlant(Plant plant) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks_plants (plant_id, task_id) VALUES (:plant_id, :task_id);";
      con.createQuery(sql)
        .addParameter("plant_id", plant.getId())
        .addParameter("task_id", this.getId())
        .executeUpdate();
    }
  }

  //DELETE//
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tasks WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

}
