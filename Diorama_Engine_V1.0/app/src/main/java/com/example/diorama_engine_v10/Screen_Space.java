package com.example.diorama_engine_v10;

public class Screen_Space {
	
	// Class to represent the Screen Space, i.e Screen Area, Screen Corner Coordinates
	
	Frustum_3D Camera;
	
	Point_3D TL; // Top Left Corner of the Screen
	Point_3D TR; // Top Right Corner of the Screen
	Point_3D BL; // Bottom Left Corner of the Screen
	Point_3D BR; // Bottom Right Corner of the Screen

	Point_3D NPTL; // Top Left Corner of the Near Plane
	Point_3D NPTR; // Top Right Corner of the Near Plane
	Point_3D NPBL; // Bottom Left Corner of the Near Plane
	Point_3D NPBR; // Bottom Right Corner of the Near Plane
	
	Point_3D Test_Intersect;
	Line_3D Test_Line;

	Plane_3D XY_Plane;
	
	// The Following Objects are representing coordinates where the Camera is the Origin

	Point_3D NPTL_Camera; // Top Left Corner of the Near Plane
	Point_3D NPTR_Camera; // Top Right Corner of the Near Plane
	Point_3D NPBL_Camera; // Bottom Left Corner of the Near Plane
	Point_3D NPBR_Camera; // Bottom Right Corner of the Near Plane
	
	Screen_Space(double iFX, double iFY, double iFZ) {
		
		Camera = new Frustum_3D(iFX,iFY,iFZ);

		XY_Plane = new Plane_3D(Camera.Focal.getX(),Camera.Focal.getY()+20,Camera.Focal.getZ(),
				Camera.Focal.getX()+20,Camera.Focal.getY()-20,Camera.Focal.Z,Camera.Focal.getZ()-20,
				Camera.Focal.getY()-20,Camera.Focal.getZ());

		Test_Line = new Line_3D(-9.112,-5.097,4.265,7.074,1.923,-5.003);
		
		P_and_L_Intersect Intersect_Test = new P_and_L_Intersect(XY_Plane,Test_Line);
		
		P_and_L_Intersect Intersect_A = new P_and_L_Intersect(XY_Plane,Camera.Cam_A);
		P_and_L_Intersect Intersect_B = new P_and_L_Intersect(XY_Plane,Camera.Cam_B);
		P_and_L_Intersect Intersect_C = new P_and_L_Intersect(XY_Plane,Camera.Cam_C);
		P_and_L_Intersect Intersect_D = new P_and_L_Intersect(XY_Plane,Camera.Cam_D);

		P_and_L_Intersect NP_Intersect_A = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_A);
		P_and_L_Intersect NP_Intersect_B = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_B);
		P_and_L_Intersect NP_Intersect_C = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_C);
		P_and_L_Intersect NP_Intersect_D = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_D);

		
		TL = new Point_3D(Intersect_A.Calc_XYZ(Camera.Cam_A)[0],Intersect_A.Calc_XYZ(Camera.Cam_A)[1],
				Intersect_A.Calc_XYZ(Camera.Cam_A)[2]);
		TR = new Point_3D(Intersect_B.Calc_XYZ(Camera.Cam_B)[0],Intersect_B.Calc_XYZ(Camera.Cam_B)[1],
				Intersect_B.Calc_XYZ(Camera.Cam_B)[2]);
		BL = new Point_3D(Intersect_C.Calc_XYZ(Camera.Cam_C)[0],Intersect_C.Calc_XYZ(Camera.Cam_C)[1],
				Intersect_C.Calc_XYZ(Camera.Cam_C)[2]);
		BR = new Point_3D(Intersect_D.Calc_XYZ(Camera.Cam_D)[0],Intersect_D.Calc_XYZ(Camera.Cam_D)[1],
				Intersect_D.Calc_XYZ(Camera.Cam_D)[2]);

		NPTL = new Point_3D(NP_Intersect_A.Calc_XYZ(Camera.Cam_A)[0],NP_Intersect_A.Calc_XYZ(Camera.Cam_A)[1],
				NP_Intersect_A.Calc_XYZ(Camera.Cam_A)[2]);
		NPTR = new Point_3D(NP_Intersect_B.Calc_XYZ(Camera.Cam_B)[0],NP_Intersect_B.Calc_XYZ(Camera.Cam_B)[1],
				NP_Intersect_B.Calc_XYZ(Camera.Cam_B)[2]);
		NPBL = new Point_3D(NP_Intersect_C.Calc_XYZ(Camera.Cam_C)[0],NP_Intersect_C.Calc_XYZ(Camera.Cam_C)[1],
				NP_Intersect_C.Calc_XYZ(Camera.Cam_C)[2]);
		NPBR = new Point_3D(NP_Intersect_D.Calc_XYZ(Camera.Cam_D)[0],NP_Intersect_D.Calc_XYZ(Camera.Cam_D)[1],
				NP_Intersect_D.Calc_XYZ(Camera.Cam_D)[2]);
		
		Test_Intersect = new Point_3D(Intersect_Test.Calc_XYZ(Test_Line)[0],Intersect_Test.Calc_XYZ(Test_Line)[1],
				Intersect_Test.Calc_XYZ(Test_Line)[2]);

		P_and_L_Intersect Intersect_NPTL_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_A_Camera);
		P_and_L_Intersect Intersect_NPTR_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_B_Camera);
		P_and_L_Intersect Intersect_NPBL_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_C_Camera);
		P_and_L_Intersect Intersect_NPBR_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_D_Camera);

		NPTL_Camera = new Point_3D(Intersect_NPTL_Camera.Calc_XYZ(Camera.Cam_A_Camera)[0],NP_Intersect_A.Calc_XYZ(Camera.Cam_A_Camera)[1],
				NP_Intersect_A.Calc_XYZ(Camera.Cam_A_Camera)[2]);
		NPTR_Camera = new Point_3D(Intersect_NPTR_Camera.Calc_XYZ(Camera.Cam_B_Camera)[0],NP_Intersect_B.Calc_XYZ(Camera.Cam_B_Camera)[1],
				NP_Intersect_B.Calc_XYZ(Camera.Cam_B_Camera)[2]);
		NPBL_Camera = new Point_3D(Intersect_NPBL_Camera.Calc_XYZ(Camera.Cam_C_Camera)[0],NP_Intersect_C.Calc_XYZ(Camera.Cam_C_Camera)[1],
				NP_Intersect_C.Calc_XYZ(Camera.Cam_C_Camera)[2]);
		NPBR_Camera = new Point_3D(Intersect_NPBR_Camera.Calc_XYZ(Camera.Cam_D_Camera)[0],NP_Intersect_D.Calc_XYZ(Camera.Cam_D_Camera)[1],
				NP_Intersect_D.Calc_XYZ(Camera.Cam_D_Camera)[2]);
		
		TL.Log_Point();
		TR.Log_Point();
		BL.Log_Point();
		BR.Log_Point();

		NPTL.Log_Point();
		NPTR.Log_Point();
		NPBL.Log_Point();
		NPBR.Log_Point();

		Test_Intersect.Log_Point();

		//Camera_Transformation_Test();


	}
	
	public void Bulk_Calculate_Line_Intersection() {
		P_and_L_Intersect Intersect_A = new P_and_L_Intersect(XY_Plane,Camera.Cam_A);
		P_and_L_Intersect Intersect_B = new P_and_L_Intersect(XY_Plane,Camera.Cam_B);
		P_and_L_Intersect Intersect_C = new P_and_L_Intersect(XY_Plane,Camera.Cam_C);
		P_and_L_Intersect Intersect_D = new P_and_L_Intersect(XY_Plane,Camera.Cam_D);

		P_and_L_Intersect NP_Intersect_A = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_A);
		P_and_L_Intersect NP_Intersect_B = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_B);
		P_and_L_Intersect NP_Intersect_C = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_C);
		P_and_L_Intersect NP_Intersect_D = new P_and_L_Intersect(Camera.Near_Plane,Camera.Cam_D);

		P_and_L_Intersect Intersect_NPTL_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_A_Camera);
		P_and_L_Intersect Intersect_NPTR_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_B_Camera);
		P_and_L_Intersect Intersect_NPBL_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_C_Camera);
		P_and_L_Intersect Intersect_NPBR_Camera = new P_and_L_Intersect(Camera.Near_Plane_Camera,Camera.Cam_D_Camera);

		TL.setX(Intersect_A.Calc_XYZ(Camera.Cam_A)[0]);
		TL.setY(Intersect_A.Calc_XYZ(Camera.Cam_A)[1]);
		TL.setZ(Intersect_A.Calc_XYZ(Camera.Cam_A)[2]);
		
		TR.setX(Intersect_B.Calc_XYZ(Camera.Cam_B)[0]);
		TR.setY(Intersect_B.Calc_XYZ(Camera.Cam_B)[1]);
		TR.setZ(Intersect_B.Calc_XYZ(Camera.Cam_B)[2]);
		
		BL.setX(Intersect_C.Calc_XYZ(Camera.Cam_B)[0]);
		BL.setY(Intersect_C.Calc_XYZ(Camera.Cam_B)[1]);
		BL.setZ(Intersect_C.Calc_XYZ(Camera.Cam_B)[2]);
		
		BR.setX(Intersect_D.Calc_XYZ(Camera.Cam_D)[0]);
		BR.setY(Intersect_D.Calc_XYZ(Camera.Cam_D)[1]);
		BR.setZ(Intersect_D.Calc_XYZ(Camera.Cam_D)[2]);

		NPTL.setX(NP_Intersect_A.Calc_XYZ(Camera.Cam_A)[0]);
		NPTL.setY(NP_Intersect_A.Calc_XYZ(Camera.Cam_A)[1]);
		NPTL.setZ(NP_Intersect_A.Calc_XYZ(Camera.Cam_A)[2]);

		NPTR.setX(NP_Intersect_B.Calc_XYZ(Camera.Cam_B)[0]);
		NPTR.setY(NP_Intersect_B.Calc_XYZ(Camera.Cam_B)[1]);
		NPTR.setZ(NP_Intersect_B.Calc_XYZ(Camera.Cam_B)[2]);

		NPBL.setX(NP_Intersect_C.Calc_XYZ(Camera.Cam_C)[0]);
		NPBL.setY(NP_Intersect_C.Calc_XYZ(Camera.Cam_C)[1]);
		NPBL.setZ(NP_Intersect_C.Calc_XYZ(Camera.Cam_C)[2]);

		NPBR.setX(NP_Intersect_D.Calc_XYZ(Camera.Cam_D)[0]);
		NPBR.setY(NP_Intersect_D.Calc_XYZ(Camera.Cam_D)[1]);
		NPBR.setZ(NP_Intersect_D.Calc_XYZ(Camera.Cam_D)[2]);

		NPTL_Camera.setX(Intersect_NPTL_Camera.Calc_XYZ(Camera.Cam_A_Camera)[0]);
		NPTL_Camera.setY(Intersect_NPTL_Camera.Calc_XYZ(Camera.Cam_A_Camera)[1]);
		NPTL_Camera.setZ(Intersect_NPTL_Camera.Calc_XYZ(Camera.Cam_A_Camera)[2]);

		NPTR_Camera.setX(Intersect_NPTR_Camera.Calc_XYZ(Camera.Cam_B_Camera)[0]);
		NPTR_Camera.setY(Intersect_NPTR_Camera.Calc_XYZ(Camera.Cam_B_Camera)[1]);
		NPTR_Camera.setZ(Intersect_NPTR_Camera.Calc_XYZ(Camera.Cam_B_Camera)[2]);

		NPBL_Camera.setX(Intersect_NPBL_Camera.Calc_XYZ(Camera.Cam_C_Camera)[0]);
		NPBL_Camera.setY(Intersect_NPBL_Camera.Calc_XYZ(Camera.Cam_C_Camera)[1]);
		NPBL_Camera.setZ(Intersect_NPBL_Camera.Calc_XYZ(Camera.Cam_C_Camera)[2]);

		NPBR_Camera.setX(Intersect_NPBR_Camera.Calc_XYZ(Camera.Cam_D_Camera)[0]);
		NPBR_Camera.setY(Intersect_NPBR_Camera.Calc_XYZ(Camera.Cam_D_Camera)[1]);
		NPBR_Camera.setZ(Intersect_NPBR_Camera.Calc_XYZ(Camera.Cam_D_Camera)[2]);

		/*
		TL.Log_Point();
		TR.Log_Point();
		BL.Log_Point();
		BR.Log_Point();

		NPTL.Log_Point();
		NPTR.Log_Point();
		NPBL.Log_Point();
		NPBR.Log_Point();

		NPTL_Camera.Log_Point();
		NPTR_Camera.Log_Point();
		NPBL_Camera.Log_Point();
		NPBR_Camera.Log_Point();
		*/
	}

	void Camera_Transformation_Test() {
		Transformations Initial_Transform = new Transformations();
		Initial_Transform.Inclination_Neg(Camera,this);
		Initial_Transform.Pivot_CW(Camera,this);

		Camera.Bulk_Camera_Transformation();


		System.out.println("Camera Origin World");
		Camera.Cam.Log_Point();

		System.out.println("BACK, UP, RIGHT Vectors");
		Camera.Back_Axis.Log_Vector();
		Camera.Up_Axis.Log_Vector();
		Camera.Right_Axis.Log_Vector();

		System.out.println("Cam Point Coordinate where Camera is Origin");
		Camera.Cam_Camera.Log_Point();
		System.out.println("Focal Point Coordinate where Camera is Origin");
		Camera.Focal_Camera.Log_Point();
		System.out.println("A Point Coordinate where Camera is Origin");
		Camera.A_Camera.Log_Point();
		System.out.println("B Point Coordinate where Camera is Origin");
		Camera.B_Camera.Log_Point();
		System.out.println("C Point Coordinate where Camera is Origin");
		Camera.C_Camera.Log_Point();
		System.out.println("D Point Coordinate where Camera is Origin");
		Camera.D_Camera.Log_Point();
	}
	
}
