package net.incideas.bayad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class bayadReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    a.a("Received " + str);
    if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(str))
      a.a(paramContext, paramIntent.getStringExtra("inapp_signed_data"), paramIntent.getStringExtra("inapp_signature"));
    while (true)
    {
      return;
      if ("com.android.vending.billing.IN_APP_NOTIFY".equals(str))
        a.c(paramContext, paramIntent.getStringExtra("notification_id"));
      else if ("com.android.vending.billing.RESPONSE_CODE".equals(str))
        a.a(paramIntent.getLongExtra("request_id", -1L), paramIntent.getIntExtra("response_code", 0));
      else
        Log.w(getClass().getSimpleName(), "Unexpected action: " + str);
    }
  }
}
