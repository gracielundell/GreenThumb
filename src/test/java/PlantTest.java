import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.sql2o.*;

public class PlantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  public void all_emptyAtFirst() {
      assertEquals(Water.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfNameOfObjectAreTheSame() {
  	Plant plantOne = new Plant("Marigold");
  	Plant plantTwo = new Plant("Marigold");
  	assertTrue(plantOne.equals(plantTwo));
  }

  @Test
  public void save_assignIdToObject() {
  	Plant myPlant = new Plant("Marigold");
  	myPlant.save();
  	Plant savedPlant = Plant.all().get(0);
  	assertEquals(myPlant.getId(), savedPlant.getId());
  }

  @Test
  public void find_findPlantInDatabase_true() {
  	Plant myPlant = new Plant("Marigold");
  	myPlant.save();
  	Plant savedPlant = Plant.find(myPlant.getId());
  	assertTrue(myPlant.equals(savedPlant));
  }

  @Test
  public void update_updatesAllWateringProperties() {
  	Plant myPlant = new Plant("Marigold");
  	myPlant.save();
  	myPlant.setName("Tomatoe");
  	myPlant.update();
  	assertEquals("Tomatoe", myPlant.getName());
  }

  @Test
  public void delete_deletePlantFromDatabase() {
  	Plant myPlant = new Plant("Marigold");
  	myPlant.save();
  	myPlant.delete();
  	assertEquals(Plant.all().size(), 0);
  }
}
