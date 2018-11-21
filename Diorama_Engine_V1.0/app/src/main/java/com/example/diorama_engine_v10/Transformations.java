package com.example.diorama_engine_v10;

import android.graphics.Camera;

public class Transformations {

	double Rotation_Angle_Rad;

	Transformations() {
		Rotation_Angle_Rad = 0.0174533;
	}
	
	// Move Camera in X+ Direction
	public void Trans_X_Pos() {
		
	}
	
	// Move Camera in X- Direction
	public void Trans_X_Neg() {
		
	}
	
	// Move Camera in Y+ Direction
	public void Trans_Y_Pos() {
		
	}
	
	// Move Camera in Y- Direction
	public void Trans_Y_Neg() {
		
	}
	
	// Move Camera towards the Focal Point
	public void Zoom_In() {
		
	}
	
	// Move the Camera Away from the Focal Point
	public void Zoom_Out() {
		
	}
	
	// Rotate Clockwise around the Pivot Axis
	public void Pivot_CW(Frustum_3D iFrustum,Screen_Space iScrn_Data) {
		RotationMatrix CW_Pivot = new RotationMatrix(iFrustum.Pivot_Point.getX(),
				iFrustum.Pivot_Point.getY(),iFrustum.Pivot_Point.getZ(),iFrustum.Pivot_Axis.getX(),
				iFrustum.Pivot_Axis.getY(),iFrustum.Pivot_Axis.getZ(),Rotation_Angle_Rad);


		Rotate_Point(iFrustum.Cam,CW_Pivot);
		Rotate_Point(iFrustum.A,CW_Pivot);
		Rotate_Point(iFrustum.B,CW_Pivot);
		Rotate_Point(iFrustum.C,CW_Pivot);
		Rotate_Point(iFrustum.D,CW_Pivot);
		Rotate_Point(iFrustum.Tilt_Point,CW_Pivot);
		Rotate_Point(iFrustum.Up_Point,CW_Pivot);
		Rotate_Point(iFrustum.Right_Point,CW_Pivot);
		Rotate_Point(iFrustum.Back_Point,CW_Pivot);
		Rotate_Point(iFrustum.Near_Plane.Point_1,CW_Pivot);
		Rotate_Point(iFrustum.Near_Plane.Point_2,CW_Pivot);
		Rotate_Point(iFrustum.Near_Plane.Point_3,CW_Pivot);

		iFrustum.Bulk_Update_Vectors();

		iScrn_Data.Bulk_Calculate_Line_Intersection();
	}
	
	// Rotate Counter Clockwise around the Pivot Axis
	public void Pivot_CCW() {
		
	}
	
	// Tilt Camera Towards Zenith
	public void Inclination_Pos() {
		
	}
	
	// Tilt Camera Away From Zenith
	public void Inclination_Neg(Frustum_3D iFrustum,Screen_Space iScrn_Data) {
		RotationMatrix Neg_Tilt = new RotationMatrix(iFrustum.Tilt_Point.getX(),
				iFrustum.Tilt_Point.getY(),iFrustum.Tilt_Point.getZ(),iFrustum.Tilt_Axis.getX(),
				iFrustum.Tilt_Axis.getY(),iFrustum.Tilt_Axis.getZ(),-Rotation_Angle_Rad);


		Rotate_Point(iFrustum.Cam,Neg_Tilt);
		Rotate_Point(iFrustum.A,Neg_Tilt);
		Rotate_Point(iFrustum.B,Neg_Tilt);
		Rotate_Point(iFrustum.C,Neg_Tilt);
		Rotate_Point(iFrustum.D,Neg_Tilt);
		Rotate_Point(iFrustum.Up_Point,Neg_Tilt);
		Rotate_Point(iFrustum.Right_Point,Neg_Tilt);
		Rotate_Point(iFrustum.Back_Point,Neg_Tilt);
		Rotate_Point(iFrustum.Near_Plane.Point_1,Neg_Tilt);
		Rotate_Point(iFrustum.Near_Plane.Point_2,Neg_Tilt);
		Rotate_Point(iFrustum.Near_Plane.Point_3,Neg_Tilt);

		iFrustum.Bulk_Update_Vectors();

		iScrn_Data.Bulk_Calculate_Line_Intersection();
	}

	public void Rotate_Point(Point_3D iPoint_3D,RotationMatrix iRotMatrix) {
		double rotOutput [] = iRotMatrix.timesXYZ(iPoint_3D.getX(),
				iPoint_3D.getY(),iPoint_3D.getZ());

		iPoint_3D.setX(rotOutput[0]);
		iPoint_3D.setY(rotOutput[1]);
		iPoint_3D.setZ(rotOutput[2]);
	}
}
