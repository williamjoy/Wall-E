package com.williamjoy.wall.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ShareToWallEnglish extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		
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
