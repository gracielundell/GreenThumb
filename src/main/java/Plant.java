import org.sql2o.*;
import java.util.List;

public class Plant {
	private String name;
	private int id;

	public Plant (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object otherPlant) {
		if(!(otherPlant instanceof Plant)) {
			return false;
		} else {
			Plant newPlant = (Plant) otherPlant;
			return this.getName().equals(newPlant.getName()) && this.getId() == newPlant.getId();
		}
	}

	//Create
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO plants (name) VALUES (:name)";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("name", this.name)
				.executeUpdate()
				.getKey();
		}
	}

	public static List<Plant> all() {
		String sql = "SELECT id, name FROM plants";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(Plant.class);
		}
	}
}