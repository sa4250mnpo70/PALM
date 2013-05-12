package net.incideas.bayad;

import a.a.a.a.b;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.LinkedList;

public class bayadService extends Service
  implements ServiceConnection
{
  private static LinkedList a = new LinkedList();
  private static a.a.a.a.a b;

  private static Intent a(Context paramContext, m paramm)
  {
    Intent localIntent = new Intent(paramContext.getPackageName() + "." + paramm.toString());
    localIntent.setClass(paramContext, bayadService.class);
    return localIntent;
  }

  private void a()
  {
    try
    {
      if (!bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), this, 1))
        Log.e(getClass().getSimpleName(), "Could not bind to MarketBillingService");
      return;
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        Log.e(getClass().getSimpleName(), "Could not bind to MarketBillingService", localSecurityException);
    }
  }

  public static void a(Context paramContext)
  {
    paramContext.startService(a(paramContext, m.a));
  }

  public static void a(Context paramContext, long paramLong)
  {
    Intent localIntent = a(paramContext, m.g);
    localIntent.setClass(paramContext, bayadService.class);
    localIntent.putExtra("EXTRA_NONCE", paramLong);
    paramContext.startService(localIntent);
  }

  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = a(paramContext, m.e);
    localIntent.putExtra("ITEM_ID", paramString);
    localIntent.putExtra("DEVELOPER_PAYLOAD", null);
    paramContext.startService(localIntent);
  }

  public static void a(Context paramContext, String[] paramArrayOfString)
  {
    Intent localIntent = a(paramContext, m.c);
    localIntent.putExtra("NOTIFY_IDS", paramArrayOfString);
    paramContext.startService(localIntent);
  }

  public static void a(Context paramContext, String[] paramArrayOfString, long paramLong)
  {
    Intent localIntent = a(paramContext, m.d);
    localIntent.putExtra("NOTIFY_IDS", paramArrayOfString);
    localIntent.putExtra("EXTRA_NONCE", paramLong);
    paramContext.startService(localIntent);
  }

  private void a(Intent paramIntent, int paramInt)
  {
    m localm = null;
    String str1 = paramIntent.getAction();
    if (str1 == null)
      if (localm != null)
        break label49;
    while (true)
    {
      return;
      String[] arrayOfString = str1.split("\\.");
      if (arrayOfString.length <= 0)
        break;
      localm = m.valueOf(arrayOfString[(-1 + arrayOfString.length)]);
      break;
      label49: switch (c()[localm.ordinal()])
      {
      default:
        break;
      case 1:
        a(new e(getPackageName(), paramInt));
        break;
      case 2:
        a(new f(getPackageName(), paramInt));
        break;
      case 5:
        a(new i(getPackageName(), paramInt, paramIntent.getStringExtra("ITEM_ID"), paramIntent.getStringExtra("DEVELOPER_PAYLOAD")));
        break;
      case 6:
        a(new j(getPackageName(), paramInt, paramIntent.getStringExtra("ITEM_ID"), paramIntent.getStringExtra("DEVELOPER_PAYLOAD")));
        break;
      case 4:
        String str3 = getPackageName();
        long l2 = paramIntent.getLongExtra("EXTRA_NONCE", 0L);
        h localh = new h(str3, paramInt, paramIntent.getStringArrayExtra("NOTIFY_IDS"));
        localh.a(l2);
        a(localh);
        break;
      case 3:
        a(new g(getPackageName(), paramInt, paramIntent.getStringArrayExtra("NOTIFY_IDS")));
        break;
      case 7:
        String str2 = getPackageName();
        long l1 = paramIntent.getLongExtra("EXTRA_NONCE", 0L);
        l locall = new l(str2, paramInt);
        locall.a(l1);
        a(locall);
      }
    }
  }

  private void a(d paramd)
  {
    a.add(paramd);
    if (b == null)
      a();
    while (true)
    {
      return;
      b();
    }
  }

  private void b()
  {
    int i = -1;
    d locald = (d)a.peek();
    if (locald == null)
      if (i >= 0)
        stopSelf(i);
    while (true)
    {
      while (true)
      {
        return;
        if (b != null)
          try
          {
            a.a(locald.a(b), locald);
            a.remove();
            if (i >= locald.f())
              break;
            i = locald.f();
          }
          catch (RemoteException localRemoteException)
          {
            while (true)
              Log.w(getClass().getSimpleName(), "Remote bayad service crashed");
          }
      }
      a();
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (b != null);
    try
    {
      unbindService(this);
      label15: return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label15;
    }
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    b = b.a(paramIBinder);
    b();
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    b = null;
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    a(paramIntent, paramInt);
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    a(paramIntent, paramInt2);
    return net.incideas.bayad.c.d.a;
  }
}
