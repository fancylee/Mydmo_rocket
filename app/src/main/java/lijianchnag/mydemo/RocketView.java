package lijianchnag.mydemo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.security.acl.LastOwnerException;
import java.util.Random;

/**
 * Created by 13155 on 2017/7/11:17:30.
 * Des :
 */

public class RocketView extends ImageView {


    private static final String TAG = "RocketView";
    private  WindowManager.LayoutParams mParams;
    WindowManager windowManager;
    Handler mHandler;
    Random mRandom;
    int widthPixels;
    int heightPixels;
    public RocketView(Context context) {
        super(context);

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        widthPixels= outMetrics.widthPixels;
        heightPixels= outMetrics.heightPixels;
        mParams = new WindowManager.LayoutParams();
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.format = PixelFormat.TRANSPARENT;
        mParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
        mParams.x = widthPixels/4;
        mParams.y = 0;
        mParams.flags =  WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        windowManager.addView(this,mParams);
        setBackground(context.getResources().getDrawable(R.drawable.rocket));
        AnimationDrawable animationDrawable = (AnimationDrawable)getBackground();
        animationDrawable.start();
        mRandom = new Random();
        mHandler = new Handler(){
            @Override
            public void dispatchMessage(Message msg) {
//                super.dispatchMessage(msg);

                mParams.y += mRandom.nextInt(20);
                Log.d(TAG,"mParams.y "+mParams.y );
                windowManager.updateViewLayout(RocketView.this,mParams);
            }
        };
        setOnTouchListener(new View.OnTouchListener() {

            int startX;
            int startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        startX = (int) event.getRawX();
                        startY = (int)event.getRawY();
//                        mParams.x = startX;
//                        mParams.y =startY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int) event.getRawX();
                        int newY = (int) event.getRawY();
                        Log.d(TAG,"newX "+newX+"newY"+newY);

                        int dx = newX -startX;
                        int dy = newY -startY;
                        Log.d(TAG,"dx "+dx+"dy"+dy);
                        mParams.x+=dx;
                        mParams.y -=dy;
//
                        windowManager.updateViewLayout(RocketView.this,mParams);
                        startX = newX;
                        startY = newY;
                        break;
                    case MotionEvent.ACTION_UP:

                        break;

                }
                return true;
            }
        });

    }

    public void setGravity(int gravity){
        mParams.gravity = gravity;
        windowManager.updateViewLayout(this,mParams);

    }

    public void startfly(){
        new Thread(){
            @Override
            public void run() {
//                super.run();
                while (mParams.y <= heightPixels - 1.5*getHeight() ){
                    SystemClock.sleep(100);
                    mHandler.sendEmptyMessage(0);
                }
            }
        }.start();

    }
    public RocketView(Context context, AttributeSet set){
        super(context,set);
    }


    public void reset() {
        mParams.x = widthPixels/4;
        mParams.y = 0;
        windowManager.updateViewLayout(this,mParams);
    }


}
