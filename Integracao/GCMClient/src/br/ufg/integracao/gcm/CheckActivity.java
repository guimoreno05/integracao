package br.ufg.integracao.gcm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import br.ufg.integracao.gcm.utilities.AlertDialogManager;
import br.ufg.integracao.gcm.utilities.ConnectionDetector;
import br.ufg.mobile.gcm.R;

import com.google.android.gcm.GCMRegistrar;

import static br.ufg.integracao.gcm.utilities.CommonUtilities.SENDER_ID;

public class CheckActivity extends Activity {
	AlertDialogManager alert = new AlertDialogManager();
	ConnectionDetector cd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		cd = new ConnectionDetector(getApplicationContext());

		if (!cd.isConnectedToInternet()) {
			alert.showAlertDialog(CheckActivity.this, "Falha na Conexão", "Por favor conecte-se a internet", false);
			return;
		}

		if (GCMRegistrar.isRegisteredOnServer(this)){
			Toast.makeText(getApplicationContext(), "Pronto para receber notificações", Toast.LENGTH_LONG).show();
			Log.d("Registration ID", GCMRegistrar.getRegistrationId(this));
			Intent i = new Intent(getApplicationContext(), ShowMessageActivity.class);
			startActivity(i);
			finish();
		} else {
			GCMRegistrar.register(this, SENDER_ID);
			Intent i = new Intent(getApplicationContext(), ShowMessageActivity.class);
			startActivity(i);
			finish();
		}
	}
}
