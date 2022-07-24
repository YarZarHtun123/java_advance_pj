package entities;

public class Movie {
	private int movie_id;
	private String movie_name;
	private Director director;
	private Actor actor;
	private Actress actress;
	private MovieType movieType;
	
	public Movie() {}
	
	public Movie(int movie_id, String movie_name, Director director, Actor actor, Actress actress, MovieType movieType,
			String duration) {
		super();
		this.movie_id = movie_id;
		this.movie_name = movie_name;
		this.director = director;
		this.actor = actor;
		this.actress = actress;
		this.movieType = movieType;
		this.duration = duration;
	}
	private String duration;
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	public Actress getActress() {
		return actress;
	}
	public void setActress(Actress actress) {
		this.actress = actress;
	}
	public MovieType getMovieType() {
		return movieType;
	}
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
