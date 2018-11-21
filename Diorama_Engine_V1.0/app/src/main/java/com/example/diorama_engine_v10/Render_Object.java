package com.example.diorama_engine_v10;

public class Render_Object {

	//Class to store data for individual objects

	//String Object_Name;
	//String File_Path;

	//String Image_Name;
	//int Total_Files;

	//boolean On_Screen;

	//Point_3D Position;

	Virtual_Bounding_Box BB;

	Render_Object(Frustum_3D iFrustum){

		// Hardcoded Input for the test object

		Point_3D [] HPI = new Point_3D[8];

		HPI[0] = new Point_3D(-2.219,-0.141,2.430);
		HPI[1] = new Point_3D(0.478,-2.171,2.430);
		HPI[2] = new Point_3D(-0.478,2.171,2.430);
		HPI[3] = new Point_3D(2.219,0.141,2.430);
		HPI[4] = new Point_3D(-0.478,2.171,-0.000);
		HPI[5] = new Point_3D(2.219,0.141,0.000);
		HPI[6] = new Point_3D(-2.219,-0.141,0.000);
		HPI[7] = new Point_3D(-2.171,-0.141,-0.000);

		BB = new Virtual_Bounding_Box(HPI,iFrustum);
	}

}
