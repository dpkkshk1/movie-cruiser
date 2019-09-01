package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	/**
	 * id for movie
	 */
	@Column(name="movie_id")
	private String movieId;
	/**
	 * name for movie
	 */
	@Column(name = "title")
	private String title;
	/**
	 * field for comments of movie
	 */
	@Column(name = "comments")
	private String comments;

	/**
	 * field for poster path of movie
	 */
	@Column(name = "poster_path")
	private String poster_path;
	/**
	 * field for releaseDate of movie
	 */
	@Column(name = "release_date")
	private String release_date;
	/**
	 * field for votingAverage of movie
	 */
	@Column(name = "voting_average")
	private double voting_average;
	/**
	 * field for votingCount of movie
	 */
	@Column(name = "voting_count")
	private int voting_count;
	/**
	 * field for userid
	 */
	@Column(name="user_id")
	private String userId;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	

	public Movie(int id, String movieId, String title, String comments, String poster_path, String release_date,
			double voting_average, int voting_count, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.voting_average = voting_average;
		this.voting_count = voting_count;
		this.userId = userId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public double getVoting_average() {
		return voting_average;
	}

	public void setVoting_average(double voting_average) {
		this.voting_average = voting_average;
	}

	public int getVoting_count() {
		return voting_count;
	}

	public void setVoting_count(int voting_count) {
		this.voting_count = voting_count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieId=" + movieId + ", title=" + title + ", comments=" + comments
				+ ", poster_path=" + poster_path + ", release_date=" + release_date + ", voting_average="
				+ voting_average + ", voting_count=" + voting_count + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + id;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + ((poster_path == null) ? 0 : poster_path.hashCode());
		result = prime * result + ((release_date == null) ? 0 : release_date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(voting_average);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + voting_count;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id != other.id)
			return false;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (poster_path == null) {
			if (other.poster_path != null)
				return false;
		} else if (!poster_path.equals(other.poster_path))
			return false;
		if (release_date == null) {
			if (other.release_date != null)
				return false;
		} else if (!release_date.equals(other.release_date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (Double.doubleToLongBits(voting_average) != Double.doubleToLongBits(other.voting_average))
			return false;
		if (voting_count != other.voting_count)
			return false;
		return true;
	}

	
}
