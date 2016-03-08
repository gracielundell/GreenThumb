import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class PlantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void book_instantiatesCorrectly_true() {
    Plant newPlant = new Plant("Marigold");
    assertTrue(newPlant instanceof Plant);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Plant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfPlantNamesAreTheSame() {
    Plant newPlantOne = new Plant("Marigold");
    Plant newPlantTwo = new Plant("Marigold");
    assertTrue(newPlantOne.equals(newPlantTwo));
  }

  @Test
  public void getTasks_returnsAllTasks_ArrayList() {
    Task myTask = new Task("Water");
    myTask.save();

    Plant myPlant = new Plant("Marigold");
    myPlant.save();

    myPlant.addTask(myTask);
    List<Task> savedTasks = myPlant.getTasks();
    assertEquals(savedTasks.size(), 1);
  }

  @Test
  public void findByPlant_returnsTasksByPlant() {
    Task newTask1 = new Task("Water");
    Task newTask2 = new Task("Fertilize");
    Plant testPlant = new Plant("Marigold");
    testPlant.save();
    newTask1.save();
    newTask2.save();
    newTask1.addPlant(testPlant);
    newTask2.addPlant(testPlant);
    assertTrue((testPlant.getTasks()).contains(newTask1));
    assertTrue((testPlant.getTasks()).contains(newTask2));
  }

}
