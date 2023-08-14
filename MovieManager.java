package com.techlabs.Assignments.app2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
	private List<Movie> movies;
    private static final String filePath = "C:\\Users\\Nitesh\\OneDrive\\Desktop\\TSM Task\\MoviesList.txt";

    public MovieManager() {
        movies = new ArrayList<>();
        loadMovies();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void clearMovies() {
        movies.clear();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getMovieId() {
        return movies.size() + 1;
    }

    public void loadMovies() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            movies = (List<Movie>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveMovies() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllMovies() {
    	movies.clear();
    	saveMovies();
    }

	public Movie getMovieById(int id) {
		for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
		return null;
	}

	public boolean deleteMovie(int movieId) {
		 Movie movieToDelete = null;
		    for (Movie movie : movies) {
		        if (movie.getId() == movieId) {
		            movieToDelete = movie;
		            break;
		        }
		    }

		    if (movieToDelete != null) {
		        movies.remove(movieToDelete);
		        return true; 
		    } else {
		        return false; 
		    }
	}
}
