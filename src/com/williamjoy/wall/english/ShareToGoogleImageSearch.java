package com.williamjoy.wall.english;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ShareToGoogleImageSearch extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		String queryText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (queryText != null) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com/search?tbm=isch&q="
							+ Uri.encode(queryText))));
		}else{
			 Uri uri = intent.getData();
			if (uri != null) {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://www.google.com/search?tbm=isch&q="
								+ Uri.encode(uri.getHost()))));
			}
		}

		finish();
	}
}
