package org.java8tet;

import java.util.function.Supplier;

public class Burger {
	
	public Burger() {
		
	}

	private String name;
	
	public Burger(String name) {
		this.name = name;
	}
	
	public static Burger createBurger(final Supplier<Burger> burger) {
		return new Burger(burger.get().getName());
	}
	
	public static Burger createBurger() {
		return new Burger();
	}
	
	public static void toTrash(Burger burger) {
		System.out.println("Putting trash burger " + burger.getName());
	}
	
	public void mixWith(Burger burger) {
		System.out.println("Burger " + this.name + " mixed with " + burger.getName());
	}

	public void omnOmnOmnBurger() {
		System.out.println("Eating burger " + this.name + ". Omn Omn Omn.");
	}
	
	private String getName() {
		return name;
	}
}
