package com.xiaojie.threadpool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    public Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Glide.with(MainActivity.this).load(message.getData().getString("url")).into((ImageView) message.obj);
            return true;
        }
    });
    private ExecutorService executorService;
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.iv_one)
    ImageView ivOne;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.iv_three)
    ImageView ivThree;
    @BindView(R.id.iv_four)
    ImageView ivFour;
    @BindView(R.id.iv_five)
    ImageView ivFive;
    @BindView(R.id.iv_six)
    ImageView ivSix;
    /*@BindView(R.id.pb)
    ProgressBar pb;*/
    private int index = 0;
    private static final String TAG = "MainActivity";
    private ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /*executorService = Executors.newFixedThreadPool(5);
        service = Executors.newFixedThreadPool(5);*/
        
        /*LeakThread thread = new LeakThread();
        thread.start();*/
        
        /*fixed();
        cache();
        single();
        scheduled();*/
        
        /*long t = System.currentTimeMillis();
        SimpleDateFormat f = new SimpleDateFormat("YYYY:MM:dd");
        String date = f.format(t);
        Toast.makeText(this, ""+date, Toast.LENGTH_SHORT).show();*/
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    class LeakThread extends Thread{

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_test)
    public void onViewClicked() {
        /*String url = "http://cache.soso.com/30d/img/web/logo.gif";
        loadImage(url, ivOne, 1000);
        loadImage(url, ivTwo, 2000);
        loadImage(url, ivThree, 3000);
        loadImage(url, ivFour, 4000);
        loadImage(url, ivFive, 5000);
        loadImage(url, ivSix, 6000);*/

        for(int i=0;i<300;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: "+Thread.currentThread().getName());
                }
            };
            service.execute(runnable);

        }
        service.shutdown();
    }

    private void scheduled() {
        service = Executors.newScheduledThreadPool(5);
    }

    private void single() {
        service = Executors.newSingleThreadExecutor();
    }

    private void cache() {
        service = Executors.newCachedThreadPool();
    }

    private void fixed() {
        service = Executors.newFixedThreadPool(5);
    }

    private void loadImage(final String url, final ImageView iv, final int delay) {
        /*new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = mHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                message.obj = iv;
                message.setData(bundle);
                mHandler.sendMessage(message);

            }
        }).start();*/

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = mHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                message.obj = iv;
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        });
        /*mHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });*/
        //mHandler.postDelayed(runnable, 0);
    }

    /*Runnable runnable = new Runnable() {
        @Override
        public void run() {
            index++;
            if (index > 10) {
                mHandler.removeCallbacks(runnable);
                index=0;
                return;
            }
            pb.setProgress(index*10);
            mHandler.postDelayed(this, 1000);
        }
    };*/
}
