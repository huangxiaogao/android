package com.android.led;
import java.util.Timer;
import java.util.TimerTask;


import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.res.ColorStateList;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
//Handler的定义: 
//主要接受子线程发送的数据, 并用此数据配合主线程更新UI.
public class Dengguang extends Activity implements OnClickListener {
	private boolean m_bScreenLight = false;
	private float m_fScreenLight = -1.0f;
	private Camera m_Camera=null;
	private boolean m_bCamera=false;
	private boolean m_sos_flight=false;
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.SOS:
			modeflash();
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}

	private void modeflash() {
		// TODO Auto-generated method stub
		if(!m_sos_flag)
			m_sos_flag=true;
		else
			m_sos_flag=false;
			
		
	}

	public class onCheckdListener implements OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			toggleButton.setChecked(isChecked);
//			imageview.setImageResource(isChecked?R.drawable.bumb_on:R.drawable.bumb_off);

		}
	}

//	private ImageView imageview=null;
	private ToggleButton toggleButton=null;
	private TextView ScreenButton=null;
	private Boolean m_sos_flag=false;
	Timer timer=null;
	Handler handler=null;
	Timer timer1=null;
	Handler handler1=null;
	TimerTask task=null;
	TimerTask task_sos=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialComponment();
//		imageview=(ImageView)this.findViewById(R.id.imageView);
		toggleButton=(ToggleButton)this.findViewById(R.id.CameraButton);
		toggleButton.setOnCheckedChangeListener(new onCheckdListener());
//		imageview.setClickable(true);
//		imageview.setOnClickListener(this);
		toggleButton.setClickable(true);
		toggleButton.setOnClickListener(this);
		///////////////////////////////////
		ScreenButton=(TextView)this.findViewById( R.id.ScreenButton);
		ScreenButton.setClickable(true);
		ScreenButton.setOnClickListener(this);
		///////////
		this.m_fScreenLight=this.getBrightness();
		timer.schedule(task, 1000,6000);
		timer1.schedule(task_sos, 1000,100);
		/////////////////////////////////////
		//this.findViewById(R.id.background).setBackgroundColor(Color.RED);
		Dengguang.this.findViewById(R.id.background).setBackgroundResource(R.drawable.mx_pic);
		this.findViewById(R.id.background).getBackground().setAlpha(255);
		
	}

	private void initialComponment() {
		// TODO Auto-generated method stub
		timer=new Timer();
		timer1=new Timer();
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what)
				{
				case 1:
					setBrightness(0.05f);
					ScreenButton.setTextColor(0xFFC0C0C0);
					Dengguang.this.m_bScreenLight=false;
					//Toast.makeText(MainLedActivity.this, "timer", Toast.LENGTH_SHORT).show();
					break;
				}
				super.handleMessage(msg);
			}		
		};
		task=new TimerTask()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(!m_bScreenLight) return;
				Message message=new Message();
				message.what=1;	
				handler.sendMessage(message);
			}		
		};	
		task_sos=new TimerTask()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(!m_sos_flag) return;
				Message message=new Message();
				if(m_sos_flight) 
				{
					message.what=1 ;
					m_sos_flight=false;
//					message.what=2 ;
//					m_sos_flight=false;
				}
				else
				{
					message.what=0;	
					m_sos_flight=true;
				}
				handler1.sendMessage(message);
			}		
		};
		handler1=new Handler()
		{
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				
				switch(msg.what)
				{
				case 1:
					setBrightness(1.0f);
					Dengguang.this.findViewById(R.id.background).setBackgroundColor(Color.RED);
					break;
//				case 2:
//					setBrightness(1.0f);
//					Dengguang.this.findViewById(R.id.background).setBackgroundColor(Color.YELLOW);
//					break;
				case 0:
					setBrightness(0.05f);
					Dengguang.this.findViewById(R.id.background).setBackgroundColor(Color.WHITE);
					break;
				}
				super.handleMessage(msg);
			}
			
		};
	}

	private float getBrightness() {
		// TODO Auto-generated method stub
		WindowManager.LayoutParams layoutparam=getWindow().getAttributes();
		return layoutparam.buttonBrightness;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_led, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int nId=v.getId();
		switch(nId)
		{
		case R.id.CameraButton:
			EnableCamerlight();
			break;
		case R.id.ScreenButton:
			Dengguang.this.findViewById(R.id.background).setBackgroundResource(R.drawable.mx_pic);
			EnableScreenlight();
			break;		
		}
		
	}

	private void EnableScreenlight() {
		// TODO Auto-generated method stub
		if(!this.m_bScreenLight)
		{
			
			ScreenButton.setTextColor(0xFF40e0d0);
			this.setBrightness(1.0f);
		}
		else
		{
			ScreenButton.setTextColor(0xFFC0C0C0);
			this.setBrightness(this.m_fScreenLight);
		}
		this.m_bScreenLight=!this.m_bScreenLight;
	}

	private void setBrightness(float f) {
		// TODO Auto-generated method stub
		WindowManager.LayoutParams layoutparam=getWindow().getAttributes();
		layoutparam.screenBrightness=f;
		getWindow().setAttributes(layoutparam);
		
	}

	private void EnableCamerlight() {
		// TODO Auto-generated method stub
		if(!m_bCamera)
		{
			this.OpenLightOn();
			this.m_bCamera=true;
		}
		else
		{
			this.CloseLightOff();
			this.m_bCamera=false;
		}
	}

	private void CloseLightOff() {
		// TODO Auto-generated method stub
		if(this.m_Camera!=null)
		{
			this.m_Camera.stopPreview();
			this.m_Camera.release();
			this.m_Camera=null;
		}
	}

	private void OpenLightOn() {
		// TODO Auto-generated method stub
		if(null==this.m_Camera)
		{
			this.m_Camera=Camera.open();
		}
		Camera.Parameters parameters = m_Camera.getParameters();             
    	parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);  
        m_Camera.setParameters( parameters );           
    	/*m_Camera.autoFocus( new Camera.AutoFocusCallback (){  
        public void onAutoFocus(boolean success, Camera camera) {  
    	        }    	          
    	    });*/
    	m_Camera.startPreview(); 
	}

}
