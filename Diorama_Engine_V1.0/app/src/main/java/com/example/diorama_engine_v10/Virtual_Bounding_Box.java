package com.example.diorama_engine_v10;

public class Virtual_Bounding_Box {

	// The process relies on a virtual bounding box to be defined, this is used to carry over the depth of the original model.

	// These two arrays represent the point data in World Coordinates
	Point_3D [] BBPs_World = new Point_3D[8];
	Line_3D [] BBLs_World = new Line_3D[12];

	// These two arrays represent the point data with the Camera as the origin.
	Point_3D [] BBPs_Camera = new Point_3D[8];
	Line_3D [] BBLs_Camera = new Line_3D[12];

	// These two arrays store Screen Coordinates
	Point_3D [] BBPs_Screen = new Point_3D[8];
	Line_3D [] BBLs_Screen = new Line_3D[12];

	Virtual_Bounding_Box(Point_3D[] iPA,Frustum_3D iFrustum) {

		for (int x=0;x<=7;x++) {
			BBPs_World[x] = iPA[x];
		}

		BBLs_World[0] = new Line_3D(iPA[0].getX(),iPA[0].getY(),iPA[0].getZ(),iPA[1].getX(),iPA[1].getY(),iPA[1].getZ());
		BBLs_World[1] = new Line_3D(iPA[0].getX(),iPA[0].getY(),iPA[0].getZ(),iPA[2].getX(),iPA[2].getY(),iPA[2].getZ());
		BBLs_World[2] = new Line_3D(iPA[3].getX(),iPA[3].getY(),iPA[3].getZ(),iPA[1].getX(),iPA[1].getY(),iPA[1].getZ());
		BBLs_World[3] = new Line_3D(iPA[3].getX(),iPA[3].getY(),iPA[3].getZ(),iPA[2].getX(),iPA[2].getY(),iPA[2].getZ());
		BBLs_World[4] = new Line_3D(iPA[6].getX(),iPA[6].getY(),iPA[6].getZ(),iPA[7].getX(),iPA[7].getY(),iPA[7].getZ());
		BBLs_World[5] = new Line_3D(iPA[6].getX(),iPA[6].getY(),iPA[6].getZ(),iPA[4].getX(),iPA[4].getY(),iPA[4].getZ());
		BBLs_World[6] = new Line_3D(iPA[5].getX(),iPA[5].getY(),iPA[5].getZ(),iPA[7].getX(),iPA[7].getY(),iPA[7].getZ());
		BBLs_World[7] = new Line_3D(iPA[5].getX(),iPA[5].getY(),iPA[5].getZ(),iPA[4].getX(),iPA[4].getY(),iPA[4].getZ());
		BBLs_World[8] = new Line_3D(iPA[0].getX(),iPA[0].getY(),iPA[0].getZ(),iPA[6].getX(),iPA[6].getY(),iPA[6].getZ());
		BBLs_World[9] = new Line_3D(iPA[2].getX(),iPA[2].getY(),iPA[2].getZ(),iPA[4].getX(),iPA[4].getY(),iPA[4].getZ());
		BBLs_World[10] = new Line_3D(iPA[1].getX(),iPA[1].getY(),iPA[1].getZ(),iPA[7].getX(),iPA[7].getY(),iPA[7].getZ());
		BBLs_World[11] = new Line_3D(iPA[3].getX(),iPA[3].getY(),iPA[3].getZ(),iPA[5].getX(),iPA[5].getY(),iPA[5].getZ());

		Camera_Transformation Cam_Trans = new Camera_Transformation(iFrustum);

		// Conversion into Camera Space Coordinates

		for (int y=0;y<=7;y++) {
			BBPs_Camera[y] = new Point_3D(Cam_Trans.Calc_Camera_Space_Coordinates(BBPs_World[y])[0],Cam_Trans.Calc_Camera_Space_Coordinates(BBPs_World[y])[1],
					Cam_Trans.Calc_Camera_Space_Coordinates(BBPs_World[y])[2]);

			//System.out.println(y);
			//System.out.println("BBPs Points");
			//BBPs_Camera[y].Log_Point();
		}

		BBLs_Camera[0] = new Line_3D(BBPs_Camera[0].getX(),BBPs_Camera[0].getY(),BBPs_Camera[0].getZ(),BBPs_Camera[1].getX(),BBPs_Camera[1].getY(),BBPs_Camera[1].getZ());
		BBLs_Camera[1] = new Line_3D(BBPs_Camera[0].getX(),BBPs_Camera[0].getY(),BBPs_Camera[0].getZ(),BBPs_Camera[2].getX(),BBPs_Camera[2].getY(),BBPs_Camera[2].getZ());
		BBLs_Camera[2] = new Line_3D(BBPs_Camera[3].getX(),BBPs_Camera[3].getY(),BBPs_Camera[3].getZ(),BBPs_Camera[1].getX(),BBPs_Camera[1].getY(),BBPs_Camera[1].getZ());
		BBLs_Camera[3] = new Line_3D(BBPs_Camera[3].getX(),BBPs_Camera[3].getY(),BBPs_Camera[3].getZ(),BBPs_Camera[2].getX(),BBPs_Camera[2].getY(),BBPs_Camera[2].getZ());
		BBLs_Camera[4] = new Line_3D(BBPs_Camera[6].getX(),BBPs_Camera[6].getY(),BBPs_Camera[6].getZ(),BBPs_Camera[7].getX(),BBPs_Camera[7].getY(),BBPs_Camera[7].getZ());
		BBLs_Camera[5] = new Line_3D(BBPs_Camera[6].getX(),BBPs_Camera[6].getY(),BBPs_Camera[6].getZ(),BBPs_Camera[4].getX(),BBPs_Camera[4].getY(),BBPs_Camera[4].getZ());
		BBLs_Camera[6] = new Line_3D(BBPs_Camera[5].getX(),BBPs_Camera[5].getY(),BBPs_Camera[5].getZ(),BBPs_Camera[7].getX(),BBPs_Camera[7].getY(),BBPs_Camera[7].getZ());
		BBLs_Camera[7] = new Line_3D(BBPs_Camera[5].getX(),BBPs_Camera[5].getY(),BBPs_Camera[5].getZ(),BBPs_Camera[4].getX(),BBPs_Camera[4].getY(),BBPs_Camera[4].getZ());
		BBLs_Camera[8] = new Line_3D(BBPs_Camera[0].getX(),BBPs_Camera[0].getY(),BBPs_Camera[0].getZ(),BBPs_Camera[6].getX(),BBPs_Camera[6].getY(),BBPs_Camera[6].getZ());
		BBLs_Camera[9] = new Line_3D(BBPs_Camera[2].getX(),BBPs_Camera[2].getY(),BBPs_Camera[2].getZ(),BBPs_Camera[4].getX(),BBPs_Camera[4].getY(),BBPs_Camera[4].getZ());
		BBLs_Camera[10] = new Line_3D(BBPs_Camera[1].getX(),BBPs_Camera[1].getY(),BBPs_Camera[1].getZ(),BBPs_Camera[7].getX(),BBPs_Camera[7].getY(),BBPs_Camera[7].getZ());
		BBLs_Camera[11] = new Line_3D(BBPs_Camera[3].getX(),BBPs_Camera[3].getY(),BBPs_Camera[3].getZ(),BBPs_Camera[5].getX(),BBPs_Camera[5].getY(),BBPs_Camera[5].getZ());

		for (int z=0;z<=7;z++) {

			double D = (Math.sqrt((iFrustum.Cam_Focal_Camera.getX()*iFrustum.Cam_Focal_Camera.getX())+(iFrustum.Cam_Focal_Camera.getY()*iFrustum.Cam_Focal_Camera.getY())+
					(iFrustum.Cam_Focal_Camera.getZ()*iFrustum.Cam_Focal_Camera.getZ())))*iFrustum.getNear_Plane_Height();

			double X = BBPs_Camera[z].getX()*D/BBPs_Camera[z].getZ();
			double Y = BBPs_Camera[z].getY()*D/BBPs_Camera[z].getZ();

			BBPs_Screen[z]= new Point_3D(X,Y,BBPs_Camera[z].getZ());
		}

		BBLs_Screen[0] = new Line_3D(BBPs_Screen[0].getX(),BBPs_Screen[0].getY(),BBPs_Screen[0].getZ(),BBPs_Screen[1].getX(),BBPs_Screen[1].getY(),BBPs_Screen[1].getZ());
		BBLs_Screen[1] = new Line_3D(BBPs_Screen[0].getX(),BBPs_Screen[0].getY(),BBPs_Screen[0].getZ(),BBPs_Screen[2].getX(),BBPs_Screen[2].getY(),BBPs_Screen[2].getZ());
		BBLs_Screen[2] = new Line_3D(BBPs_Screen[3].getX(),BBPs_Screen[3].getY(),BBPs_Screen[3].getZ(),BBPs_Screen[1].getX(),BBPs_Screen[1].getY(),BBPs_Screen[1].getZ());
		BBLs_Screen[3] = new Line_3D(BBPs_Screen[3].getX(),BBPs_Screen[3].getY(),BBPs_Screen[3].getZ(),BBPs_Screen[2].getX(),BBPs_Screen[2].getY(),BBPs_Screen[2].getZ());
		BBLs_Screen[4] = new Line_3D(BBPs_Screen[6].getX(),BBPs_Screen[6].getY(),BBPs_Screen[6].getZ(),BBPs_Screen[7].getX(),BBPs_Screen[7].getY(),BBPs_Screen[7].getZ());
		BBLs_Screen[5] = new Line_3D(BBPs_Screen[6].getX(),BBPs_Screen[6].getY(),BBPs_Screen[6].getZ(),BBPs_Screen[4].getX(),BBPs_Screen[4].getY(),BBPs_Screen[4].getZ());
		BBLs_Camera[6] = new Line_3D(BBPs_Screen[5].getX(),BBPs_Screen[5].getY(),BBPs_Screen[5].getZ(),BBPs_Screen[7].getX(),BBPs_Screen[7].getY(),BBPs_Screen[7].getZ());
		BBLs_Camera[7] = new Line_3D(BBPs_Screen[5].getX(),BBPs_Screen[5].getY(),BBPs_Screen[5].getZ(),BBPs_Screen[4].getX(),BBPs_Screen[4].getY(),BBPs_Screen[4].getZ());
		BBLs_Camera[8] = new Line_3D(BBPs_Screen[0].getX(),BBPs_Screen[0].getY(),BBPs_Screen[0].getZ(),BBPs_Screen[6].getX(),BBPs_Screen[6].getY(),BBPs_Screen[6].getZ());
		BBLs_Camera[9] = new Line_3D(BBPs_Screen[2].getX(),BBPs_Screen[2].getY(),BBPs_Screen[2].getZ(),BBPs_Screen[4].getX(),BBPs_Screen[4].getY(),BBPs_Screen[4].getZ());
		BBLs_Camera[10] = new Line_3D(BBPs_Screen[1].getX(),BBPs_Screen[1].getY(),BBPs_Screen[1].getZ(),BBPs_Screen[7].getX(),BBPs_Screen[7].getY(),BBPs_Screen[7].getZ());
		BBLs_Camera[11] = new Line_3D(BBPs_Screen[3].getX(),BBPs_Screen[3].getY(),BBPs_Screen[3].getZ(),BBPs_Screen[5].getX(),BBPs_Screen[5].getY(),BBPs_Screen[5].getZ());
	}

	void Update_Cam_Coordinates(Frustum_3D iFrustum) {
		for (int x=0;x<=7;x++) {

			Camera_Transformation Cam_Trans = new Camera_Transformation(iFrustum);

			BBPs_Camera[x] = new Point_3D(Cam_Trans.Calc_Camera_Space_Coordinates(BBPs_World[x])[0],Cam_Trans.Calc_Camera_Space_Coordinates(BBPs_World[x])[1],
					Cam_Trans.Calc_Camera_Space_Coordinates(BBPs_World[x])[2]);

			BBLs_Camera[0] = new Line_3D(BBPs_Camera[0].getX(),BBPs_Camera[0].getY(),BBPs_Camera[0].getZ(),BBPs_Camera[1].getX(),BBPs_Camera[1].getY(),BBPs_Camera[1].getZ());
			BBLs_Camera[1] = new Line_3D(BBPs_Camera[0].getX(),BBPs_Camera[0].getY(),BBPs_Camera[0].getZ(),BBPs_Camera[2].getX(),BBPs_Camera[2].getY(),BBPs_Camera[2].getZ());
			BBLs_Camera[2] = new Line_3D(BBPs_Camera[3].getX(),BBPs_Camera[3].getY(),BBPs_Camera[3].getZ(),BBPs_Camera[1].getX(),BBPs_Camera[1].getY(),BBPs_Camera[1].getZ());
			BBLs_Camera[3] = new Line_3D(BBPs_Camera[3].getX(),BBPs_Camera[3].getY(),BBPs_Camera[3].getZ(),BBPs_Camera[2].getX(),BBPs_Camera[2].getY(),BBPs_Camera[2].getZ());
			BBLs_Camera[4] = new Line_3D(BBPs_Camera[6].getX(),BBPs_Camera[6].getY(),BBPs_Camera[6].getZ(),BBPs_Camera[7].getX(),BBPs_Camera[7].getY(),BBPs_Camera[7].getZ());
			BBLs_Camera[5] = new Line_3D(BBPs_Camera[6].getX(),BBPs_Camera[6].getY(),BBPs_Camera[6].getZ(),BBPs_Camera[4].getX(),BBPs_Camera[4].getY(),BBPs_Camera[4].getZ());
			BBLs_Camera[6] = new Line_3D(BBPs_Camera[5].getX(),BBPs_Camera[5].getY(),BBPs_Camera[5].getZ(),BBPs_Camera[7].getX(),BBPs_Camera[7].getY(),BBPs_Camera[7].getZ());
			BBLs_Camera[7] = new Line_3D(BBPs_Camera[5].getX(),BBPs_Camera[5].getY(),BBPs_Camera[5].getZ(),BBPs_Camera[4].getX(),BBPs_Camera[4].getY(),BBPs_Camera[4].getZ());
			BBLs_Camera[8] = new Line_3D(BBPs_Camera[0].getX(),BBPs_Camera[0].getY(),BBPs_Camera[0].getZ(),BBPs_Camera[6].getX(),BBPs_Camera[6].getY(),BBPs_Camera[6].getZ());
			BBLs_Camera[9] = new Line_3D(BBPs_Camera[2].getX(),BBPs_Camera[2].getY(),BBPs_Camera[2].getZ(),BBPs_Camera[4].getX(),BBPs_Camera[4].getY(),BBPs_Camera[4].getZ());
			BBLs_Camera[10] = new Line_3D(BBPs_Camera[1].getX(),BBPs_Camera[1].getY(),BBPs_Camera[1].getZ(),BBPs_Camera[7].getX(),BBPs_Camera[7].getY(),BBPs_Camera[7].getZ());
			BBLs_Camera[11] = new Line_3D(BBPs_Camera[3].getX(),BBPs_Camera[3].getY(),BBPs_Camera[3].getZ(),BBPs_Camera[5].getX(),BBPs_Camera[5].getY(),BBPs_Camera[5].getZ());
		}
	}

	void Update_Screen_Coordinates(Frustum_3D iFrustum) {
		for (int c=0;c<=7;c++) {

			double D = (Math.sqrt((iFrustum.Cam_Focal_Camera.getX()*iFrustum.Cam_Focal_Camera.getX())+(iFrustum.Cam_Focal_Camera.getY()*iFrustum.Cam_Focal_Camera.getY())+
					(iFrustum.Cam_Focal_Camera.getZ()*iFrustum.Cam_Focal_Camera.getZ())))*iFrustum.getNear_Plane_Height();

			double X = BBPs_Camera[c].getX()*D/BBPs_Camera[c].getZ();
			double Y = BBPs_Camera[c].getY()*D/BBPs_Camera[c].getZ();

			BBPs_Screen[c]= new Point_3D(X,Y,BBPs_Camera[c].getZ());
		}

		BBLs_Screen[0] = new Line_3D(BBPs_Screen[0].getX(),BBPs_Screen[0].getY(),BBPs_Screen[0].getZ(),BBPs_Screen[1].getX(),BBPs_Screen[1].getY(),BBPs_Screen[1].getZ());
		BBLs_Screen[1] = new Line_3D(BBPs_Screen[0].getX(),BBPs_Screen[0].getY(),BBPs_Screen[0].getZ(),BBPs_Screen[2].getX(),BBPs_Screen[2].getY(),BBPs_Screen[2].getZ());
		BBLs_Screen[2] = new Line_3D(BBPs_Screen[3].getX(),BBPs_Screen[3].getY(),BBPs_Screen[3].getZ(),BBPs_Screen[1].getX(),BBPs_Screen[1].getY(),BBPs_Screen[1].getZ());
		BBLs_Screen[3] = new Line_3D(BBPs_Screen[3].getX(),BBPs_Screen[3].getY(),BBPs_Screen[3].getZ(),BBPs_Screen[2].getX(),BBPs_Screen[2].getY(),BBPs_Screen[2].getZ());
		BBLs_Screen[4] = new Line_3D(BBPs_Screen[6].getX(),BBPs_Screen[6].getY(),BBPs_Screen[6].getZ(),BBPs_Screen[7].getX(),BBPs_Screen[7].getY(),BBPs_Screen[7].getZ());
		BBLs_Screen[5] = new Line_3D(BBPs_Screen[6].getX(),BBPs_Screen[6].getY(),BBPs_Screen[6].getZ(),BBPs_Screen[4].getX(),BBPs_Screen[4].getY(),BBPs_Screen[4].getZ());
		BBLs_Screen[6] = new Line_3D(BBPs_Screen[5].getX(),BBPs_Screen[5].getY(),BBPs_Screen[5].getZ(),BBPs_Screen[7].getX(),BBPs_Screen[7].getY(),BBPs_Screen[7].getZ());
		BBLs_Screen[7] = new Line_3D(BBPs_Screen[5].getX(),BBPs_Screen[5].getY(),BBPs_Screen[5].getZ(),BBPs_Screen[4].getX(),BBPs_Screen[4].getY(),BBPs_Screen[4].getZ());
		BBLs_Screen[8] = new Line_3D(BBPs_Screen[0].getX(),BBPs_Screen[0].getY(),BBPs_Screen[0].getZ(),BBPs_Screen[6].getX(),BBPs_Screen[6].getY(),BBPs_Screen[6].getZ());
		BBLs_Screen[9] = new Line_3D(BBPs_Screen[2].getX(),BBPs_Screen[2].getY(),BBPs_Screen[2].getZ(),BBPs_Screen[4].getX(),BBPs_Screen[4].getY(),BBPs_Screen[4].getZ());
		BBLs_Screen[10] = new Line_3D(BBPs_Screen[1].getX(),BBPs_Screen[1].getY(),BBPs_Screen[1].getZ(),BBPs_Screen[7].getX(),BBPs_Screen[7].getY(),BBPs_Screen[7].getZ());
		BBLs_Screen[11] = new Line_3D(BBPs_Screen[3].getX(),BBPs_Screen[3].getY(),BBPs_Screen[3].getZ(),BBPs_Screen[5].getX(),BBPs_Screen[5].getY(),BBPs_Screen[5].getZ());
	}
	
}
