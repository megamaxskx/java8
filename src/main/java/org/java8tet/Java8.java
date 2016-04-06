package org.java8tet;

import java.io.OutputStream;
import java.nio.file.Files;
import java.time.Clock;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.java8tet.Car.AmericanCar;
import org.java8tet.Car.RussianCar;
import org.java8tet.DogFactory.Dog;

public class Java8 
{
    public static void main( String[] args ) {
    	Java8 j8 = new Java8();
    	 // lambdas
    	j8.lambdas();
    	separator();
    	// funcitional interfaces
    	j8.functionInterfaces();
    	separator();
    	// deafult interface implementation 
    	j8.testDefaultInterfaceMethods();
    	separator();
    	// interface static methods 
    	j8.testStaticMethods();
    	separator();
    	// static method references
    	j8.testStaticMethodRefernces();
    	separator();
    	// repeatable annotation
    	// in java 8 you can now do sth like 
    	// @Annotation("Some annotation")
    	// @Annotation("Another name")
    	// public void method() { ...
    	// ---------------------------------------
    	// in Java 8 you can annotate almost everything:
    	//	-	local variables; 
    	//	-	generic types;
    	//	-	super classes;
    	//	-	implementing interfaces;
    	//	-	method exception declaration;
    	// ---------------------------------------
    	// Optional test
    	j8.testForOptional();
    	separator();
    	// test streams
    	j8.testStrreams();
    	separator();
    	// test date functionality
    	j8.testJava8Date();
    	separator();
    }
    
	private static void separator() {
    	System.out.println("------------------------------------------------------");
	}

	/** lambdas */
    private void lambdas() {
    	Arrays.asList("a", "b", "c").forEach(c -> System.out.println(c));
    	Arrays.asList("a", "b", "c").forEach((String s) -> {
    		System.out.println("Letter is " + s);
    	});
    	new Thread(() -> {
    		System.out.println("Execute separate task in thread '" + Thread.currentThread().getName() + "'");
    	}, "Lambda thread").start();
    }
    
    /** functional interface */
    private void functionInterfaces() {
    	Task checkIsValera = (s) -> s.equalsIgnoreCase("valera");
    	System.out.println( checkIsValera.execute("vasa") );
    	System.out.println( checkIsValera.execute("valera") );
    }
    
    @FunctionalInterface
    private interface Task {
    	public boolean execute(String s);
    }
    
    /** default interface methods */
    
    public void testDefaultInterfaceMethods() {
    	AmericanCar ac = new AmericanCar();
    	System.out.println(ac.getBestCarName());
    	System.out.println(ac.isBestCar(new Car("Dodge challenger")));
    	
    	RussianCar rc = new RussianCar();
    	System.out.println(rc.getBestCarName());
    	System.out.println(rc.isBestCar(new Car("Gygulini", true)));
    	System.out.println(rc.isBestCar(new Car("Gygulini")));
    }
    
    /** static methods */
    public void testStaticMethods() {
    	Dog dog = DogFactory.createDog("Pitbull");
    	System.out.println(dog.getName());
    	dog = DogFactory.createDog("yorkshire");
    	System.out.println(dog);
    }
    
    public void testStaticMethodRefernces() {
    	// 1 constructor reference
    	Burger burg = Burger.createBurger(Burger::new);
    	// or method reference
    	burg = Burger.createBurger(Burger::createBurger);
    	// 2 static method reference
    	List<Burger> burgers = Arrays.asList(new Burger("Classic"), new Burger("Mexican"), new Burger("Vegan"));
    	burgers.forEach( Burger::toTrash );
    	// 3 non static method without argumentss reference
    	burgers.forEach( Burger::omnOmnOmnBurger );
    	// 4 particular object reference call
    	Burger chicken = new Burger("Chicken");
    	burgers.forEach( chicken::mixWith );
    }
    
    // test Optional
    private void testForOptional() {
    	Optional<String> opt =  Optional.ofNullable(null);
    	System.out.println(opt.isPresent());
    	System.out.println(opt.orElseGet(() -> "Object is null"));
    	System.out.println(opt.orElse("Object is null"));
    	System.out.println(opt.map((String e) -> Arrays.asList(e)));
    	
    	Optional<String> opt2 = Optional.of("some object");
    	System.out.println(opt2.isPresent());
    	System.out.println(opt2.orElseGet(() -> "Object is null"));
    	System.out.println(opt2.map((String e) -> Arrays.asList(e)).get());
	}
    
    // streams example
    public void testStrreams() {
    	Skiman sk1 = new Skiman("Roger",  22, Skiman.MAN);
    	Skiman sk2 = new Skiman("Valera", 34, Skiman.MAN);
    	Skiman sk3 = new Skiman("Iryna",  66, Skiman.WOMAN);
    	Skiman sk4 = new Skiman("Anna",   104, Skiman.WOMAN);
    	
    	List<Skiman> sks = Arrays.asList(sk1, sk2, sk3, sk4);
    	// find maximum points
    	OptionalDouble max = sks.stream().mapToDouble( Skiman::getPoints ).max();
    	System.out.println("Max point has " + max.getAsDouble());
    	// get skiiman with max points 
    	Optional<Skiman> winner = sks.stream().sorted((s1, s2) -> Double.compare(s1.getPoints(), s2.getPoints())*-1).findFirst();
    	System.out.println("Max points is " + winner);
    	// sum all points
    	double sum = sks.stream().mapToDouble(Skiman::getPoints).sum();
    	System.out.println("Sum of points is " + sum);
    	// calculate sum in paralel
    	OptionalDouble parallelSum = sks.parallelStream().mapToDouble(Skiman::getPoints).reduce(Double::sum);
    	System.out.println("Parallel sum is " + parallelSum.getAsDouble());
    	// group by sex
    	Map<Character, List<Skiman>> collect = sks.stream().collect( Collectors.groupingBy( Skiman::getSex ));
    	System.out.println("Group by sex: " + collect);
    	// get names of all skiman
    	String skiman = sks.stream().map(Skiman::getName).collect(Collectors.joining(", "));
    	System.out.println("All skiman are " + skiman);
    	// create ranking list
    	System.out.println("******************************");
    	AtomicInteger inc = new AtomicInteger();
    	List<String> ranking = sks.stream()
    		.sorted((s1,s2) -> Double.compare(s1.getPoints(), s2.getPoints())*-1)
    		.map(s -> inc.incrementAndGet() + ". " +  s.getName() + "\t" + s.getSex() + "\t" + s.getPoints())
    		.collect(Collectors.toList());
    	ranking.forEach(s -> System.out.println(s));
    	
    }
    
    // test new pretty awesome java 8 date
    private void testJava8Date() {
    	Clock clock = Clock.systemDefaultZone();
    	System.out.println( clock.instant() );
    }
    
}
