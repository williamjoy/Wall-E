package com.williamjoy.wall.english;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apmem.tools.layouts.FlowLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

public class TextReaderActivity extends Activity {
	String learn_content = "Dedicated to my little Mary. :)";
	public static final String LEARN_CONTENT = "LEARN_CONTENT";
	ActionMode.Callback mActionModeCallback;
	ActionMode mActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn_sentence);
		TextView t = (TextView) this.findViewById(R.id.given_content);
		prepareActionCallBack();
		Intent intent = getIntent();
		String input = intent.getStringExtra(LEARN_CONTENT);
		if (input != null && !input.isEmpty()) {
			learn_content = input;
		}
		learn_content = learn_content.replace('’', '\'');
		t.setText(learn_content);
		Pattern p = Pattern.compile(
				"([a-z0-9']+(-[a-z0-9]+)*)|[,.()#@\\$!?\\\":]+|\\n+",
				Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(learn_content);
		ViewGroup stubViewGroup = (ViewGroup) this
				.findViewById(R.id.passage_layout);
		FlowLayout flowlayout = new FlowLayout(this.getApplicationContext());

		stubViewGroup.addView(flowlayout);
		while (m.find()) {
			String token = m.group(0);
			if (token.matches("\\n+")) {
				flowlayout = new FlowLayout(this.getApplicationContext());
				/*
				 * android.widget.Space @since API level 14 TODO: replace with
				 * back compatible widget
				 */
				Space divider = new Space(getApplicationContext());
				divider.setMinimumHeight(40);
				stubViewGroup.addView(divider);
				stubViewGroup.addView(flowlayout);
				continue;
			}
			TextView current = new TextView(getApplicationContext());
			current.setText(token);
			flowlayout.addView(current);
			current.setOnLongClickListener(new View.OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					if (mActionMode != null) {
						return false;
					}

					mActionMode = startActionMode(mActionModeCallback);
					v.setSelected(true);
					return true;
				}
			});

		}
	}

	private void prepareActionCallBack() {
		mActionModeCallback = new ActionMode.Callback() {
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			public void onDestroyActionMode(ActionMode mode) {
				mActionMode = null;
			}

			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				mode.setTitle("Demo");
				getMenuInflater().inflate(R.menu.activity_learn_sentence, menu);
				return true;
			}

			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
				case R.id.menu_about:
					Toast.makeText(getBaseContext(), "Menu1 is selcted",
							Toast.LENGTH_LONG).show();
					mode.finish();
					break;

				}
				return false;
			};
		};
	}
}
