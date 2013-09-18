package com.williamjoy.wall.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.ActionMode;
import android.widget.TextView;

public class TextReaderActivity extends Activity {
	String learn_content = "Dedicated to my little Mary. :)";
	public static final String LEARN_CONTENT = "LEARN_CONTENT";
	ActionMode.Callback mActionModeCallback;
	ActionMode mActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_reader_layout);
		TextView t = (TextView) this.findViewById(R.id.textViewReader);

		Intent intent = getIntent();
		String input = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (input != null && !input.isEmpty()) {
			learn_content = input;
		}
		learn_content = learn_content.replaceAll("([A-Za-z0-9'’]+(-[a-z0-9]+)*)", "<a href=\"http://www.google.com/search?tbm=isch&q=$1\">$1</a>")+ "</br>"+
				learn_content.replaceAll("([A-Za-z0-9'’]+(-[a-z0-9]+)*)", "<a href=\"dict://$1\">$1</a>");
		t.setText(Html.fromHtml(learn_content));
		t.setMovementMethod(LinkMovementMethod.getInstance());
		Log.d("HTML", learn_content);

	}
}
