package mobilefase.example.com.rocketman;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class BackgroundActivity extends Activity {
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			finish();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_background);
		
		ImageView iv_top = (ImageView) findViewById(R.id.iv_top);
		ImageView iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
		
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(500);
		iv_top.startAnimation(alphaAnimation);
		iv_bottom.startAnimation(alphaAnimation);
		
		mHandler.sendEmptyMessageDelayed(0, 1000);
	}
}
