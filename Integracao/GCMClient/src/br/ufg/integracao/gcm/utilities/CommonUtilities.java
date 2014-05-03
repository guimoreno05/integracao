package br.ufg.integracao.gcm.utilities;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {

	public static final String SENDER_ID = "796632224584";

	public static final String TAG = "FastMessage";

	public static final String DISPLAY_MESSAGE_ACTION = "br.ufg.integracao.gcm.DISPLAY_MESSAGE";

	public static final String EXTRA_MESSAGE = "message";

	public static void displayMessage(Context context, String message) {
		Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
		intent.putExtra(EXTRA_MESSAGE, message);
		context.sendBroadcast(intent);
	}
}
