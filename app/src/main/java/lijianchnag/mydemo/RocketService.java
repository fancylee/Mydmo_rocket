package lijianchnag.mydemo;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by 13155 on 2017/7/11:12:05.
 * Des :
 */

public class RocketService extends Service {

    public  final String TAG = RocketService.class.getSimpleName();

    LocalBinder mLocalBinder;
    WindowManager windowManager;
    View view;
    int widthPixels;
    int heightPixels;

    @Override
    public void onCreate() {

        super.onCreate();
        mLocalBinder = new LocalBinder();
        Log.d(TAG,"onCreate");




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

    class LocalBinder extends Binder{

        public RocketService getService(){
            return RocketService.this;
        }

    }
}
