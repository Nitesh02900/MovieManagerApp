package com.techlabs.Assignments.app2;

public class TestMenu {

	public static void main(String[] args) {
		MovieManager movieManager = new MovieManager();
        MovieController movieController = new MovieController(movieManager);
        movieController.start();

	}

}
