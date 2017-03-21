# Technical-Android-Notification
  - Instruct you about Notification Android
    - Searches related to Android Notifications
      - android push notifications
      - android notifications on lock screen
      - android notifications tutorial
      - android notifications settings
      - android notifications app
      - android notifications disable
      - android notifications example
      - android notifications icons
      - Android Push Notification Tutorial
  - Demonstration 
    - Research PendingIntent
      - PendingIntent definition
      - PendingIntent tutorial
      - PendingIntent event button
      - PendingInent event touch
      - PendingIntent relative Acitivity or Intent
      - Example about PendingIntent:
      
              import android.app.Notification;
              import android.app.NotificationManager;
              import android.app.PendingIntent;
              import android.content.Intent;
              import android.os.Build;
              import android.support.annotation.RequiresApi;
              import android.support.v7.app.AppCompatActivity;
              import android.os.Bundle;
              import android.view.View;
              import android.widget.Button;

              import com.example.enclaveit.app.R;

              public class ActivityDemo extends AppCompatActivity {

              Button btn;
              @Override
              protected void onCreate(Bundle savedInstanceState) {
                  super.onCreate(savedInstanceState);
                  setContentView(R.layout.activity_demo);

                  btn = (Button)this.findViewById(R.id.btn);

                  btn.setOnClickListener(new View.OnClickListener() {
                      @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                      @Override
                      public void onClick(View view) {
                          Intent intent = new Intent();
                          PendingIntent pendingIntent = PendingIntent.getActivity(ActivityDemo.this,0,intent,0);
                          Notification notification = null;
                          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                              notification = new Notification.Builder(ActivityDemo.this)
                                      .setTicker("Ticker Title")
                                      .setContentTitle("Do senior project")
                                      .setContentText("Perfectly")
                                      .addAction(R.mipmap.ic_launcher,"Action 1",pendingIntent)
                                      .addAction(R.mipmap.ic_launcher,"Action 2",pendingIntent)
                                      .setSmallIcon(R.mipmap.ic_launcher)
                                      .setContentIntent(pendingIntent).getNotification();
                          }

                          notification.flags = Notification.FLAG_AUTO_CANCEL;

                          NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                          nm.notify(0,notification);
                      }
                  });
              }
            }


# Research notification
  - Notification will include title, message and icon
  - Message is sent from <a href="http://i.imgur.com/QcZ0h7C.png" target="_blank">Firebase Console UI</a>
  - You need to use notification key in json data. This is a link 
  - You need to have a server side logic to send the notification using Firebase API
    - Server side: SchoolManager
    - Firebase API
    - You need to use <a href="http://i.imgur.com/dOybfVx.png" target="_blank">data key</a> when sending this message.
    - data payload + message like that <a href="http://i.imgur.com/AkWi5um.png" target="_blank"></a>
  
  - Structure of message:
    [ messagetype="" messagecontent="" messagetarget="single or group" ]
    
  - Integrating Firebase Cloud Messaging
    
# Practise notification
  - Go to page: <a href="https://firebase.google.com/" target="_blank">firebase UI</a>
  - Set the same package of your project
  - Paste the google-services.json file to your project’s app folder
  - ...
  
# Technical Code Class
  - Use <b>public final static String</b> when you want to create a String that:
    - belongs to the class (static: no instance necessary to use it), and that
    - Won't change (final), for instance when you want to define a String constant that will be available to all instances of the class, and to other objects using the class    
    - Example about <b>public final static String</b>
      - Express operation equal
      
            public final static String MY_CONSTANT = "SomeValue";
  
            // ... in some other code, possibly in another object, use the constant:
            if (input.equals(MyClass.MY_CONSTANT)
# MainAcitivity
  - BroadCastRceiver: 
  - The question is set out: I am new to android. I what to know the difference between Intent and BroadcastReceiver. I am more confused with BroadcastReceiver than Intent              ?
  - This is answer about it: 
    - I want to create an app to check subway status from it's webpage. I also want a system notification if the subway is not      working.
    - I will have:
      - An Activity to show results.
      - A Service to check if the subway is working and show a notification if it's not working.
      - A Broadcast Receiver called Alarm Receiver to call the service every 15 minutes.
    - Example about it:
    
          /* AlarmReceiver.java */
          public class AlarmReceiver extends BroadcastReceiver {
              public static final String ACTION_REFRESH_SUBWAY_ALARM =
                    "com.x.ACTION_REFRESH_SUBWAY_ALARM";

              @Override
              public void onReceive(Context context, Intent intent) {
                  Intent startIntent = new Intent(context, StatusService.class);
                  context.startService(startIntent);
              }
          }
    
          - startActivity(new Intent()) => A other activity is called when Intent contain information to call other activity
          - startService(new Intent()) => A intend contain a type of service
          And intent is a message.
          - We define a type of service
          
          public class StatusService extends Service {
            @Override
            public void onCreate() {
                super.onCreate();
                mAlarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intentToFire = new Intent(AlarmReceiver.ACTION_REFRESH_ALARM);
                mAlarmIntent = PendingIntent.getBroadcast(this, 0, intentToFire, 0);
            }

            @Override
            public void onStart(Intent intent, int arg1) {
                super.onStart(intent, arg1);
                Log.d(TAG, "SERVICE STARTED");
                setAlarm();
                Log.d(TAG, "Performing update!");
                new SubwayAsyncTask().execute();
                stopSelf();
            }

            private void setAlarm() {
                int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;
                mAlarms.setInexactRepeating(alarmType, SystemClock.elapsedRealtime() + timeToRefresh(),
                            AlarmManager.INTERVAL_HALF_DAY, mAlarmIntent);
            }}
  
    - A BroadcastReceiver is a base class for code that will receive intents sent by sendBroadcast().
    
# intent
 - [ intent.getAction() [ ... ] ]
 
 
# Shared Preferences
  - SAVING DATA
    [[[[ ..........data................]]]]
    [ getSharedPreferences [ [ [ ........ data ........ ]]]]
    [ getSharedPreferences [ .editor [ [........ data ....... ]]]]
    [ getSharedPreferences [ .editor [ .putString [........ data ....... ]]]]
    [ getSharedPreferences [ .editor [ .apply [........ data ....... ]]]]
    
  - EXPORT DATA
    [[[[ ..........data................]]]]
    [ getSharedPreferences [ [ [ ........ data ........ ]]]] // Case 1
    [ getSharedPreferences [ .getString() [ [ ........ data ........ ]]]]
    
    [ getApplicationContext().getSharedPreference(...) [ [ [ ........ data ........ ]]]] // Case 2
    
# Intent Filter
  - I will explain it to you
    [ [ ... IMPLICIT INTENT ... ] ]
    - Case 1: [ Normal [ ... IMPLICIT INTENT ... ] ]
    - Case 2: [ Intent Filter [IMPLICIT INTENT]] ... IMPLICIT INTENT ... ] ] => Create a new choose 
    
# NotificationUtils
  - [[[[[[ .... Message .... ]]]]]]
  - [ Activity.class.getSimpleName() [[[[[ .... Message .... ]]]]]]
  
  - showNotificationMessage
    - [ Activity.class.getSimpleName() [ showNotificationMessage [[[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ validate message [[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ validate message [ [[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ new Intent().setFlags [[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ PendingIntent.getActivity() [[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ new NotificationCompat.Builder [[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ Uri.parse(Path of music) [[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ validateImageURL [[[ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ validateImageURL [ choose - ImageURL [[ .... Message .... ]]]]]]
      - [ Activity.class.getSimpleName() [ showNotificationMessage [ validateImageURL [ choose - ImageURL [ showBigNotification() [ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ validateImageURL [ choose - Icon [[ .... Message .... ]]]]]]
      - [ Activity.class.getSimpleName() [ showNotificationMessage [ validateImageURL [ choose - Icon [ showSmallNotification() [ .... Message .... ]]]]]]
    - [ Activity.class.getSimpleName() [ showNotificationMessage [ validateImageURL [ playNotificationSound() [[ .... Message .... ]]]]]]
 
# Final Keyword in method paramaters
  - Java always makes a copy of parameters before sending them to methods. This means the final doesn't mean any difference for the calling code. This only means that inside the method the variables can not be reassigned. (note that if you have a final object, you can still change the attributes of the object).
  
  
  
 
    

