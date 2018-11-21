package com.example.diorama_engine_v10;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.example.diorama_engine_v10.Render_View.*;

public class Engine_Activity extends Activity {

    private Render_View Render_Output;
    public Render_Object Test_Object;
    public Screen_Space Scrn_Data;
    public Read_File File_Output_Test;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine_);


        // Code for reading Obj not working for unknown reasons.
        /*
        AssetManager am = getAssets();
        try {
            InputStream is = am.open("Utah_Teapot");

            File_Output_Test = new Read_File(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */



        Scrn_Data = new Screen_Space(0,0,0);
        Test_Object = new Render_Object(Scrn_Data.Camera);

        Setup_Render_Activity();

    }


    public void Setup_Render_Activity() {
        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();


        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);


        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        // Create an instance of our GameView
        // Passing in the screen resolution to the constructor
        Render_Output = new Render_View(this, size.x, size.y,Test_Object,Scrn_Data);

        // Make our gameView the view for the Activity
        setContentView(Render_Output);
    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause()
    {
        super.onPause();
        Render_Output.pause();

    }   //  protected void onPause()

    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume()
    {
        super.onResume();
        Render_Output.resume();
    }   //     protected void onResume()

}