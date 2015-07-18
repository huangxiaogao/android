package com.android.led;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainLedActivity extends Activity {
private LinearLayout kaideng;
private LinearLayout saomiao;
private ImageButton fujin;
private TextView textView1;
private TextView textView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		kaideng=(LinearLayout)this.findViewById(R.id.kaideng);
		saomiao=(LinearLayout)this.findViewById(R.id.saomiao);
		textView1=(TextView)this.findViewById(R.id.textView1);
		textView2=(TextView)this.findViewById(R.id.textView2);
//		fujin=(ImageButton)this.findViewById(R.id.fujin);
		kaideng.setOnClickListener(kaiListener); 
		saomiao.setOnClickListener(SaoListener);
		}
		 
	private OnClickListener kaiListener =new OnClickListener(){
	   
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainLedActivity.this,
					Dengguang.class);
			startActivity(intent);
		}



			};
			private OnClickListener SaoListener =new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainLedActivity.this,
							saomiao.class);
					startActivity(intent);
					 
					
				}
		
};}


		 

			
		

	

	


