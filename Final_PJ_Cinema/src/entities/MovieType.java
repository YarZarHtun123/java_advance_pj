package entities;

public class MovieType {
	private int movieType_id;
	private String movieType_name;
	
	public MovieType() {
		
	}
	
	public MovieType(int id,String name) {
		this.movieType_id=id;
		this.movieType_name=name;
	}

	public int getMovieType_id() {
		return movieType_id;
	}

	public void setMovieType_id(int movieType_id) {
		this.movieType_id = movieType_id;
	}

	public String getMovieType_name() {
		return movieType_name;
	}

	public void setMovieType_name(String movieType_name) {
		this.movieType_name = movieType_name;
	}
	
	
}

