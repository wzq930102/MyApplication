package com.jtvr.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.jt.base.R;


public class ClearEditText extends EditText implements  
        OnFocusChangeListener, TextWatcher { 
	/**
	 * 删除按钮的引用
	 */
    protected Drawable mClearDrawable; 
    /**
     * 控件是否有焦点
     */
    protected boolean hasFoucs;
 
    public ClearEditText(Context context) { 
    	this(context, null); 
    	this.setOnEditorActionListener(editorActionListener);
    } 
 
    public ClearEditText(Context context, AttributeSet attrs) { 
    	//这里构造方法也很重要，不加这个很多属性不能再XML里面定义
    	this(context, attrs, android.R.attr.editTextStyle); 
    	this.setOnEditorActionListener(editorActionListener);
    } 
    
    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnEditorActionListener(editorActionListener);
        init();
    }
    
    
    protected void init() { 
    	//获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
    	mClearDrawable = getCompoundDrawables()[2]; 
        if (mClearDrawable == null) { 
//        	throw new NullPointerException("You can add drawableRight attribute in XML");
        	mClearDrawable = getResources().getDrawable(R.drawable.edit_clear);
        } 
        
        mClearDrawable.setBounds(0, 0, 35, 35); 
        //默认设置隐藏图标
        setClearIconVisible(false); 
        //设置焦点改变的监听
        setOnFocusChangeListener(this); 
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this); 
    } 
 
 
    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override 
	public boolean onTouchEvent(MotionEvent event) {
//    	final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);               
//    	imm.showSoftInput(textEntryView, 0); 
//    	imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {

				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
						&& (event.getX() < ((getWidth() - getPaddingRight())));
				
				if (touchable) {
					this.setText("");
				}
			}
		}

		return super.onTouchEvent(event);
	}
 
    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    public void onFocusChange(View v, boolean hasFocus) { 
    	this.hasFoucs = hasFocus;
        if (hasFocus) { 
            setClearIconVisible(getText().length() > 0); 
        } else { 
            setClearIconVisible(false); 
        } 
    } 
    
    OnEditorActionListener editorActionListener = new OnEditorActionListener() {
		
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			 if (actionId == EditorInfo.IME_ACTION_DONE) {  
                 //让小叉号隐藏 
				 //mClearDrawable.setVisible(false, true);
				 setClearIconVisible(false);
				 hideSoftInput();
				 return true;
             }  
			return false;
		}
	};
    
    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) { 
        Drawable right = visible ? mClearDrawable : null; 
        setCompoundDrawables(getCompoundDrawables()[0], 
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]); 
    } 
    
    
    protected void hideSoftInput() {
    	InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
        imm.hideSoftInputFromWindow(this.getWindowToken(),0);		
	}

	/**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override 
    public void onTextChanged(CharSequence s, int start, int count, 
            int after) { 
            	if(hasFoucs){
            		setClearIconVisible(s.length() > 0);
            	}
    } 
 
    public void beforeTextChanged(CharSequence s, int start, int count, 
            int after) { 
         
    } 
 
    public void afterTextChanged(Editable s) { 
         
    } 
    
   
    /**
     * 设置晃动动画
     */
    public void setShakeAnimation(){
    	this.setAnimation(shakeAnimation(5));
    }
    
    
    /**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts){
    	Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
    	translateAnimation.setInterpolator(new CycleInterpolator(counts));
    	translateAnimation.setDuration(1000);
    	return translateAnimation;
    }
 
 
}
