package net.mitchtech.ioio;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.AbstractIOIOActivity;
import net.mitchtech.ioio.simpledigitaloutput.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SimpleDigitalOutputActivity extends AbstractIOIOActivity implements OnClickListener {
	
	private final int LED1_PIN = 34;
	private final int LED2_PIN = 35;
	private final int LED3_PIN = 36;
	
	private Button mLed1Button, mLed2Button, mLed3Button;

	private boolean mLed1State = false;
	private boolean mLed2State = false;
	private boolean mLed3State = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mLed1Button = (Button) findViewById(R.id.btn1);
		mLed1Button.setOnClickListener(this);
		mLed2Button = (Button) findViewById(R.id.btn2);
		mLed2Button.setOnClickListener(this);
		mLed3Button = (Button) findViewById(R.id.btn3);
		mLed3Button.setOnClickListener(this);
	}

	class IOIOThread extends AbstractIOIOActivity.IOIOThread {
		private DigitalOutput mLed1;
		private DigitalOutput mLed2;
		private DigitalOutput mLed3;

		@Override
		protected void setup() throws ConnectionLostException {
			mLed1 = ioio_.openDigitalOutput(LED1_PIN, false);
			mLed2 = ioio_.openDigitalOutput(LED2_PIN, false);
			mLed3 = ioio_.openDigitalOutput(LED3_PIN, false);
		}

		@Override
		protected void loop() throws ConnectionLostException {
			mLed1.write(mLed1State);
			mLed2.write(mLed2State);
			mLed3.write(mLed3State);
			try {
				sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	protected AbstractIOIOActivity.IOIOThread createIOIOThread() {
		return new IOIOThread();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			if (mLed1State == true) {
				mLed1State = false;
				mLed1Button.setText("LED Off");
			} else {
				mLed1State = true;
				mLed1Button.setText("LED On");
			}
			break;

		case R.id.btn2:
			if (mLed2State == true) {
				mLed2State = false;
				mLed2Button.setText("LED Off");
			} else {
				mLed2State = true;
				mLed2Button.setText("LED On");
			}
			break;

		case R.id.btn3:
			if (mLed3State == true) {
				mLed3State = false;
				mLed3Button.setText("LED Off");
			} else {
				mLed3State = true;
				mLed3Button.setText("LED On");
			}
			break;

		default:
			break;
		}
	}
}