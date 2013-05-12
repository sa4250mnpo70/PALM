package dvoph.apo.palm;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;

public class PALMActivity extends Activity
{
  public static bu b;
  public static Context c;
  static boolean f = true;
  static boolean g = false;
  static boolean h = false;
  static boolean i = true;
  static String j;
  static boolean m = false;
  static boolean n = false;
  TextView a;
  public Prefs d = new Prefs();
  NotificationManager e;
  ProgressDialog k;
  ProgressDialog l;
  private final k o = new k(this);
  private boolean p = true;
  private boolean q = false;
  private Timer r;
  private final BroadcastReceiver s = new r(this);

  private void a(String paramString1, String paramString2)
  {
    File localFile1 = new File(paramString1);
    File localFile2;
    if (localFile1.exists())
      localFile2 = new File(paramString2);
    try
    {
      new File("/mnt/sdcard/palm/backup/ini").mkdirs();
      localFile2.delete();
      localFile2.createNewFile();
      f.a(new BufferedInputStream(new FileInputStream(localFile1)), new FileOutputStream(localFile2));
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
      {
        Toast.makeText(this, "Error! One of the INI not found!", 1).show();
        localFileNotFoundException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Toast.makeText(this, "Error! Could not access to sdcard!", 1).show();
        localIOException.printStackTrace();
      }
    }
  }

  private void b(String paramString)
  {
    String str1 = "";
    String str2 = "";
    String str3 = "";
    String str4 = "";
    String str6;
    String str5;
    if (!g)
    {
      str6 = "...";
      str5 = "off";
      if (h)
        str3 = "MySQL server: on<br>";
      if ((!str2.equalsIgnoreCase("")) && (!paramString.equalsIgnoreCase("0.0.0.0")))
        if (!str2.equalsIgnoreCase("80"))
          break label321;
    }
    label280: label321: for (str1 = str1 + "<br>2. http://" + paramString + "/ (from remote devices)"; ; str1 = str1 + "<br>2. http://" + paramString + ":" + str2 + "/ (from remote devices)")
    {
      this.a.setText(Html.fromHtml("" + "Server status: " + str5 + "<br>Port: " + str6 + "<br>" + str3 + "WIFI IP Address: " + paramString + "<br>Doc root: " + str4 + "<br>" + str1));
      this.a.setMovementMethod(LinkMovementMethod.getInstance());
      return;
      if (b.a("moveInis").equalsIgnoreCase("true"))
      {
        str2 = d.a("/mnt/sdcard/palm/ini/lighttpd.conf", "server.port");
        str4 = d.a("/mnt/sdcard/palm/ini/lighttpd.conf", "server.document-root");
        label221: if (!str2.equalsIgnoreCase("80"))
          break label280;
      }
      for (str1 = str1 + "<br>Type any url from the following list in browser's address bar to get access to server:<br>1. <a href='http://localhost/'>http://localhost/</a> (from your device)"; ; str1 = str1 + "<br>Type any url from the following list in browser's address bar to get access to server:<br>1. <a href='http://localhost:" + str2 + "/'>http://localhost:" + str2 + "/</a> (from your device)")
      {
        str5 = "on";
        str6 = str2;
        break;
        str2 = d.a("/data/data/dvoph.apo.palm/lighttpd.conf", "server.port");
        str4 = d.a("/data/data/dvoph.apo.palm/lighttpd.conf", "server.document-root");
        break label221;
      }
    }
  }

  private void n()
  {
    int i1 = 0;
    List localList = ((ActivityManager)getSystemService("activity")).getRunningServices(100);
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= localList.size())
        i1 = 1;
      ActivityManager.RunningServiceInfo localRunningServiceInfo;
      do
      {
        if (i1 != 0)
          startService(new Intent(this, palmService.class));
        new Timer().schedule(new bt(this, this.o.c), 0L, 5000L);
        return;
        localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localList.get(i2);
      }
      while (palmService.class.getName().equalsIgnoreCase(localRunningServiceInfo.service.getClassName()));
    }
  }

  private void o()
  {
    if (this.e != null)
      this.e.cancelAll();
    d.a(Process.myPid());
    System.exit(0);
  }

  private void p()
  {
    new f(this, this.o.b).start();
  }

  private static boolean q()
  {
    File localFile = new File("/data/data/dvoph.apo.palm/ver1.00");
    if (!localFile.exists());
    while (true)
    {
      try
      {
        localFile.createNewFile();
        bool = true;
        return bool;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        continue;
      }
      boolean bool = false;
    }
  }

  private String r()
  {
    long l1 = ((WifiManager)getSystemService("wifi")).getConnectionInfo().getIpAddress();
    if (l1 < 0L)
      l1 += 4294967296L;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Long.valueOf(l1 & 0xFF);
    arrayOfObject[1] = Long.valueOf(0xFF & l1 >> 8);
    arrayOfObject[2] = Long.valueOf(0xFF & l1 >> 16);
    arrayOfObject[3] = Long.valueOf(0xFF & l1 >> 24);
    return String.format("%d.%d.%d.%d", arrayOfObject);
  }

  public final void a()
  {
    this.p = false;
    android.support.v4.app.a.a(this);
  }

  public final void a(String paramString)
  {
    this.a.setText(Html.fromHtml(paramString));
    this.a.setMovementMethod(LinkMovementMethod.getInstance());
  }

  public final void b()
  {
    this.p = true;
    android.support.v4.app.a.a(this);
  }

  public final void c()
  {
    this.q = true;
    android.support.v4.app.a.a(this);
  }

  public final void d()
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).setPositiveButton(17039370, null).setTitle("Warning!").setMessage(Html.fromHtml("Alert Message here fro Dabawenyo!")).create();
    localAlertDialog.show();
    ((TextView)localAlertDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
  }

  public final void e()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://127.0.0.1:10000/")));
  }

  public final void f()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://127.0.0.1:10001/")));
  }

  public final boolean g()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
    if ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public final void h()
  {
    a("/data/data/dvoph.apo.palm/php.ini", "/mnt/sdcard/palm/backup/ini/php.ini");
    a("/data/data/dvoph.apo.palm/my.ini", "/mnt/sdcard/palm/backup/ini/my.ini");
    a("/data/data/dvoph.apo.palm/lighttpd.conf", "/mnt/sdcard/palm/backup/ini/lighttpd.conf");
    a("/data/data/dvoph.apo.palm/msmtp/etc/msmtprc", "/mnt/sdcard/palm/backup/ini/msmtprc");
  }

  public final void i()
  {
    this.k = ProgressDialog.show(this, "Extract Zip", "Extracting Files...", true, false);
    new bw("phpMyAdmin", "/data/data/dvoph.apo.palm/phpMyAdmin.zip", "/mnt/sdcard/palm/phpMyAdmin/", this, this.o.d).start();
  }

  public final void j()
  {
    this.k = ProgressDialog.show(this, "Extract Zip", "Extracting Files...", true, false);
    new bw("Adminer", "/data/data/dvoph.apo.palm/adminer.zip", "/mnt/sdcard/palm/adminer/", this, this.o.d).start();
  }

  public final void k()
  {
    d.a(Process.myPid());
    l();
  }

  public final void l()
  {
    if (b.a("moveInis").equalsIgnoreCase("true"))
    {
      if ((!new File("/mnt/sdcard/palm/ini/php.ini").exists()) || (!new File("/mnt/sdcard/palm/ini/my.ini").exists()) || (!new File("/mnt/sdcard/palm/ini/lighttpd.conf").exists()))
        this.d.j();
      d.b("/mnt/sdcard/palm/ini/lighttpd.conf", "bin-path", "/data/data/dvoph.apo.palm/php-cgi-ext -c /mnt/sdcard/palm/ini/php.ini");
    }
    d locald = new d(this, new k(this).g);
    new Timer().schedule(new a(locald), 0L, 4000L);
    SharedPreferences localSharedPreferences1 = getSharedPreferences("checkVersion", 0);
    int i1 = localSharedPreferences1.getInt("version_number", 0);
    try
    {
      i2 = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      if (i2 > i1)
      {
        AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this);
        View localView1 = LayoutInflater.from(this).inflate(2130903045, null);
        localBuilder1.setTitle("What's New in palm");
        localBuilder1.setView(localView1);
        localBuilder1.setNeutralButton("OK", new w(this));
        localBuilder1.create().show();
        SharedPreferences.Editor localEditor1 = localSharedPreferences1.edit();
        localEditor1.putInt("version_number", i2);
        localEditor1.commit();
        SharedPreferences.Editor localEditor2 = getSharedPreferences("sec", 0).edit();
        localEditor2.putString("cAU", "");
        localEditor2.commit();
      }
      if (g())
      {
        localSharedPreferences3 = getSharedPreferences("sec", 0);
        localc = new c();
        String str = localc.b(localSharedPreferences3.getString("cntS", "0"));
        if (str.equalsIgnoreCase(""))
          str = "0";
        int i4 = Integer.valueOf(str).intValue();
        if (i4 < 3)
        {
          int i5 = i4 + 1;
          SharedPreferences.Editor localEditor5 = localSharedPreferences3.edit();
          localEditor5.putString("cntS", localc.a(String.valueOf(i5)));
          localEditor5.commit();
        }
      }
      else
      {
        new bq(this).start();
        SharedPreferences localSharedPreferences2 = getSharedPreferences("checkCountStart", 0);
        int i3 = localSharedPreferences2.getInt("countStart", 0);
        if (i3 == 10)
        {
          AlertDialog.Builder localBuilder2 = new AlertDialog.Builder(this);
          View localView2 = LayoutInflater.from(this).inflate(2130903044, null);
          localBuilder2.setTitle("Kumustamus, amigo!");
          localBuilder2.setView(localView2);
          localBuilder2.setNegativeButton("No, thanks", new u(this));
          localBuilder2.setPositiveButton("Rate", new v(this));
          localBuilder2.create().show();
        }
        SharedPreferences.Editor localEditor3 = localSharedPreferences2.edit();
        localEditor3.putInt("countStart", i3 + 1);
        localEditor3.commit();
        n();
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        SharedPreferences localSharedPreferences3;
        c localc;
        int i2 = 0;
        continue;
        SharedPreferences.Editor localEditor4 = localSharedPreferences3.edit();
        localEditor4.putString("cntS", localc.a("0"));
        localEditor4.commit();
        localEditor4.putString("cAU", "");
        localEditor4.commit();
      }
    }
  }

  public final void m()
  {
    b(r());
  }

  public void onBackPressed()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setFlags(268435456);
    startActivity(localIntent);
  }

  public void onCreate(Bundle paramBundle)
  {
    setTitle(getResources().getString(2131165185));
    super.onCreate(paramBundle);
    setContentView(2130903041);
    new File("/data/data/dvoph.apo.palm/restart").delete();
    new File("/mnt/sdcard/palm/").mkdirs();
    new File("/mnt/sdcard/palm/logs").mkdirs();
    new File("/mnt/sdcard/palm/gps").mkdirs();
    new File("/mnt/sdcard/palm/ini").mkdirs();
    new File("/mnt/sdcard/palm/sessions").mkdirs();
    PreferenceManager.setDefaultValues(this, 2130968576, false);
    Context localContext = getApplicationContext();
    Prefs.a(localContext);
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    this.a = ((TextView)findViewById(2131034133));
    c = getApplicationContext();
    b = new bu(c);
    int i1 = Integer.valueOf(b.a("port")).intValue();
    if (Boolean.valueOf(b.a("enableRootFunctions")).booleanValue())
    {
      m = d.d();
      if ((i1 > 0) && (i1 < 1024) && (m))
        n = true;
    }
    while (true)
    {
      if (f)
        d.a(Process.myPid());
      if ((!new File(b.a("rooDir")).exists()) && (new File(b.a("rootDir").substring(0, -1 + b.a("rootDir").length())).mkdirs()));
      try
      {
        BufferedInputStream localBufferedInputStream = new BufferedInputStream(getAssets().open("index.php"));
        File localFile8 = new File(b.a("rootDir") + "index.php");
        localFile8.createNewFile();
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile8);
        f.a(localBufferedInputStream, localFileOutputStream);
        localBufferedInputStream.close();
        localFileOutputStream.close();
        if (b.a("hideTrayIcon").equalsIgnoreCase("false"))
        {
          this.e = ((NotificationManager)getSystemService("notification"));
          Notification localNotification = new Notification(2130837513, "palm v1.00", System.currentTimeMillis());
          localNotification.setLatestEventInfo(getApplicationContext(), "palm", "Server started...", PendingIntent.getActivity(this, 0, getIntent(), 0));
          localNotification.flags = (0x2 | localNotification.flags);
          this.e.notify(1, localNotification);
        }
        if (f)
        {
          f = false;
          File localFile1 = new File("/data/data/dvoph.apo.palm/php.ini");
          File localFile2 = new File("/data/data/dvoph.apo.palm/my.ini");
          File localFile3 = new File("/data/data/dvoph.apo.palm/lighttpd.conf");
          File localFile4 = new File("/data/data/dvoph.apo.palm/php-cgi-ext");
          File localFile5 = new File("/data/data/dvoph.apo.palm/lighttpd");
          File localFile6 = new File("/data/data/dvoph.apo.palm/lighttpd.conf");
          File localFile7 = new File("/data/data/dvoph.apo.palm/mysqld");
          if ((localFile1.exists()) && (localFile2.exists()) && (localFile3.exists()) && (localFile5.exists()) && (localFile6.exists()) && (localFile7.exists()) && (localFile4.exists()) && (!q()))
            break label874;
          if ((localFile1.exists()) && (localFile2.exists()) && (localFile3.exists()))
          {
            AlertDialog.Builder localBuilder2 = new AlertDialog.Builder(this);
            localBuilder2.setTitle("Warning!");
            localBuilder2.setMessage("palm detected that INI files (php.ini, my.ini, lighttpd.conf, msmtprc) already exist in palm system folder. Before replacing these files installation process will backup them to \"../sdcard/palm/backup/ini\" if it is possible. \n\n");
            localBuilder2.setNegativeButton("Exit", new s(this));
            localBuilder2.setPositiveButton("OK", new t(this));
            localBuilder2.create().show();
            if (!b.a("isStartMin").equalsIgnoreCase("true"))
              break label965;
            moveTaskToBack(false);
          }
        }
        else
        {
          return;
          n = false;
          continue;
          if ((i1 <= 0) || (i1 >= 1024))
            continue;
          AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this);
          localBuilder1.setTitle("Warning!");
          localBuilder1.setMessage("The selected port needs root access function to be enabled! Please, go to options and enable it.");
          localBuilder1.setPositiveButton("OK", new ac(this));
          localBuilder1.create().show();
        }
      }
      catch (IOException localIOException)
      {
        label965: 
        while (true)
        {
          localIOException.printStackTrace();
          continue;
          p();
          continue;
          label874: if ((!this.d.a(c, "palm_php_ext")) && (!this.d.a(c, "palm_full_version")))
          {
            new bv(this, this.o.b).start();
          }
          else
          {
            i = false;
            l();
            registerReceiver(this.s, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            b(r());
            continue;
            Bundle localBundle = getIntent().getExtras();
            if ((localBundle != null) && (localBundle.getInt("action") == 100))
              moveTaskToBack(false);
          }
        }
      }
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131361792, paramMenu);
    paramMenu.findItem(2131034145).setVisible(this.p);
    paramMenu.findItem(2131034150).setVisible(this.p);
    paramMenu.findItem(2131034146).setVisible(this.p);
    paramMenu.findItem(2131034151).setVisible(this.p);
    paramMenu.findItem(2131034149).setVisible(this.p);
    paramMenu.findItem(2131034148).setVisible(this.p);
    paramMenu.findItem(2131034147).setVisible(this.p);
    if (this.q)
      paramMenu.findItem(2131034147).setVisible(true);
    return true;
  }

  protected void onDestroy()
  {
    if (this.e != null)
      this.e.cancelAll();
    super.onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = false;
    switch (paramMenuItem.getItemId())
    {
    default:
      return bool;
    case 2131034152:
      AlertDialog localAlertDialog = new AlertDialog.Builder(this).setPositiveButton(17039370, null).setTitle("About").setMessage(Html.fromHtml("Thank you for choosing palm!")).create();
      localAlertDialog.show();
      ((TextView)localAlertDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
    case 2131034153:
    case 2131034148:
    case 2131034149:
    case 2131034147:
    case 2131034150:
    case 2131034145:
    case 2131034151:
    case 2131034146:
    }
    while (true)
    {
      bool = true;
      break;
      o();
      continue;
      if (i)
        System.exit(0);
      moveTaskToBack(false);
      continue;
      if (i)
        System.exit(0);
      k();
      continue;
      startActivity(new Intent(this, this.d.getClass()));
      continue;
      startActivity(new Intent(this, MysqlMonitorActivity.class));
      continue;
      SharedPreferences localSharedPreferences = getSharedPreferences("checkVersion", 0);
      int i1 = localSharedPreferences.getInt("web_interface_count_button_click", 0);
      if (i1 > 0)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://127.0.0.1:9000/")));
      }
      else
      {
        int i2 = i1 + 1;
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        localEditor.putInt("web_interface_count_button_click", i2);
        localEditor.commit();
        AlertDialog.Builder localBuilder2 = new AlertDialog.Builder(this);
        localBuilder2.setTitle("Notice!");
        localBuilder2.setMessage("Web Interface login and password by default: \"admin\". We strongly recommend to change the password for security reasons!");
        localBuilder2.setPositiveButton("OK", new ab(this));
        localBuilder2.create().show();
        continue;
        if (i)
          System.exit(0);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(b.a("goto"))));
        continue;
        if (i)
          System.exit(0);
        AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this);
        localBuilder1.setTitle("Make Your Choice");
        localBuilder1.setMessage("What do you want to start?");
        localBuilder1.setPositiveButton("ADMINER", new x(this));
        localBuilder1.setNegativeButton("PHPMYADMIN", new y(this));
        localBuilder1.create().show();
      }
    }
  }

  protected void onPause()
  {
    if (this.r != null)
    {
      this.r.cancel();
      this.r = null;
    }
    super.onPause();
  }

  public void onResume()
  {
    if (!i)
      b(r());
    if (this.r == null)
    {
      this.r = new Timer();
      this.r.schedule(new b(this.o.f), 0L, 3000L);
    }
    super.onResume();
  }

  protected void onStop()
  {
    try
    {
      unregisterReceiver(this.s);
      label8: super.onStop();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label8;
    }
  }
}
