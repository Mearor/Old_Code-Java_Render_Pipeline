package com.example.diorama_engine_v10;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class P_and_L_Intersect {

	/* This class is used to find the intersect between a 3D line and a plane.
	* The equations to calculate the XYZ intersect requires valuating T.
	* In this case T is equal to the determinant of Matrix A multiplied by the inverse
	* of Matrix B.
	*/
	
	double [][] a = new double [4][4];
	double [][] b = new double [4][4];
	double [][] iB = new double [4][4]; // Inverse of Matrix B]
	double [][] axiB = new double [4][4]; // The Result of A*B^-1

	double t;
	
	public P_and_L_Intersect(Plane_3D iPlane_3D,Line_3D iLine_3D) {
		
		// The input requires Values for 
		
		// Variables for Matrix A
		
		double m21 = iPlane_3D.Point_1.getX();
		double m22 = iPlane_3D.Point_2.getX();
		double m23 = iPlane_3D.Point_3.getX();
		double m24 = iLine_3D.getX1();
		
		double m31 = iPlane_3D.Point_1.getY();
		double m32 = iPlane_3D.Point_2.getY();
		double m33 = iPlane_3D.Point_3.getY();
		double m34 = iLine_3D.getY1();
		
		double m41 = iPlane_3D.Point_1.getZ();
		double m42 = iPlane_3D.Point_2.getZ();
		double m43 = iPlane_3D.Point_3.getZ();
		double m44 = iLine_3D.getZ1();
		
		// Variables for Matrix B

		double n21 = iPlane_3D.Point_1.getX();
		double n22 = iPlane_3D.Point_2.getX();
		double n23 = iPlane_3D.Point_3.getX();
		double n24 = iLine_3D.getX();

		double n31 = iPlane_3D.Point_1.getY();
		double n32 = iPlane_3D.Point_2.getY();
		double n33 = iPlane_3D.Point_3.getY();
		double n34 = iLine_3D.getY();

		double n41 = iPlane_3D.Point_1.getZ();
		double n42 = iPlane_3D.Point_2.getZ();
		double n43 = iPlane_3D.Point_3.getZ();
		double n44 = iLine_3D.getZ();
		
		// Applying these variables into a 2 Dimensional Array
		
		a[0][0] = 1; a[0][1] = 1; a[0][2] = 1; a[0][3] = 1;
		a[1][0] = m21; a[1][1] = m22; a[1][2] = m23; a[1][3] = m24;
		a[2][0] = m31; a[2][1] = m32; a[2][2] = m33; a[2][3] = m34;
		a[3][0] = m41; a[3][1] = m42; a[3][2] = m43; a[3][3] = m44;
		
		b[0][0] = 1; b[0][1] = 1; b[0][2] = 1; b[0][3] = 0;
		b[1][0] = n21; b[1][1] = n22; b[1][2] = n23; b[1][3] = n24;
		b[2][0] = n31; b[2][1] = n32; b[2][2] = n33; b[2][3] = n34;
		b[3][0] = n41; b[3][1] = n42; b[3][2] = n43; b[3][3] = n44;
		
		
		
		//System.out.println(a[0][0]+","+a[0][1]+","+a[0][2]+","+a[0][3]);
		//System.out.println(a[1][0]+","+a[1][1]+","+a[1][2]+","+a[1][3]);
		//System.out.println(a[2][0]+","+a[2][1]+","+a[2][2]+","+a[2][3]);
		//System.out.println(a[3][0]+","+a[3][1]+","+a[3][2]+","+a[3][3]);
		
		//System.out.println("");

		//System.out.println(b[0][0]+","+b[0][1]+","+b[0][2]+","+b[0][3]);
		//System.out.println(b[1][0]+","+b[1][1]+","+b[1][2]+","+b[1][3]);
		//System.out.println(b[2][0]+","+b[2][1]+","+b[2][2]+","+b[2][3]);
		//System.out.println(b[3][0]+","+b[3][1]+","+b[3][2]+","+b[3][3]);
		
		System.out.println("");
		
		
		iB = calc_Inverse(b);
		
		//System.out.println(iB[0][0]+","+iB[0][1]+","+iB[0][2]+","+iB[0][3]);
		//System.out.println(iB[1][0]+","+iB[1][1]+","+iB[1][2]+","+iB[1][3]);
		//System.out.println(iB[2][0]+","+iB[2][1]+","+iB[2][2]+","+iB[2][3]);
		//System.out.println(iB[3][0]+","+iB[3][1]+","+iB[3][2]+","+iB[3][3]);
		
		System.out.println("");
		
		axiB = Multiply_Matrices(a,iB);
		
		//System.out.println(axiB[0][0]+","+axiB[0][1]+","+axiB[0][2]+","+axiB[0][3]);
		//System.out.println(axiB[1][0]+","+axiB[1][1]+","+axiB[1][2]+","+axiB[1][3]);
		//System.out.println(axiB[2][0]+","+axiB[2][1]+","+axiB[2][2]+","+axiB[2][3]);
		//System.out.println(axiB[3][0]+","+axiB[3][1]+","+axiB[3][2]+","+axiB[3][3]);
		
		t = Calc_T(axiB);
	}
	
	double [][] calc_Inverse(double [][] b) {
		
		double [][] ib = new double [4][4];
		
		double detb = calc_D(b);
		
		System.out.println(detb+"");
		System.out.println("");
		
		// The Following Variables form the intemediary matrix which will be multiplied by the determinant to
		// provide the inverse of Matrix A and B.
		// 

		double i11 = (b[1][1]*b[2][2]*b[3][3])+(b[1][2]*b[2][3]*b[3][1])+(b[1][3]*b[2][1]*b[3][2])-
				(b[1][1]*b[2][3]*b[3][2])-(b[1][2]*b[2][1]*b[3][3])-(b[1][3]*b[2][2]*b[3][1]);
		double i12 = (b[0][1]*b[2][3]*b[3][2])+(b[0][2]*b[2][1]*b[3][3])+(b[0][3]*b[2][2]*b[3][1])-
				(b[0][1]*b[2][2]*b[3][3])-(b[0][2]*b[2][3]*b[3][1])-(b[0][3]*b[2][1]*b[3][2]);
		double i13 = (b[0][1]*b[1][2]*b[3][3])+(b[0][2]*b[1][3]*b[3][1])+(b[0][3]*b[1][1]*b[3][2])-
				(b[0][1]*b[1][3]*b[3][2])-(b[0][2]*b[1][1]*b[3][3])-(b[0][3]*b[1][2]*b[3][1]);
		double i14 = (b[0][1]*b[1][3]*b[2][2])+(b[0][2]*b[1][1]*b[2][3])+(b[0][3]*b[1][2]*b[2][1])-
				(b[0][1]*b[1][2]*b[2][3])-(b[0][2]*b[1][3]*b[2][1])-(b[0][3]*b[1][1]*b[2][2]);
		
		double i21 = (b[1][0]*b[2][3]*b[3][2])+(b[1][2]*b[2][0]*b[3][3])+(b[1][3]*b[2][2]*b[3][0])-
				(b[1][0]*b[2][2]*b[3][3])-(b[1][2]*b[2][3]*b[3][0])-(b[1][3]*b[2][0]*b[3][2]);
		double i22 = (b[0][0]*b[2][2]*b[3][3])+(b[0][2]*b[2][3]*b[3][0])+(b[0][3]*b[2][0]*b[3][2])-
				(b[0][0]*b[2][3]*b[3][2])-(b[0][2]*b[2][0]*b[3][3])-(b[0][3]*b[2][2]*b[3][0]);
		double i23 = (b[0][0]*b[1][3]*b[3][2])+(b[0][2]*b[1][0]*b[3][3])+(b[0][3]*b[1][2]*b[3][0])-
				(b[0][0]*b[1][2]*b[3][3])-(b[0][2]*b[1][3]*b[3][0])-(b[0][3]*b[1][0]*b[3][2]);
		double i24 = (b[0][0]*b[1][2]*b[2][3])+(b[0][2]*b[1][3]*b[2][0])+(b[0][3]*b[1][0]*b[2][2])-
				(b[0][0]*b[1][3]*b[2][2])-(b[0][2]*b[1][0]*b[2][3])-(b[0][3]*b[1][2]*b[2][0]);
		
		double i31 = (b[1][0]*b[2][1]*b[3][3])+(b[1][1]*b[2][3]*b[3][0])+(b[1][3]*b[2][0]*b[3][1])-
				(b[1][0]*b[2][3]*b[3][1])-(b[1][1]*b[2][0]*b[3][3])-(b[1][3]*b[2][1]*b[3][0]);
		double i32 = (b[0][0]*b[2][3]*b[3][1])+(b[0][1]*b[2][0]*b[3][3])+(b[0][3]*b[2][1]*b[3][0])-
				(b[0][0]*b[2][1]*b[3][3])-(b[0][1]*b[2][3]*b[3][0])-(b[0][3]*b[2][0]*b[3][1]);
		double i33 = (b[0][0]*b[1][1]*b[3][3])+(b[0][1]*b[1][3]*b[3][0])+(b[0][3]*b[1][0]*b[3][1])-
				(b[0][0]*b[1][3]*b[3][1])-(b[0][1]*b[1][0]*b[3][3])-(b[0][3]*b[1][1]*b[3][0]);
		double i34 = (b[0][0]*b[1][3]*b[2][1])+(b[0][1]*b[1][0]*b[2][3])+(b[0][3]*b[1][1]*b[2][0])-
				(b[0][0]*b[1][1]*b[2][3])-(b[0][1]*b[1][3]*b[2][0])-(b[0][3]*b[1][0]*b[2][1]);
		
		double i41 = (b[1][0]*b[2][2]*b[3][1])+(b[1][1]*b[2][0]*b[3][2])+(b[1][2]*b[2][1]*b[3][0])-
				(b[1][0]*b[2][1]*b[3][2])-(b[1][1]*b[2][2]*b[3][0])-(b[1][2]*b[2][0]*b[3][1]);
		double i42 = (b[0][0]*b[2][1]*b[3][2])+(b[0][1]*b[2][2]*b[3][0])+(b[0][2]*b[2][0]*b[3][1])-
				(b[0][0]*b[2][2]*b[3][1])-(b[0][1]*b[2][0]*b[3][2])-(b[0][2]*b[2][1]*b[3][0]);
		double i43 = (b[0][0]*b[1][2]*b[3][1])+(b[0][1]*b[1][0]*b[3][2])+(b[0][2]*b[1][1]*b[3][0])-
				(b[0][0]*b[1][1]*b[3][2])-(b[0][1]*b[1][2]*b[3][0])-(b[0][2]*b[1][0]*b[3][1]);
		double i44 = (b[0][0]*b[1][1]*b[2][2])+(b[0][1]*b[1][2]*b[2][0])+(b[0][2]*b[1][0]*b[2][1])-
				(b[0][0]*b[1][2]*b[2][1])-(b[0][1]*b[1][0]*b[2][2])-(b[0][2]*b[1][1]*b[2][0]);
		
		ib[0][0] = i11; ib[0][1] = i12; ib[0][2] = i13; ib[0][3] = i14;
		ib[1][0] = i21; ib[1][1] = i22; ib[1][2] = i23; ib[1][3] = i24;
		ib[2][0] = i31; ib[2][1] = i32; ib[2][2] = i33; ib[2][3] = i34;
		ib[3][0] = i41; ib[3][1] = i42; ib[3][2] = i43; ib[3][3] = i44;
		
		for (int c = 0;c <= 3; c++) {
			for (int d = 0;d <= 3; d++) {
				ib[c][d] = ib[c][d]*(1/detb);
				//ib[c][d] = round(ib[c][d],2);
			}
		}
		
		return ib;
		
	}
	
	double calc_D(double [][] b) {
		
		// Calculates the determinant of a Matrix
		
		double detb = (b[0][0]*b[1][1]*b[2][2]*b[3][3])+(b[0][0]*b[1][2]*b[2][3]*b[3][1])+
				(b[0][0]*b[1][3]*b[2][1]*b[3][2])+(b[0][1]*b[1][0]*b[2][3]*b[3][2])+
				(b[0][1]*b[1][2]*b[2][0]*b[3][3])+(b[0][1]*b[1][3]*b[2][2]*b[3][0])+
				(b[0][2]*b[1][0]*b[2][1]*b[3][3])+(b[0][2]*b[1][1]*b[2][3]*b[3][0])+
				(b[0][2]*b[1][3]*b[2][0]*b[3][1])+(b[0][3]*b[1][0]*b[2][2]*b[3][1])+
				(b[0][3]*b[1][1]*b[2][0]*b[3][2])+(b[0][3]*b[1][2]*b[2][1]*b[3][0])-
				(b[0][0]*b[1][1]*b[2][3]*b[3][2])-(b[0][0]*b[1][2]*b[2][1]*b[3][3])-
				(b[0][0]*b[1][3]*b[2][2]*b[3][1])-(b[0][1]*b[1][0]*b[2][2]*b[3][3])-
				(b[0][1]*b[1][2]*b[2][3]*b[3][0])-(b[0][1]*b[1][3]*b[2][0]*b[3][2])-
				(b[0][2]*b[1][0]*b[2][3]*b[3][1])-(b[0][2]*b[1][1]*b[2][0]*b[3][3])-
				(b[0][2]*b[1][3]*b[2][1]*b[3][0])-(b[0][3]*b[1][0]*b[2][1]*b[3][2])-
				(b[0][3]*b[1][1]*b[2][2]*b[3][0])-(b[0][3]*b[1][2]*b[2][0]*b[3][1]);
		
		return detb;
	}
	
	double [][] Multiply_Matrices(double [][] a,double [][] b) {
		
		double [][] axiB = new double[4][4];
		
		for (int c = 0;c <= 3; c++) {
			for (int d = 0;d <= 3; d++) {
					
				axiB[c][d] = (a[c][0]*b[0][d])+(a[c][1]*b[1][d])+(a[c][2]*b[2][d])+(a[c][3]*b[3][d]);
					
			}
		}
		
		return axiB;
	}
	
	public static double round(double value, int places) {
		
		// Code taken off Stack Exchange to round values to make them easy to read.
		
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	double Calc_T(double [][] b) {
		
		double t = -(calc_D(b));
		
		return t;
	}
	
	public double [] Calc_XYZ(Line_3D iLine_3D) {
		
		// Calculates XYZ Coordinates once T has been valuated.
		
		double output[] = new double [3];
		
		output[0] = iLine_3D.X1 + iLine_3D.x * t;
		output[1] = iLine_3D.Y1 + iLine_3D.y * t;
		output[2] = iLine_3D.Z1 + iLine_3D.z * t;
				
		return output;
	}
}
