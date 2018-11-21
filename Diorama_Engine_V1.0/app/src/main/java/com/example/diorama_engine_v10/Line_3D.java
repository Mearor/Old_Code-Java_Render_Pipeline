package com.example.diorama_engine_v10;

public class Line_3D {
	
	// Class to represent data for a Line in 3D space
	// Line is defined by Two Points and Parametric Equations
	
	// Coordinates of Point 1
	double X1;
	double Y1;
	double Z1;
	
	// Coordinates of Point 2
	double X2;
	double Y2;
	double Z2;
	
	// Vector between Point 1 & 2
	double x;
	double y;
	double z;
	
	Line_3D(double iX1,double iY1,double iZ1,double iX2,double iY2,double iZ2) {
		
		X1 = iX1;
		Y1 = iY1;
		Z1 = iZ1;
		
		X2 = iX2;
		Y2 = iY2;
		Z2 = iZ2;
		
		x = X2-X1;
		y = Y2-Y1;
		z = Z2-Z1;
		
	}
	
	// Getters and Setters

	public double getX1() {
		return X1;
	}

	public void setX1(double x1) {
		X1 = x1;
		update_x();
	}

	public double getY1() {
		return Y1;
	}

	public void setY1(double y1) {
		Y1 = y1;
		update_y();
	}

	public double getZ1() {
		return Z1;
	}

	public void setZ1(double z1) {
		Z1 = z1;
		update_z();
	}

	public double getX2() {
		return X2;
	}

	public void setX2(double x2) {
		X2 = x2;
		update_x();
	}

	public double getY2() {
		return Y2;
	}

	public void setY2(double y2) {
		Y2 = y2;
		update_y();
	}

	public double getZ2() {
		return Z2;
	}

	public void setZ2(double z2) {
		Z2 = z2;
		update_z();
	}
	
	// Vector Update Calculators


	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	private void update_x() {
		x = X2-X1;
	}
	
	private void update_y() {
		y = Y2-Y1;
	}
	
	private void update_z() {
		z = Z2-Z1;
	}
	
	public void Log_Vector() {
		
		System.out.println("Point 1 = "+"<<"+X1+","+Y1+","+Z1+">>");
		System.out.println("Point 2 = "+"<<"+X2+","+Y2+","+Z2+">>");
		System.out.println("Vector = "+"<<"+x+","+y+","+z+">>");
		System.out.println("");
	}
	
}
