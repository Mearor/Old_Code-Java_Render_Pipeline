package com.example.diorama_engine_v10;

public class Point_3D {

	// Class to represent data for a point in 3D space
	
	double X;
	double Y;
	double Z;

	Point_3D(double iX,double iY,double iZ) {
		
		X = iX;
		Y = iY;
		Z = iZ;
	}
	
	// Getters and Setters

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getZ() {
		return Z;
	}

	public void setZ(double z) {
		Z = z;
	}
	
	// Output Variables for Debugging
	
	public void Log_Point(){
		System.out.println("Point = "+"<<"+X+","+Y+","+Z+">>");
	}

}
