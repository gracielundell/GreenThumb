import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class WaterTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
      assertEquals(Water.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfDescriptionAreTheSame() {
    Water WaterOne = new Water(5);
    Water WaterTwo = new Water(5);
    assertTrue(WaterOne.equals(WaterTwo));
  }

  @Test
  public void save_assignsIdToObject() {
    Water myWater = new Water(5);
    myWater.save();
    Water savedWater = Water.all().get(0);
    assertEquals(myWater.getId(), savedWater.getId());
  }

  @Test
  public void find_findWateringInDatabase_true() {
    Water myWater = new Water(5);
    myWater.save();
    Water savedWater = Water.find(myWater.getId());
    assertTrue(myWater.equals(savedWater));
  }
  

}