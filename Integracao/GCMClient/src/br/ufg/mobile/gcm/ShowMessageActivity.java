package br.ufg.mobile.gcm;

import static br.ufg.mobile.gcm.utilities.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static br.ufg.mobile.gcm.utilities.CommonUtilities.EXTRA_MESSAGE;
import static br.ufg.mobile.gcm.utilities.CommonUtilities.SENDER_ID;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import br.ufg.mobile.gcm.R;
import br.ufg.mobile.gcm.utilities.AlertDialogManager;
import br.ufg.mobile.gcm.utilities.WakeLocker;

import com.google.android.gcm.GCMRegistrar;

public class ShowMessageActivity extends Activity {
	TextView lblMessage;
	AlertDialogManager alert = new AlertDialogManager();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_message);
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		lblMessage = (TextView) findViewById(R.id.lblMessage);

		if (getIntent().getStringExtra(EXTRA_MESSAGE) != null) {
			lblMessage.setTextColor(Color.YELLOW);
			lblMessage.setText(getIntent().getStringExtra(EXTRA_MESSAGE) + "\n");
		}

		registerReceiver(mHandleMessageReceiver, new IntentFilter(DISPLAY_MESSAGE_ACTION));

		final String regId = GCMRegistrar.getRegistrationId(this);
		System.err.print(regId);
		
		if (regId.equals("")) {
			GCMRegistrar.register(this, SENDER_ID);
		} else {
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				return;
			} else {
				GCMRegistrar.setRegisteredOnServer(this, true);
			}
		}
	}

	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
			WakeLocker.acquire(getApplicationContext());
			lblMessage.append(newMessage + "\n");
			Toast.makeText(getApplicationContext(),"Nova Mensagem: " + newMessage, Toast.LENGTH_LONG).show();
			WakeLocker.release();
		}
	};

	@Override
	protected void onDestroy() {
		try {
			unregisterReceiver(mHandleMessageReceiver);
			GCMRegistrar.onDestroy(this);
		} catch (Exception e) {
			Log.e("Erro no unregisterReceiver", "> " + e.getMessage());
		}
		super.onDestroy();
	}

}
