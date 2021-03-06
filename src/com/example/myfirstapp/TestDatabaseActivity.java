package com.example.myfirstapp;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.myfirstapp.datasource.CommentsDataSource;
import com.example.myfirstapp.model.Comment;

public class TestDatabaseActivity extends ListActivity {

	private CommentsDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_database);

		datasource = new CommentsDataSource(this);
		datasource.open();

		List<Comment> values = datasource.getAllComments();

		// use the SimpleCursorAdapter to show the elements in a ListView
		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.test_database, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Will be called via the onClick attribute of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		Comment comment = null;
		switch (view.getId()) {
			case R.id.add:
				String[] comments = new String[]{ "Cool", "Very nice", "Hate it" };
				int nextInt = new Random().nextInt(3);
				// save the new comment to the database
				comment = datasource.createComment(comments[nextInt]);
				adapter.add(comment);
				break;
			case R.id.delete:
				if (getListAdapter().getCount() > 0) {
					comment = (Comment) getListAdapter().getItem(0);
					datasource.deleteComment(comment);
					adapter.remove(comment);
				}
				break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
