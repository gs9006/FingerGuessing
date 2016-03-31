package com.example.fingerguessing;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnClickListener{

	private TextView result;
	private TextView finalResult;
	private TextView sum;
	private TextView success;
	private TextView fail;
	private TextView draw;
	private static int sum_value=0;//静态变量继承上次的结果***
	private static int success_value=0;//静态变量继承上次的结果***
	private static int fail_value=0;//静态变量继承上次的结果***
	private static int draw_value=0;//静态变量继承上次的结果***
	
	SharedPreferences sp;
	Editor editor;
	private Button ct;
	private ImageView imgResult;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        sp=getSharedPreferences("NUMBER", MODE_PRIVATE);
        editor=sp.edit();

        initView();
        initData();
        
	}

	/**
	 * 处理结果
	 */
	private void initData() {
		
		sum_value=sp.getInt("sum", 0)+1;
		editor.putInt("sum", sum_value);
		editor.commit();
        sum.setText(""+sp.getInt("sum", 0));
		
		 /*
		  * 1--石头，2--剪刀，3--布
		  */
		 //玩家出拳的结果
		 Bundle bundle = this.getIntent().getExtras(); 
	     int pChuquan = bundle.getInt("people");
	     //电脑随机出拳的结果
	     Random random = new Random();	
		 int cChunquan= random.nextInt(3)+1;
		 
		 /*
		  * 猜拳结果判断
		  */
		 if(pChuquan==1&&cChunquan==2||pChuquan==2&&cChunquan==3||pChuquan==3&&cChunquan==1)
		 {
			    if(pChuquan==1){
	    			result.setText("玩家：石头"+"VS"+"电脑：剪刀");
	    		}
	    		if(pChuquan==2){
	    			result.setText("玩家：剪刀"+"VS"+"电脑：布");
	    		}
	    		if(pChuquan==3){
	    			result.setText("玩家：布"+"VS"+"电脑：石头");
	    		}
	    		finalResult.setText("赢");
	    		imgResult.setBackgroundDrawable(getResources().getDrawable(R.drawable.win));
	    		success_value=sp.getInt("success", 0)+1;
	    		editor.putInt("success", success_value);
	    		editor.commit();
	    
		 }
		 if(pChuquan==2&&cChunquan==1||pChuquan==3&&cChunquan==2||pChuquan==1&&cChunquan==3)
		 {
			    if(pChuquan==1){
	    			result.setText("玩家：石头"+"VS"+"电脑：布");
	    		}
	    		if(pChuquan==2){
	    			result.setText("玩家：剪刀"+"VS"+"电脑：石头");
	    		}
	    		if(pChuquan==3){
	    			result.setText("玩家：布"+"VS"+"电脑：剪刀");
	    		}
	    		finalResult.setText("输");
	    		imgResult.setBackgroundDrawable(getResources().getDrawable(R.drawable.fail));
	    		fail_value=sp.getInt("fail", 0)+1;
	    		editor.putInt("fail", fail_value);
	    		editor.commit();
	    		
		 }
		 if(pChuquan==cChunquan)
		 {
			 if(pChuquan==1)
			 {
				 result.setText("玩家：石头"+"VS"+"电脑:石头");
			 }
			 if(pChuquan==2)
			 {
				 result.setText("玩家：剪刀"+"VS"+"电脑:剪刀");
			 }
			 if(pChuquan==3)
			 {
				 result.setText("玩家：布"+"VS"+"电脑:布");
			 }
			 finalResult.setText("平局");
	    	 imgResult.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw));
			 draw_value=sp.getInt("draw", 0)+1;
			 editor.putInt("draw", draw_value);
			 editor.commit();
		 }
		 
		 /*
		  * 不能放在判断语句中，否则出现Bug
		  */
		 success.setText(""+sp.getInt("success", 0));
 		 fail.setText(""+sp.getInt("fail", 0));
		 draw.setText(""+sp.getInt("draw", 0));
		 
         int wealth=(sp.getInt("success", 0)-sp.getInt("fail", 0))*10;
         Editor editor=MainActivity.sp.edit();
         editor.putInt("WealthValue", wealth);
         editor.commit();
         editor.clear();		
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		result=(TextView) findViewById(R.id.result);
		finalResult=(TextView) findViewById(R.id.finalResult);
		sum=(TextView) findViewById(R.id.sum);
		success=(TextView) findViewById(R.id.success);
		fail=(TextView) findViewById(R.id.fail);
		draw=(TextView) findViewById(R.id.draw);
		ct=(Button) findViewById(R.id.ct);
		imgResult=(ImageView) findViewById(R.id.img_result);
		ct.setOnClickListener(this);
	}

	/**
	 * 设置按钮点击事件
	 */
	@Override
	public void onClick(View v) {

		Intent intent=new Intent("com.example.fingerguessing.ACTION_TWO");
		startActivity(intent);
		finish();
		
			
	}
}
