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

  // @Test
  // public void save_returnsTrueIfDescriptionAreTheSame() {
  //   Water WaterOne = new Water("Jerry", 0);
  //   Water WaterTwo = new Water("Jerry", 0);
  //   assertTrue(WaterOne.equals(WaterTwo));
  // }

  // @Test
  // public void save_assignsIdToObject() {
  //   Water myWater = new Water("Davis", 0);
  //   myWater.save();
  //   Water savedWater = Water.all().get(0);
  //   assertEquals(myWater.getId(), savedWater.getId());
  // }

}