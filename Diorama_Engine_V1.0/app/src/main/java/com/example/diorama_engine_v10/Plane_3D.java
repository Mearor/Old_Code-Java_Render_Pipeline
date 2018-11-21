package com.example.diorama_engine_v10;

public class Plane_3D {

    // Planes can be represented as 3 points in 3D Space this class provides a more tidy way of storing these points

    Point_3D Point_1;
    Point_3D Point_2;
    Point_3D Point_3;

    Plane_3D(double ix1,
             double iy1,
             double iz1,
             double ix2,
             double iy2,
             double iz2,
             double ix3,
             double iy3,
             double iz3) {

        Point_1 = new Point_3D(ix1,iy1,iz1);
        Point_2 = new Point_3D(ix2,iy2,iz2);
        Point_3 = new Point_3D(ix3,iy3,iz3);
    }

}
