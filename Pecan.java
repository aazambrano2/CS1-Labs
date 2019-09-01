/* CS1101 – Intro to Computer Science 
Instructor: Aguirre 

Comprehensive Lab 3

By including my name below I confirm that:
-	I am submitting my original work.
-	If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
-	This submission does not incur in any academic dishonesty practice as described in the course syllabus.


Modified and submitted by: [Aaron Zambrano] 
	
*/ 

public class Pecan {
	
private int id;
private int age;
private boolean isHealthy;
private int spread;
private double yield;

//default Constructor
public Pecan() {
	this.id = 0;
	this.age = 0;
	this.isHealthy = false;
	this.spread = 0;
	this.yield = 0.0;
}

public Pecan(int id, int age, boolean isHealthy, int spread, double yield) {
	this.id = id;
	this.age = age;
	this.isHealthy = isHealthy;
	this.spread = spread;
	this.yield = yield;
}
//Setters
public void setID(int id) {
	this.id = id;
}

public void setAge(int age) {
	this.age = age;
}

public void setIsHealthy(boolean isHealthy) {
	this.isHealthy = isHealthy;
}

public void setSpread( int spread) {
  this.spread = spread;
}

public void setYield(double yield) {
	this.yield= yield;
}

//Getters
public int getID() {
	return this.id;
}
 
public int getAge() {
	return this.age;
}

public boolean getIsHealthy() {
	return this.isHealthy;
}

public int getSpread() {
	return this.spread;
}

public double getYield() {
	return this.yield;
}

//Actuators
public boolean needsPruning(int spread) {
	if(spread > 80){
		return true;
	}
	return false;
}

public String toString() {
	String s = "ID: " + this.id + ", age: " + this.age + ", healthy: " +this.isHealthy + ", spread: " + this.spread + ", yield: " + this.yield;
    return s;
}

}// end of Pecan.java

