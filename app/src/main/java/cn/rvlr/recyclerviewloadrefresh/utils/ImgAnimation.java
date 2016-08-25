package cn.rvlr.recyclerviewloadrefresh.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * ImageView动画效果添加
 */
public class ImgAnimation {
    //分300步进行移动动画
    final static int count = 300;

    /**
     * 要start 动画的那张图片的ImageView,,抛物线动画
     */
    public static void startAnimation() {
        Keyframe[] keyframes = new Keyframe[count];
        final float keyStep = 1f / (float) count;
        float key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, i + 1);
            key += keyStep;
        }
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
        key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, -getY(i + 1));
            key += keyStep;
        }
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes);
        ObjectAnimator yxBouncer = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhY, pvhX).setDuration(1500);
        yxBouncer.setInterpolator(new BounceInterpolator());
        yxBouncer.start();
    }

    final static float a = -1f / 75f;

    /**
     * 这里是根据三个坐标点{（0,0），（300,0），（150,300）}计算出来的抛物线方程
     *
     * @param x
     * @return
     */
    private static float getY(float x) {
        return a * x * x + 4 * x;
    }


    public static ImageView imageView;

    /*
     * 1.创建一个AnimationSet对象，该对象存储的是动画的集合
	 * 2.根据需要创建相应的Animation对象
	 * 3.根据动画的需求，为Animation对象设置相应的数据（即执行效果）
	 * 4.奖Animation对象添加到AnimationSet对象当中
	 * 5.使用控件对象开始执行AnimationSet
	 */
    public static void Alpha() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);
        animationSet.addAnimation(alphaAnimation);
        imageView.startAnimation(animationSet);
    }

    public static void Rotata() {
        AnimationSet animationSet = new AnimationSet(true);
        //后面的四个参数定义的是旋转的圆心位置
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0f);
        rotateAnimation.setDuration(2000);
        animationSet.addAnimation(rotateAnimation);
        imageView.startAnimation(animationSet);
    }

    public static void Scale() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1, 0.1f, 1, 0.1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        animationSet.addAnimation(scaleAnimation);
        imageView.startAnimation(scaleAnimation);
    }

    public static void Translate() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,  //X轴的开始位置
                Animation.RELATIVE_TO_SELF, 0.5f,  //X轴的结束位置
                Animation.RELATIVE_TO_SELF, 0f,  //Y轴的开始位置
                Animation.RELATIVE_TO_SELF, 1.0f);  //Y轴的结束位置
        translateAnimation.setDuration(2000);
        animationSet.addAnimation(translateAnimation);

		/*
		 * 第一行的设置如果为true，则动画执行完之后效果定格在执行完之后的状态
		 * 第二行的设置如果为false，则动画执行完之后效果定格在执行完之后的状态
		 * 第三行设置的是一个long类型的值，是指动画延迟多少毫秒之后执行
		 * 第四行定义的是动画重复几次执行
		 */
        animationSet.setFillAfter(true);
        animationSet.setFillBefore(false);
        animationSet.setStartOffset(2000);
        animationSet.setRepeatCount(3);

        imageView.startAnimation(animationSet);
    }

    public static void Alpha_Translate() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);
        animationSet.addAnimation(alphaAnimation);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,  //X轴的开始位置
                Animation.RELATIVE_TO_SELF, 0.5f,  //X轴的结束位置
                Animation.RELATIVE_TO_SELF, 0f,  //Y轴的开始位置
                Animation.RELATIVE_TO_SELF, 1.0f);  //Y轴的结束位置
        translateAnimation.setDuration(2000);
        animationSet.addAnimation(translateAnimation);
        imageView.startAnimation(animationSet);
    }
}
