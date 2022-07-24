package entities;

public class Director {
	private int director_id;
	private String director_name;
	
	public Director() {
		
	}
	
	public Director(int id,String name) {
		this.director_id=id;
		this.director_name=name;
	}

	public int getDirector_id() {
		return director_id;
	}

	public void setDirector_id(int director_id) {
		this.director_id = director_id;
	}

	public String getDirector_name() {
		return director_name;
	}

	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	
	
}

