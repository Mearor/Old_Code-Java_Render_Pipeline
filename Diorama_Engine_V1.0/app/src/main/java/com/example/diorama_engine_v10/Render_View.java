package com.example.diorama_engine_v10;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class Render_View extends SurfaceView implements Runnable {

    volatile boolean Running;

    Thread Render_Thread = null;

    // For drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    private Context context;

    private int screenX;
    private int screenY;

    Screen_Space Scrn;
    Render_Object Test_Obj;

    public Render_View( Context context, int x, int y, Render_Object Test_Object, Screen_Space Scrn_Data)
    {
        super(context);

        this.context  = context;

        // Initialize our drawing objects
        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;

        Scrn = Scrn_Data;
        Test_Obj = Test_Object;


    }   //    public GameView( Context context, int x, int y )

    public void setupScreenObjects()
    {

    }

    public void run()
    {
        while ( Running )
        {
            update();
            draw();
            control();

        }   //  while (playing)

    }   //    public void run()

    private void update()
    {
        // Every cycle the camera position updates

        Transformations Initial_Transform = new Transformations();
        Initial_Transform.Inclination_Neg(Scrn.Camera,Scrn);
        Initial_Transform.Pivot_CW(Scrn.Camera,Scrn);

        Scrn.Camera.Bulk_Camera_Transformation();
        Scrn.Bulk_Calculate_Line_Intersection();
        Test_Obj.BB.Update_Cam_Coordinates(Scrn.Camera);
        Test_Obj.BB.Update_Screen_Coordinates(Scrn.Camera);

    }   //     private void update()




    private void draw()
    {
        if (ourHolder.getSurface().isValid())
        {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();

            // Rub out the last frame
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            Draw_Bounding_Box();

            // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }   //     private void draw()

    private void control()
    {
        try
        {
            Render_Thread.sleep(17);
        }
        catch (InterruptedException e)
        {

        }
    }   //    private void control()

    // Clean up our thread if the game is interrupted or the player quits
    public void pause()
    {
        Running = false;

        try
        {
            Render_Thread.join();
        }
        catch (InterruptedException e)
        {

        }
    }   //    public void pause()

    // Make a new thread and start it
    // Execution moves to our R
    public void resume()
    {
        Running = true;
        Render_Thread = new Thread(this);
        Render_Thread.start();

    }

    void Draw_Bounding_Box() {

        paint.setColor(Color.argb(255,255,255,255));
        paint.setStrokeWidth(1);

        for (int d = 0;d < 12; d++) {

            //System.out.println(d);

            //System.out.println("X1 Screen X");
            //System.out.println(Test_Obj.BB.BBLs_Screen[d].getX1());

            Float X1 = Linear_Interpolation_Calculator(Scrn.NPTL_Camera.getX(),Test_Obj.BB.BBLs_Screen[d].getX1(),Scrn.NPTR_Camera.getX(),0,screenX);
            Float Y1 = Linear_Interpolation_Calculator(Scrn.NPTL_Camera.getY(),Test_Obj.BB.BBLs_Screen[d].getY1(),Scrn.NPBL_Camera.getY(),0,screenY);

            Float X2 = Linear_Interpolation_Calculator(Scrn.NPTL_Camera.getX(),Test_Obj.BB.BBLs_Screen[d].getX2(),Scrn.NPTR_Camera.getX(),0,screenX);
            Float Y2 = Linear_Interpolation_Calculator(Scrn.NPTL_Camera.getY(),Test_Obj.BB.BBLs_Screen[d].getY2(),Scrn.NPBL_Camera.getY(),0,screenY);

            canvas.drawLine(X1,Y1,X2,Y2,paint);

            System.out.println("Linear Interpolation Test = X1:"+Scrn.NPTL_Camera.getX()+" | X2: "+Scrn.NPTR_Camera.getX()+" | X3:"+Test_Obj.BB.BBLs_Screen[d].getX1()+" | Y1: 0 | Y2: "+screenX);
            System.out.println("X2 Coord = "+X2);
            System.out.println("Y1 Coord = "+Y1);
            System.out.println("Y2 Coord = "+Y2);

            System.out.println("X1 Coord = "+X1);
            System.out.println("X2 Coord = "+X2);
            System.out.println("Y1 Coord = "+Y1);
            System.out.println("Y2 Coord = "+Y2);


        }
    }

    float Linear_Interpolation_Calculator(double X1,double X2,double X3,double Y1,double Y3){

        // Translates screen coordinates into coordinates that can be use by the draw function

        float Y2 = (float) ((X2-X1)*(Y3-Y1)/(X3-X1)+Y1);

        return Y2;
    }
}
