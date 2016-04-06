package org.java8tet;

public class Skiman {

	@Override
	public String toString() {
		return "Skiman [name=" + name + ", points=" + points + ", sex=" + sex + "]";
	}

	public static final char MAN = 'M';
	
	public static final char WOMAN = 'W';
	
	private String name;
	
	private double points;
	
	private char sex;

	public Skiman(String name, double points, char sex) {
		this.name = name;
		this.points = points;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}
	
}
