package com.matt.module.net.inner.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.matt.module.net.inner.CompileConfig;
import com.matt.module.net.inner.model.BaseRequest;

import java.util.List;

public class CommonUtils {

    private static final String TAG = "CommonUtils";

    private static Gson sGson;

    public static String dealRequest(BaseRequest request,boolean isEncrypt){
        if(request == null){
            return null;
        }
        if(sGson == null){
            sGson = new Gson();
        }
        String params = sGson.toJson(request);
        if(CompileConfig.DEBUG){
            Log.i("httpop", "dealRequest: "+params);
        }
        if(!isEncrypt){
            return params;
        }
        String aesParams = AesUtils.encrypt(params);
        if(CompileConfig.DEBUG){
            Log.i("httpop", "dealRequest:aes: "+aesParams);
        }
        return aesParams;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) v;
            final int scrollX = v.getScrollX();
            final int scrollY = v.getScrollY();
            final int count = group.getChildCount();
            // Count backwards - let topmost views consume scroll distance first.
            for (int i = count - 1; i >= 0; i--) {
                // TODO: Add versioned support here for transformed views.
                // This will not work for transformed views in Honeycomb+
                final View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop()
                        && y + scrollY < child.getBottom()
                        && canScroll(child, true, dx, x + scrollX - child.getLeft(), y + scrollY - child.getTop())) {
                    return true;
                }
            }
        }

        boolean res = checkV && ViewCompat.canScrollHorizontally(v, -dx);
        return res;
    }

    /** 强制隐藏键盘 */
    public final static void hideSoftInput(Context cnt, IBinder binder) {
        try {
            InputMethodManager imm = (InputMethodManager) cnt.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
            if (isOpen) {
                imm.hideSoftInputFromWindow(binder, 0); // 强制隐藏键盘
            }
        } catch (Exception e) {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "Exception", e);
            }
        } catch (Error error) {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "error", error);
            }
        }
    }

    public final static void showSoftInput(Context cnt, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) cnt.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, 0); // 强制显示键盘
        } catch (Exception e) {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "Exception", e);
            }
        } catch (Error error) {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "error", error);
            }
        }
    }

    /**
     * 刷新view显示状态
     * @param v
     * @param visibility
     */
    public static boolean updateVisibility(View v, int visibility){
        if(v == null){
            return false;
        }
        if(v.getVisibility() == visibility){
            return false;
        }
        v.setVisibility(visibility);
        return true;
    }

    /**
     * 显示虚拟按键区域
     * @param rootView
     */
    /*public static void showNavigationBar(View rootView){
        if(rootView == null){
            return;
        }
        int navigationBarHeight = InternalNewsIntegrationAssistant.getNavigationBarHeight();
        View navigationBarMask = rootView.findViewById(R.id.nav_mask);
        if(navigationBarMask == null){
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) navigationBarMask.getLayoutParams();
        layoutParams.height = navigationBarHeight;
        navigationBarMask.setLayoutParams(layoutParams);
        navigationBarMask.setVisibility(navigationBarHeight <= 0 ? GONE : VISIBLE);
    }*/


    /**
     * 刷新view Margin状态
     * @param v
     * @param margin
     */
    public static boolean updateMargin(View v, int margin){
        if(v == null){
            return false;
        }
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if(layoutParams == null){
            return false;
        }
        if(layoutParams instanceof RelativeLayout.LayoutParams){
            ((RelativeLayout.LayoutParams) layoutParams).setMargins(margin,margin,margin,margin);
        }else if(layoutParams instanceof LinearLayout.LayoutParams){
            ((LinearLayout.LayoutParams) layoutParams).setMargins(margin,margin,margin,margin);
        }else if(layoutParams instanceof FrameLayout.LayoutParams){
            ((FrameLayout.LayoutParams) layoutParams).setMargins(margin,margin,margin,margin);
        }else{
            return false;
        }
        return true;
    }

    /**
     * 获取view的截屏imageview
     * @param view
     * @return
     */
    public static ImageView getCaptureView(Context context, View view) {
        if(view == null){
            return null;
        }
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(context);
        iv.setImageBitmap(cache);
        return iv;
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    public static View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    public static ViewGroup getDecorViewGroup(Activity activity) {
        ViewGroup moveViewGroup = (ViewGroup) activity.getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(activity.getApplicationContext());
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }


    public static void hideSystemUI(View mDecorView) {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /*public static void showSystemUI(Activity mActivity) {
        if(mActivity == null || mActivity.getWindow() == null){
            return;
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
            if(decorView == null){
                return;
            }
            View topInsetView = decorView.findViewById(R.id.view_top_inset);
            if(topInsetView != null){
                topInsetView .setVisibility(VISIBLE);
            }else{
                View statusBarView = decorView.findViewById(R.id.statusbar_id);
                if(statusBarView != null){
                    statusBarView.setVisibility(VISIBLE);
                }
            }
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    public static void hideSystemUI(Activity mActivity) {
        if(mActivity == null || mActivity.getWindow() == null){
            return;
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
            if(decorView == null){
                return;
            }
            View topInsetView = decorView.findViewById(R.id.view_top_inset);
            if(topInsetView != null){
                topInsetView .setVisibility(GONE);
            }else{
                View statusBarView = decorView.findViewById(R.id.statusbar_id);
                if(statusBarView != null){
                    statusBarView.setVisibility(GONE);
                }
            }
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }*/
    public static String formatTime(float sec) {
        int minutes = (int) (sec / 60);
        int seconds = (int) (sec % 60);
        return String.format("%d:%02d", minutes, seconds);
    }



    /**
     * 显示动画
     */
    public static void videoMastShowAnimation(View view){
        AlphaAnimation mShowAnimation = new AlphaAnimation(0,1);
        mShowAnimation.setDuration(100);
        view.startAnimation(mShowAnimation);
    }
    /**
     * 隐藏动画
     */
    public static void videoMastHideAnimation(View view){
        AlphaAnimation mHideAnimation = new AlphaAnimation(1,0);
        mHideAnimation.setDuration(100);
        view.startAnimation(mHideAnimation);
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public static void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null) return;

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        for (View v : viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
