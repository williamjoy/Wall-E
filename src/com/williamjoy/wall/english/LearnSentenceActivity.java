package com.williamjoy.wall.english;

import com.williamjoy.wall.english.view.VocabularyInput;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LearnSentenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_sentence);
        ViewGroup l=(ViewGroup) this.findViewById(R.id.wrapper_layout);
        l.addView(new VocabularyInput(getApplicationContext(), "Mary"));
        l.addView(new VocabularyInput(getApplicationContext(), "Yahoo!"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_learn_sentence, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_settings):
                Intent intent = new Intent(this.getApplicationContext(),
                        SettingsActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
