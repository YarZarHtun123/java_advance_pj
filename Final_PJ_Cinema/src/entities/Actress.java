package entities;

public class Actress {
	private int actress_id;
	private String actress_name;
	
	public Actress() {
		
	}
	
	public Actress(int id,String name) {
		this.actress_id=id;
		this.actress_name=name;
	}

	public int getActress_id() {
		return actress_id;
	}

	public void setActress_id(int actress_id) {
		this.actress_id = actress_id;
	}

	public String getActress_name() {
		return actress_name;
	}

	public void setActress_name(String actress_name) {
		this.actress_name = actress_name;
	}
	
	
}
