import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class PlantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
      assertEquals(Water.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfNameOfObjectAreTheSame() {
  	Plant plantOne = new Plant("Marigold");
  	Plant plantTwo = new Plant("Marigold");
  	assertTrue(plantOne.equals(plantTwo));
  }

}  