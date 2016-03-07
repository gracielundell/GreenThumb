import org.sql2o.*;
import java.util.List;

public class Plant {
	private String name;
	private int id;

	public Plant (String name) {
		this.name = name;
	}


	//Getter
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}


	//Setter
	public void setName(String newName) {
		this.name = newName;
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

	public static Plant find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM plants where id=:id";
			Plant plant = con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(Plant.class);
			return plant;
		}
	}

	//Update
	public void update() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE plants SET name = :name WHERE id=:id";
			con.createQuery(sql)
				.addParameter("name", this.name)
				.addParameter("id", this.id)
				.executeUpdate();
		}
	}

	//Delete
	public void delete() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM plants where id=:id";
			con.createQuery(sql)
				.addParameter("id", this.id)
				.executeUpdate();
		}
	}
}