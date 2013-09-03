package com.williamjoy.wall.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

public class ShareToWallEnglish extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		String action = intent.getAction();
		String type = intent.getType();

		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (sharedText != null) {
			Intent i = new Intent(getApplicationContext(),
					LearnSentenceActivity.class);
			i.putExtra("LEARN_CONTENT", sharedText);
			i.putExtra("ENABLE_AUTO_COMPLETE", true);
			startActivity(i);
		}
		finish();
	}
}
