package com.example.enclaveit.app.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.example.enclaveit.app.R;

public class ActivityPendingIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent);

        this.findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                PendingIntent pendingIntent = PendingIntent.getActivity(ActivityPendingIntent.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(ActivityPendingIntent.this);
                builder.setContentTitle("Activity");
                builder.setContentText("I will inform about activity on 26th");
                builder.addAction(R.mipmap.ic_launcher,"Action 1",pendingIntent);
                builder.addAction(R.mipmap.ic_launcher,"Action 2",pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);

                Notification notification = builder.build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,notification);

            }
        });
    }
}
