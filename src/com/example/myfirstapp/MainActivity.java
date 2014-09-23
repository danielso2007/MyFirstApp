package com.example.myfirstapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; este adiciona itens à barra de ação se ela
		// estiver presente.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item da barra de acção clica aqui. A barra de ação irá
		// tratar automaticamente os
		// cliques no botão Home / Up, contanto que você especificar uma
		// atividade pai no AndroidManifest.xml.
		switch (item.getItemId()) {
			case R.id.action_search:
				openSearch();
				return true;
			case R.id.action_settings:
				openSettings();
				return true;
			case R.id.action_dataBase:
				openDataBase();
				return true;
			case R.id.action_process:
				openProcesso();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void openProcesso() {
		Intent intent = new Intent(this, ProgressBarView.class);
		startActivity(intent);
	}

	private void openDataBase() {
		Intent intent = new Intent(this, TestDatabaseActivity.class);
		startActivity(intent);
	}

	private void openSettings() {
		System.out.println("Setting");
		createNotification(null);
	}

	private void openSearch() {
		System.out.println("Search");
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void createNotification(View view) {
		// Prepare intent which is triggered if the
		// notification is selected
		Intent intent = new Intent(this, TestDatabaseActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		// Build notification
		// Actions are just fake
		Notification noti = new Notification.Builder(this)
				.setContentTitle("New mail from " + "test@gmail.com")
				.setContentText("Subject")
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(pIntent)
				.addAction(R.drawable.ic_launcher, "Call", pIntent)
				.addAction(R.drawable.ic_launcher, "More", pIntent)
				.addAction(R.drawable.ic_launcher, "And more", pIntent)
				.build();
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// hide the notification after its selected
		noti.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(0, noti);

	}

}
