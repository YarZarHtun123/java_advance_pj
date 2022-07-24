package entities;

public class Actor {
	private int actor_id;
	private String actor_name;
	
	public Actor() {
		
	}
	
	public Actor(int id,String name) {
		this.actor_id=id;
		this.actor_name=name;
	}

	public int getActor_id() {
		return actor_id;
	}

	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}

	public String getActor_name() {
		return actor_name;
	}

	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}
	
	
}

