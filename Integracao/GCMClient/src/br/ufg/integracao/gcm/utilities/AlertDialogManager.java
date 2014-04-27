package br.ufg.integracao.gcm.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import br.ufg.mobile.gcm.R;

public class AlertDialogManager {

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);

		if (status != null)
			alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
		});
		alertDialog.show();
	}
}
