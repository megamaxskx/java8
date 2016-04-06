package org.java8tet;

public class Car {
	public String name;
	public Car(String name) {
		this(name, false);
	}
	public Car(String name, boolean b) {
		this.name = name;
		this.canRide = b;
	}
	private boolean canRide;
	public boolean canRide() {
		return canRide;
	}
	public String getName() {
		return name;
	}
	
	public interface CarBuilder {
		public default String getBestCarName() {
			return "BMW";
		}
		
		public default boolean isBestCar(Car car) {
			return car.getName().equals(getBestCarName());
		}
	}

	public static class AmericanCar implements CarBuilder {
		public String getBestCarName() {
			return "Dodge challenger";
		}
	}

	public static class RussianCar implements CarBuilder {
		public boolean isBestCar(Car car) {
			return car.canRide();
		}
	}
}

