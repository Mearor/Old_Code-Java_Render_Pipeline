package com.example.diorama_engine_v10;

public class Camera_Transformation {

    // This class holds the matrix and functions necessary to change from World Cooridnates to Camera Coordinates.

    double m11;
    double m12;
    double m13;
    double m14;

    double m21;
    double m22;
    double m23;
    double m24;

    double m31;
    double m32;
    double m33;
    double m34;

    double a[][] = new double[4][4];


    Camera_Transformation(Frustum_3D iFrustum) {

        // This is the creation of a matrix that holds the necessary transformations to change a point from
        // World space to Camera Space. When applied to the cam point the desired outcome should be 0,0,0

        m11 = Normalized(iFrustum.Right_Axis).getX();
        m12 = Normalized(iFrustum.Right_Axis).getY();
        m13 = Normalized(iFrustum.Right_Axis).getZ();
        m14 = -iFrustum.Cam.getX()*Normalized(iFrustum.Right_Axis).getX()-iFrustum.Cam.getY()*Normalized(iFrustum.Right_Axis).getY()-
                iFrustum.Cam.getZ()*Normalized(iFrustum.Right_Axis).getZ();

        m21 = Normalized(iFrustum.Up_Axis).getX();
        m22 = Normalized(iFrustum.Up_Axis).getY();
        m23 = Normalized(iFrustum.Up_Axis).getZ();
        m24 = -iFrustum.Cam.getX()*Normalized(iFrustum.Up_Axis).getX()-iFrustum.Cam.getY()*Normalized(iFrustum.Up_Axis).getY()-
                iFrustum.Cam.getZ()*Normalized(iFrustum.Up_Axis).getZ();

        m31 = Normalized(iFrustum.Back_Axis).getX();
        m32 = Normalized(iFrustum.Back_Axis).getY();
        m33 = Normalized(iFrustum.Back_Axis).getZ();
        m34 = -(iFrustum.Cam.getX()*Normalized(iFrustum.Back_Axis).getX())-(iFrustum.Cam.getY()*Normalized(iFrustum.Back_Axis).getY())-
                (iFrustum.Cam.getZ()*Normalized(iFrustum.Back_Axis).getZ());

        a[0][0] = m11; a[0][1] = m12; a[0][2] = m13; a[0][3] = m14;
        a[1][0] = m21; a[1][1] = m22; a[1][2] = m23; a[1][3] = m24;
        a[2][0] = m31; a[2][1] = m32; a[2][2] = m33; a[2][3] = m34;
        a[3][0] = 0; a[3][1] = 0; a[3][2] = 0; a[3][3] = 1;

        System.out.println(a[0][0]+","+a[0][1]+","+a[0][2]+","+a[0][3]);
        System.out.println(a[1][0]+","+a[1][1]+","+a[1][2]+","+a[1][3]);
        System.out.println(a[2][0]+","+a[2][1]+","+a[2][2]+","+a[2][3]);
        System.out.println(a[3][0]+","+a[3][1]+","+a[3][2]+","+a[3][3]);


    }

    double [] Calc_Camera_Space_Coordinates(Point_3D iPoint_3D) {

        // Applies the matrices to the input vector bringing it into Camera Coordinates

        double b[] = new double[4];
        double c[] = new double [4];

        b[0] = iPoint_3D.getX();
        b[1] = iPoint_3D.getY();
        b[2] = iPoint_3D.getZ();
        b[3] = 1;

        for (int x = 0; x < 4; x++) {
            c[x] = (a[x][0]*b[0])+(a[x][1]*b[1])+(a[x][2]*b[2])+(a[x][3]*b[3]);
        }

        System.out.println(a[0][0]+","+a[0][1]+","+a[0][2]+","+a[0][3]);

        return c;
    }


    Point_3D Normalized(Line_3D iLine_3D) {

        // Produces a normalized vector from an input line

        double Length = Math.sqrt((iLine_3D.getX()*iLine_3D.getX())+(iLine_3D.getY()*iLine_3D.getY())+
                (iLine_3D.getZ()*iLine_3D.getZ()));

        Point_3D Normalized = new Point_3D(iLine_3D.getX()/Length,iLine_3D.getY()/Length,iLine_3D.getZ()/Length);

        return Normalized;
    }
}
