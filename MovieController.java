package com.techlabs.Assignments.app2;

import java.util.Scanner;

	public class MovieController {
	    private MovieManager manager;

	    public MovieController(MovieManager manager) {
	        this.manager = manager;
	    }

	    public void start() {
	        displayMenu();
	    }

	    public void displayMenu() {
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	        	System.out.println("======== Movie Store Menu =======");
	            System.out.println("1. Add Movie");
	            System.out.println("2. Show All Movies");
	            System.out.println("3. Show Movie Details");
	            System.out.println("4. Delete Movie");
	            System.out.println("5. Clear All Movies");
	            System.out.println("6. Get Movie ID");
	            System.out.println("7. Exit");
	            System.out.println("Enter your choice:");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            
	            switch (choice) {
	            case 1:
	            	
	                Movie newMovie = setMovieDetails();
	                manager.addMovie(newMovie);
	                manager.saveMovies(); // Save movies after adding
	                System.out.println("Movie added successfully!");
	                break;
	            case 2:
	                displayAllMovies();
	                break;
	            case 3:
	                showMovieDetails();
	                break;
	            case 4:
	                deleteMovie();
	                manager.saveMovies(); // Save movies after deletion
	                break;
	            case 5:
	                manager.clearMovies();
	                manager.saveMovies(); // Save movies after clearing
	                System.out.println("All movies cleared!");
	                break;
	            case 6:
	                System.out.println("Current Movie ID: " + manager.getMovieId());
	                break;
	            case 7:
	                System.out.println("Exiting...");
	                return;
	            default:
	                System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }
	    }

	    private void deleteMovie() {
	    	 Scanner scanner = new Scanner(System.in);
	    	    System.out.print("Enter movie ID to delete: ");
	    	    int movieId = scanner.nextInt();
	    	    scanner.nextLine(); // Consume newline

	    	    boolean deleted = manager.deleteMovie(movieId);
	    	    if (deleted) {
	    	        manager.saveMovies(); // Save movies after deletion
	    	        System.out.println("Movie deleted successfully!");
	    	    } else {
	    	        System.out.println("Movie not found.");
	    	    }
			
		}

		private void showMovieDetails() {
	    	 Scanner scanner = new Scanner(System.in);
	    	    System.out.print("Enter movie ID: ");
	    	    int movieId = scanner.nextInt();
	    	    scanner.nextLine(); // Consume newline

	    	    Movie movie = manager.getMovieById(movieId);
	    	    if (movie != null) {
	    	        System.out.println("MovieID: " + movie.getId());
	    	        System.out.println("Name: " + movie.getName());
	    	        System.out.println("Year: " + movie.getYear());
	    	        System.out.println("Genre: " + movie.getGenre());
	    	    } else {
	    	        System.out.println("Movie not found.");
	    	    }
	    	}
	    
		public void displayAllMovies() {
	        System.out.println("MovieID       Name                            Year     Genre");
	        System.out.println("---------------------------------------------------------------");
	        for (Movie movie : manager.getMovies()) {
	            System.out.printf("%-14s %-32s %-8d %s%n",movie.getId(),"'" + movie.getName() + "'",movie.getYear(),"'" + movie.getGenre() + "'");
	        }
	    }

	    public Movie setMovieDetails() {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter movie name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter movie year: ");
	        int year = scanner.nextInt();
	        scanner.nextLine();

	        System.out.print("Enter movie genre: ");
	        String genre = scanner.nextLine();

	        int id = manager.getMovieId();
	        return new Movie(id, name, year, genre);
	    }
}
