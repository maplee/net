package com.matt.module.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.matt.module.demo.request.GetCodeRequest;
import com.matt.module.net.inner.CompileConfig;
import com.matt.module.net.inner.model.BaseParseModel;
import com.matt.module.net.inner.parse.NetException;
import com.matt.module.net.openapi.IResponse;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (CompileConfig.DEBUG) {
            Log.i(TAG, "onCreate: ");
        }
        init();
//        test();
    }

    private void init() {
        GetCodeRequest request = new GetCodeRequest();
        request.setPhone("18911821111");
        new TestModel().getCode(request, new IResponse<String>() {
            @Override
            public void onResponse(BaseParseModel<String> baseParseModel) {
                if (CompileConfig.DEBUG) {
                    Log.i(TAG, "onResponse: "+baseParseModel.getData());
                }
            }

            @Override
            public void onFailure(NetException exception) {
                if (CompileConfig.DEBUG) {
                    Log.w(TAG, "onFailure: ", exception);
                }
            }
        });
        new TestModel().getCode1(request, new IResponse<String>() {
            @Override
            public void onResponse(BaseParseModel<String> baseParseModel) {
                if (CompileConfig.DEBUG) {
                    Log.i(TAG, "onResponse: "+baseParseModel.getData());
                }
            }

            @Override
            public void onFailure(NetException exception) {
                if (CompileConfig.DEBUG) {
                    Log.w(TAG, "onFailure: ", exception);
                }
            }
        });
    }

    private static final int THREADS_CONUT = 5;
     AtomicInteger count = new AtomicInteger(0);

    private void test(){
        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
//                    for (int i = 0; i < 3; i++) {
                        count.incrementAndGet();
//                    }
                }
            });
            if (CompileConfig.DEBUG) {
                Log.i(TAG, "test: "+i);
            }
            threads[i].start();
        }
        if (CompileConfig.DEBUG) {
            Log.i(TAG, "Thread.activeCount():"+Thread.activeCount());
        }
        while (Thread.activeCount() > 0) {
            Thread.yield();
        }
        if (CompileConfig.DEBUG) {
            Log.i(TAG, "final count:"+count.get());
        }

    }



}
