package org.java8tet;

import java.util.Arrays;
import java.util.List;

public interface DogFactory {

	public static List<String> availableDogs() {
		return Arrays.asList("Shepard","Bulldog","Pitbull");
	}
	
	public static Dog createDog(String dogtype) {
		if( availableDogs().contains(dogtype) ) {
			return new Dog(dogtype);
		}
		return null;
	}
	
	public static class Dog {
		private String name;
		public Dog(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
}
