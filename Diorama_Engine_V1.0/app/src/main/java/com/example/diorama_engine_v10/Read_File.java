package com.example.diorama_engine_v10;

import android.content.Context;
import android.content.res.AssetManager;
import android.icu.util.Output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;

// The function used to parse data from output strings malfunctions for unknown reasons
// This class will require further development to work

public class Read_File {

    List<Point_3D> Vertexes = new ArrayList<Point_3D>(); // Vertex in Object Coordinates
    List<Point_3D> Vertex_Normals = new ArrayList<Point_3D>(); // Vertex Normal Vectors
    List<String> File = new ArrayList<String>(); //File 1:

    Context Context;
    InputStream Input;

    String Line;

    // Due to the volume of data, a small time delay has been put on the read function to
    // Counteract issues that were arising due to high volume of data.
    boolean Use_Time_Delay = true;

    Read_File(InputStream Input) {

        System.out.println("Initialisation Started");

        int Lines_Appended = 0;

        BufferedReader r = new BufferedReader(new InputStreamReader(Input));
        try {
            while ((Line = r.readLine()) != null) {

                //if (Line.charAt(0) == 'v') {
                //Sort_Vertex_Data(Line);
                //}

                File.add(Line);

                System.out.println(Lines_Appended + " = " + Line);

                Lines_Appended++;

                if (Use_Time_Delay = true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //
        for (int a = 0; a < File.size(); a++) {
            System.out.println("Line " + a + " = " + File.get(a));

            if (File.get(a).charAt(0) == 'v') {
                Sort_Vertex_Data(File.get(a));
            }

            if (Use_Time_Delay = true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Sort_Vertex_Data(String cLine) {
        System.out.println("vertex data detected");

        // Method to interpret lines prefixed with "v" into a list of Point_3D objects.

        String Value_1 = "";
        String Value_2 = "";
        String Value_3 = "";

        int Value_Num = 1;

        String Current_Value = "";

        for ( int x = 2;x < cLine.length();)
        {
            System.out.println(Value_Num+"");


            while ((x < cLine.length()) &&(cLine.charAt(x)!= ' ')) {

                // Loop continues until a full number has been read and puncuated by a space
                char a = cLine.charAt(x);

                Current_Value += a;
                System.out.println("Current Value = "+Current_Value);
                System.out.println("Character Search");

                if (x != cLine.length()) {
                    x++;
                }

                if (Use_Time_Delay = true) {
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Value_Num == 1) {
                Value_1 = Current_Value;
                System.out.println("Value_1 = "+Value_1);
            }
            else if(Value_Num == 2) {
                Value_2 = Current_Value;
                System.out.println("Value_2 = "+Value_2);
            }
            else if(Value_Num == 3) {
                Value_3 = Current_Value;
                System.out.println("Value_3 = "+Value_3);
            }

            x++;
            Value_Num++;
            Current_Value = "";

            if (Use_Time_Delay = true) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        Point_3D Output_Point = new Point_3D(Double.parseDouble(Value_1),Double.parseDouble(Value_2),
                Double.parseDouble(Value_3));

        Vertexes.add(Output_Point);
    }


}
