package com.example.fingerguessing;

import java.util.Random;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{
	
	public static SharedPreferences sp;
		
    private Button submit;
    private RadioButton shitou;
    private RadioButton jiandao;
    private RadioButton bu;
    private TextView wealthValue;
    private int i = 0;
    private ImageView  img;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        submit.setOnClickListener(this); 
        initData();
     
    }


    private void initData() {
		sp=getSharedPreferences("Data", MODE_PRIVATE);
		wealthValue.setText(""+sp.getInt("WealthValue", 0));
	}


	private void initView() {

		submit=(Button) findViewById(R.id.submit);
		shitou=(RadioButton) findViewById(R.id.sh);
		jiandao=(RadioButton) findViewById(R.id.jiandao);
		bu=(RadioButton) findViewById(R.id.bu);
		wealthValue=(TextView) findViewById(R.id.wealthValue);
		img=(ImageView) findViewById(R.id.img);
		

	}

	@Override
	public void onClick(View v) {

		if(shitou.isChecked())
		{
			i=1;
		}
		else if(jiandao.isChecked())
		{
			i=2;
		}
		else if(bu.isChecked())
		{
			i=3;
		}
		
		Intent intent=new Intent("com.example.fingerguessing.ACTION_ONE");
		intent.putExtra("people", i);
		intent.putExtras(intent);
		startActivity(intent);
		finish();
		
	}
	
	public void doClick(View v)
	{
		switch(v.getId())
		{
		case R.id.sh:
			img.setBackgroundDrawable(getResources().getDrawable(R.drawable.stone));
			break;
		case R.id.jiandao:
			img.setBackgroundDrawable(getResources().getDrawable(R.drawable.scissors));
			break;
		case R.id.bu:
			img.setBackgroundDrawable(getResources().getDrawable(R.drawable.bu));
			break;
		}
	}

	



}
