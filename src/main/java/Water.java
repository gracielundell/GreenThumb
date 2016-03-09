import org.sql2o.*;
import java.util.List;


public class Water {
	private int id;
	private int frequency;


	public Water(int frequency) {
		this.frequency = frequency;
	}

	public int getId() {
		return id;
	}

	public int getFrequency() {
		return frequency;
	}

  @Override
  public boolean equals(Object otherWater){
    if (!(otherWater instanceof Water)) {
      return false;
    } else {
      Water newWater = (Water) otherWater;
      return this.getFrequency() == newWater.getFrequency() &&
        this.getId() == newWater.getId();
    }
  }	


//Create
  public void save() {
    try (Connection con = DB.sql2o.open()) {
       String sql = "INSERT INTO watering (frequency) VALUES (:frequency)";
       this.id = (int) con.createQuery(sql, true)
         .addParameter("frequency", this.frequency)
         .executeUpdate()
         .getKey();
    }
  }

  public static List<Water> all() {
    String sql = "SELECT id, frequency FROM watering";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Water.class);
    }
  }

  public static Water find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM watering where id=:id";
      Water water = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Water.class);
      return water;
    }
  }






}