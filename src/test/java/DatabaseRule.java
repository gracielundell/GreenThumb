import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/green_thumb_test", null, null);
  }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTasksQuery = "DELETE FROM tasks *";
      String deletePlantsQuery = "DELETE FROM plants *";
      String deleteTasksPlantsQuery = "DELETE FROM tasks_plants *";
      con.createQuery(deleteTasksQuery).executeUpdate();
      con.createQuery(deletePlantsQuery).executeUpdate();
      con.createQuery(deleteTasksPlantsQuery).executeUpdate();
    }
  }
}
