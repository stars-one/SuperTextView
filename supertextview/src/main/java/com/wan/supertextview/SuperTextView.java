package com.wan.supertextview;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by xen on 2018/4/7 0007.
 */

public class SuperTextView extends AppCompatTextView {
    private Context context;

    private String text="";
    private int count=1;
    private String signal;
    private int typeStartTime,typeTime;
   private boolean openAudoHide = false;

    public SuperTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typearray = context.obtainStyledAttributes(attrs,R.styleable.SuperTextView);
        text = typearray.getString(R.styleable.SuperTextView_text);
        signal = typearray.getString(R.styleable.SuperTextView_typeSignal);
        if (signal==null){
            signal="";
        }
        typeStartTime = typearray.getInt(R.styleable.SuperTextView_typeStartTime,400);
        typeTime = typearray.getInt(R.styleable.SuperTextView_typeTime,500);
        setText(text);
        typearray.recycle();

    }

    public void startShow(){

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (text.length()!=0){

                            if (count!=text.length()+1){

                                setText(text.substring(0,count)+signal);
                                postInvalidate();
                                count++;
                            }else{
                                setText(text.substring(0,count-1));
                                postInvalidate();
                                count=1;

                                if (openAudoHide){

                                    setVisibility(GONE);
                                }

                                timer.cancel();
                            }
                        }

                    }
                });
            }
        },typeStartTime,typeTime);
    }

    /**
     *
     * @param typeStartTime 开始打印的延迟时间（经过多长的时间开始打印）
     * @param typeTime 打印每个字的间隔时间
     */
    public void startShow(int typeStartTime, int typeTime){
        this.typeTime = typeTime;
        this.typeStartTime = typeStartTime;
        startShow();
    }
    public void typeAndhide(){

    }
    /**
     *与startShow连用
     * @param v 根布局的实例（通过findviewbyid找到）
     * @param duration 经过多少秒消失
     */
    public void typeAndhide(View v,int duration){
        openAudoHide = true;
        ViewGroup viewgroup =(ViewGroup)v;
        LayoutTransition transition = new LayoutTransition();

        transition.setDuration(duration);
        viewgroup.setLayoutTransition(transition);

    }

    /**
     *可单独使用，TextView消失效果
     * @param v 根布局的实例（通过findviewbyid找到）
     * @param duration 经过多少秒消失
     */
    public void hide(View v,int duration){
        ViewGroup viewgroup =(ViewGroup)v;
        LayoutTransition transition = new LayoutTransition();

        transition.setDuration(duration);
        viewgroup.setLayoutTransition(transition);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        setVisibility(GONE);
                        postInvalidate();
                    }
                });
            }
        },0,1);
    }
}
