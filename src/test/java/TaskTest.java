import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void book_instantiatesCorrectly_true() {
    Task newTask = new Task("Water");
    assertTrue(newTask instanceof Task);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTaskTitlesAreTheSame() {
    Task newTaskOne = new Task("Water");
    Task newTaskTwo = new Task("Water");
    assertTrue(newTaskOne.equals(newTaskTwo));
  }

  @Test
  public void save_assignsIdToObject() {
    Task myTask = new Task("Water");
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertEquals(myTask.getId(), savedTask.getId());
  }

  @Test
  public void find_findTaskInDatabase_true() {
    Task myTask = new Task("Water");
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertTrue(myTask.equals(savedTask));
  }

  @Test
  public void update_updatesAllTaskProperties() {
    Task newTask = new Task("Water");
    newTask.save();
    newTask.setDescription("Fertilize");
    newTask.update();
    assertEquals("Fertilize", newTask.getDescription());
  }


  @Test
  public void delete_removesTaskFromTheDatabase() {
    Task newTask = new Task("Trim");
    newTask.save();
    newTask.delete();
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void getAuthors_returnsAllAuthors_ArrayList() {
    Task myTask = new Task("Water");
    myTask.save();

    Plant myPlant = new Plant("Marigold");
    myPlant.save();

    myTask.addPlant(myPlant);
    List savedPlants = myTask.getPlants();
    assertEquals(savedPlants.size(), 1);
  }

  @Test
  public void getCreatedDate_returnsDateCreated_LocalDateTime() {
    Task myTask = new Task("Water");
    myTask.save();
    Long nowInMinutes = System.currentTimeMillis()/60000;
    assertEquals(myTask.getCreatedTimestamp()/60000, nowInMinutes);
  }

}
