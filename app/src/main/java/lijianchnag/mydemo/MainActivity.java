package lijianchnag.mydemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private static final int REQCODE = 200 ;
    private final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.activity_bt_startservice)
    Button mStartButton;
    @Bind(R.id.activity_bt_stopservice)
    Button mStopButton;
    RocketService mRocketService;
    @Bind(R.id.activity_tv_sevicestate)
    TextView mTvState;
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRocketService = ((RocketService.LocalBinder)service).getService();
            mTvState.setText("开启了服务");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private View view;
    WindowManager windowManager;
    ImageView mIvRocket;
    WindowManager.LayoutParams mParams;
    RocketView mRocketView;
    RocketView mRocketView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
        mRocketView = new RocketView(this);
        mRocketView1 = new RocketView(this);
        mRocketView1.setGravity(Gravity.BOTTOM |Gravity.RIGHT);
//        mRocketView.startfly();
//        if (Build.VERSION.SDK_INT >= 23)
//            checkDrawOverlayPermission();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(outMetrics);
//        int widthPixels = outMetrics.widthPixels;
//        int heightPixels = outMetrics.heightPixels;
//        view = View.inflate(this,R.layout.rocket,null);
//        mIvRocket = (ImageView) view.findViewById(R.id.iv_rocket);
//         mParams = new WindowManager.LayoutParams();
//        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        mParams.format = PixelFormat.TRANSPARENT;
//        mParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
//        mParams.x = widthPixels/4;
//        mParams.flags =  WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        windowManager.addView(view,mParams);
//        int [] loction = new int[2];
//        view.getLocationInWindow(loction);
//        Log.d(TAG,"loction0"+loction[0]+"loction1"+loction[1]);
//        view.setOnTouchListener(new View.OnTouchListener() {
//
//            int startX;
//            int startY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//
//                        startX = (int) event.getRawX();
//                        startY = (int)event.getRawY();
////                        mParams.x = startX;
////                        mParams.y =startY;
////                        windowManager.updateViewLayout(view,mParams);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int newX = (int) event.getRawX();
//                        int newY = (int) event.getRawY();
//                        Log.d(TAG,"newX "+newX+"newY"+newY);
//
//                        int dx = newX -startX;
//                        int dy = newY -startY;
//                        Log.d(TAG,"dx "+dx+"dy"+dy);
//                        mParams.x+=dx;
//                        mParams.y +=dy;
////
////                        windowManager.updateViewLayout(view,mParams);
//                        windowManager.updateViewLayout(view,mParams);
//                        startX = newX;
//                        startY = newY;
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//                        break;
//
//                }
//                return true;
//            }
//        });
//        AnimationDrawable animationDrawable =(AnimationDrawable) mIvRocket.getBackground();
//        animationDrawable.start();

    }



    private void initListener() {
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,RocketService.class);
//                bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
//                finish();
                mRocketView.startfly();
                mRocketView1.startfly();

            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mServiceConnection != null)
//                    unbindService(mServiceConnection);
                mRocketView.reset();
                mRocketView1.reset();

            }
        });
    }

    @Override
    protected void onDestroy() {
//        windowManager.removeView(view);
        windowManager.removeView(mRocketView);
        windowManager.removeView(mRocketView1);
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQCODE){
            if(Build.VERSION.SDK_INT >= 23){

//            if(!Settings.canDrawOverlays(this)){
//
//            }
            }
        }
    }
}
