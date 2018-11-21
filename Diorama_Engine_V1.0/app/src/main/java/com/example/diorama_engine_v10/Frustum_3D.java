package com.example.diorama_engine_v10;
import java.lang.Math;

public class Frustum_3D {
	
	// This class represents the Theoretical Viewing Frustum and holds the Line and Point data
	
	Point_3D Cam; // Camera point, all lines that are used to define the view Frustum converge on this point
	
	Point_3D Focal; // Point on which the Camera Focuses
	
	Point_3D Pivot_Point;
	Point_3D Tilt_Point;

	Point_3D Up_Point;
	Point_3D Right_Point;
	Point_3D Back_Point;

	Line_3D Up_Axis;
	Line_3D Right_Axis;
	Line_3D Back_Axis;
	
	Point_3D A; // Point 2 of Cam_A
	Point_3D B; // Point 2 of Cam_B
	Point_3D C; // Point 2 of Cam_C
	Point_3D D; // Point 2 of Cam_D
	
	Line_3D Pivot_Axis; // Axis on which the camera rotates around.
	Line_3D Tilt_Axis; // Axis on which the camera tilts
	
	Line_3D Cam_Focal;
	
	Line_3D Cam_A;
	Line_3D Cam_B;
	Line_3D Cam_C;
	Line_3D Cam_D;

	Plane_3D Near_Plane;

	// The Following Objects are representing coordinates where the Camera is the Origin

	Point_3D Cam_Camera; // SHOULD BE 0,0,0;

	Point_3D Focal_Camera; //  SHOULD BE 0,0,-10

	Point_3D A_Camera; // Point 2 of Cam_A
	Point_3D B_Camera; // Point 2 of Cam_B
	Point_3D C_Camera; // Point 2 of Cam_C
	Point_3D D_Camera; // Point 2 of Cam_D

	Line_3D Cam_Focal_Camera;

	Line_3D Cam_A_Camera;
	Line_3D Cam_B_Camera;
	Line_3D Cam_C_Camera;
	Line_3D Cam_D_Camera;

	Plane_3D Near_Plane_Camera;

	// Other variables used for camera control

	double Cam_Radial_Distance;
	double Cam_Polar_Angle;
	double Cam_Azimuth_Angle;
	double Near_Plane_Height;
	
	Frustum_3D(double ifocal_X,double ifocal_Y,double ifocal_Z) {

		Near_Plane_Height = 0.70;

		Focal = new Point_3D(ifocal_X,ifocal_Y,ifocal_Z);
		
		Cam = new Point_3D(ifocal_X,ifocal_Y,ifocal_Z+10);
		
		A = new Point_3D(ifocal_X-16,ifocal_Y+10,-10);
		B = new Point_3D(ifocal_X+16,ifocal_Y+10,-10);
		C = new Point_3D(ifocal_X-16,ifocal_Y-10,-10);
		D = new Point_3D(ifocal_X+16,ifocal_Y-10,-10);
		
		Cam_Focal = new Line_3D(Cam.X,Cam.Y,Cam.Z,ifocal_X,
				ifocal_Y,ifocal_Z);
		
		Pivot_Point = new Point_3D(ifocal_X,ifocal_Y,ifocal_Z+10);
		Tilt_Point = new Point_3D(ifocal_X+8,ifocal_Y,ifocal_Z);

		Up_Point = new Point_3D(Cam.getX(),Cam.getY()+10,Cam.getZ());
		Right_Point = new Point_3D(Cam.getX()+10,Cam.getY(),Cam.getZ());
		Back_Point = new Point_3D(Cam.getX(),Cam.getY(),Cam.getZ()+10);
		
		Pivot_Axis = new Line_3D(Focal.X,Focal.Y,Focal.Z,Pivot_Point.X,Pivot_Point.Y,Pivot_Point.Z);
		Tilt_Axis = new Line_3D(Focal.X,Focal.Y,Focal.Z,Tilt_Point.X,Tilt_Point.Y,Tilt_Point.Z);

		Up_Axis = new Line_3D(Cam.getX(),Cam.getY(),Cam.getZ(),Up_Point.getX(),Up_Point.getY(),Up_Point.getZ());
		Right_Axis = new Line_3D(Cam.getX(),Cam.getY(),Cam.getZ(),Right_Point.getX(),Right_Point.getY(),Right_Point.getZ());
		Back_Axis = new Line_3D(Cam.getX(),Cam.getY(),Cam.getZ(),Back_Point.getX(),Back_Point.getY(),Back_Point.getZ());
		
		Cam_A = new Line_3D(Cam.X,Cam.Y,Cam.Z,A.X,A.Y,A.Z);
		Cam_B = new Line_3D(Cam.X,Cam.Y,Cam.Z,B.X,B.Y,B.Z);
		Cam_C = new Line_3D(Cam.X,Cam.Y,Cam.Z,C.X,C.Y,C.Z);
		Cam_D = new Line_3D(Cam.X,Cam.Y,Cam.Z,D.X,D.Y,D.Z);

		Near_Plane = new Plane_3D(Cam.getX()+5,Cam.getY()+5,Cam.getZ()*Near_Plane_Height,
				Cam.getX()+3,Cam.getY()-6,Cam.getZ()*Near_Plane_Height,
				Cam.getX()+7,Cam.getY()+1,Cam.getZ()*Near_Plane_Height);
		
		Log_Points();
		Log_Vectors();


		Camera_Transformation Cam_Trans = new Camera_Transformation(this);

		double a[] = new double[3];

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Cam)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Cam)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Cam)[2];
		Cam_Camera = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Focal)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Focal)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Focal)[2];
		Focal_Camera = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(A)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(A)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(A)[2];

		A_Camera = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(B)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(B)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(B)[2];
		B_Camera = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(C)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(C)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(C)[2];
		C_Camera = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(D)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(D)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(D)[2];
		D_Camera = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_1)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_1)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_1)[2];
		Point_3D C_Plane_1 = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_2)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_2)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_2)[2];
		Point_3D C_Plane_2 = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_3)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_3)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_3)[2];
		Point_3D C_Plane_3 = new Point_3D(a[0],a[1],a[2]);

		Near_Plane_Camera = new Plane_3D(C_Plane_1.getX(),C_Plane_1.getY(),C_Plane_1.getZ(),
				C_Plane_2.getX(),C_Plane_2.getY(),C_Plane_2.getZ(),C_Plane_3.getX(),C_Plane_3.getY(),C_Plane_3.getZ());

		//Cam_Camera.Log_Point();

		Cam_Focal_Camera = new Line_3D(Cam_Camera.getX(),Cam_Camera.getY(),Cam_Camera.getZ(),Cam_Focal.getX(),
				Cam_Focal.getY(),Cam_Focal.getZ());

		Cam_A_Camera = new Line_3D(Cam_Camera.getX(),Cam_Camera.getY(),Cam_Camera.getZ(),A.getX(),
				A.getY(),A.getZ());
		Cam_B_Camera = new Line_3D(Cam_Camera.getX(),Cam_Camera.getY(),Cam_Camera.getZ(),B.getX(),
				B.getY(),B.getZ());
		Cam_C_Camera = new Line_3D(Cam_Camera.getX(),Cam_Camera.getY(),Cam_Camera.getZ(),C.getX(),
				C.getY(),C.getZ());
		Cam_D_Camera = new Line_3D(Cam_Camera.getX(),Cam_Camera.getY(),Cam_Camera.getZ(),D.getX(),
				D.getY(),D.getZ());

	}
	
	public void Bulk_Update_Vectors() {
		
		// Updates all the Line_3D Objects in the Frustum_3D Class
		Update_Vector(Cam_Focal,Cam,Focal);
		Update_Vector(Pivot_Axis,Focal,Pivot_Point);
		Update_Vector(Tilt_Axis,Focal,Tilt_Point);
		Update_Vector(Cam_A,Cam,A);
		Update_Vector(Cam_B,Cam,B);
		Update_Vector(Cam_C,Cam,C);
		Update_Vector(Cam_D,Cam,D);
		Update_Vector(Up_Axis,Cam,Up_Point);
		Update_Vector(Right_Axis,Cam,Right_Point);
		Update_Vector(Back_Axis,Cam,Back_Point);
	}
	
	public void Update_Vector(Line_3D iLine, Point_3D iPoint_1, Point_3D iPoint_2) {
		
		// Updates Line_3D Objects with Updated Point_3D Objects
		
		iLine.setX1(iPoint_1.getX());
		iLine.setY1(iPoint_1.getY());
		iLine.setZ1(iPoint_1.getZ());

		iLine.setX2(iPoint_2.getX());
		iLine.setY2(iPoint_2.getY());
		iLine.setZ2(iPoint_2.getZ());
	}
	
	public void Log_Points() {

		/*
		Cam.Log_Point();
		Focal.Log_Point();
		Pivot_Point.Log_Point();
		Tilt_Point.Log_Point();
		A.Log_Point();
		B.Log_Point();
		C.Log_Point();
		D.Log_Point();
		Up_Point.Log_Point();
		Right_Point.Log_Point();
		Back_Point.Log_Point();
		*/
	}
	
	public void Log_Vectors() {

		/*
		Cam_Focal.Log_Vector();
		Pivot_Axis.Log_Vector();
		Tilt_Axis.Log_Vector();
		Cam_A.Log_Vector();
		Cam_B.Log_Vector();
		Cam_C.Log_Vector();
		Cam_D.Log_Vector();
		Up_Axis.Log_Vector();
		Right_Axis.Log_Vector();
		Back_Axis.Log_Vector();
		*/
	}

	public void Bulk_Camera_Transformation(){

		Camera_Transformation Cam_Trans = new Camera_Transformation(this);

		Set_Camera_Coordinates(Cam,Cam_Camera,Cam_Trans);
		Set_Camera_Coordinates(Focal,Focal_Camera,Cam_Trans);
		Set_Camera_Coordinates(A,A_Camera,Cam_Trans);
		Set_Camera_Coordinates(B,B_Camera,Cam_Trans);
		Set_Camera_Coordinates(C,C_Camera,Cam_Trans);
		Set_Camera_Coordinates(D,D_Camera,Cam_Trans);

		double a[] = new double[3];

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_1)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_1)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_1)[2];
		Point_3D C_Plane_1 = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_2)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_2)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_2)[2];
		Point_3D C_Plane_2 = new Point_3D(a[0],a[1],a[2]);

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_3)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_3)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(Near_Plane.Point_3)[2];
		Point_3D C_Plane_3 = new Point_3D(a[0],a[1],a[2]);

		Near_Plane_Camera = new Plane_3D(C_Plane_1.getX(),C_Plane_1.getY(),C_Plane_1.getZ(),
				C_Plane_2.getX(),C_Plane_2.getY(),C_Plane_2.getZ(),C_Plane_3.getX(),C_Plane_3.getY(),C_Plane_3.getZ());

		Update_Vector(Cam_Focal_Camera,Cam_Camera,Focal_Camera);
		Update_Vector(Cam_A_Camera,Cam_Camera,A_Camera);
		Update_Vector(Cam_B_Camera,Cam_Camera,B_Camera);
		Update_Vector(Cam_C_Camera,Cam_Camera,C_Camera);
		Update_Vector(Cam_D_Camera,Cam_Camera,D_Camera);
	}

	void Set_Camera_Coordinates(Point_3D iPoint_3D_W,Point_3D iPoint_3D_C,Camera_Transformation Cam_Trans) {
		double a[] = new double[3];

		a[0] = Cam_Trans.Calc_Camera_Space_Coordinates(iPoint_3D_W)[0];
		a[1] = Cam_Trans.Calc_Camera_Space_Coordinates(iPoint_3D_W)[1];
		a[2] = Cam_Trans.Calc_Camera_Space_Coordinates(iPoint_3D_W)[2];

		iPoint_3D_C.setX(a[0]);
		iPoint_3D_C.setY(a[1]);
		iPoint_3D_C.setZ(a[2]);
	}

	public double getNear_Plane_Height() {
		return Near_Plane_Height;
	}
}
