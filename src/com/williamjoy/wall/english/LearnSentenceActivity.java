package com.williamjoy.wall.english;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apmem.tools.layouts.FlowLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Space;
import android.widget.TextView;

import com.williamjoy.wall.english.view.VocabularyInput;

public class LearnSentenceActivity extends Activity {
    String learn_content = "It is a truth universally acknowledged, that a single man in possession of a good fortune, must be in want of a wife.\n" + 
    		"However little known the feelings or views of such a man may be on his first entering a neighbourhood, this truth is so well fixed in the minds of the surrounding families, that he is considered the rightful property of some one or other of their daughters.\n" + 
    		"\"My dear Mr. Bennet,\" said his lady to him one day, \"have you heard that Netherfield Park is let at last?\"\n" + 
    		"Mr. Bennet replied that he had not.\n" + 
    		"\"But it is,\" returned she; \"for Mrs. Long has just been here, and she told me all about it.\"\n" + 
    		"Mr. Bennet made no answer.\n" + 
    		"\"Do you not want to know who has taken it?\" cried his wife impatiently.\n" + 
    		"\"You want to tell me, and I have no objection to hearing it.\"\n" + 
    		"This was invitation enough.\n" + 
    		"\"Why, my dear, you must know, Mrs. Long says that Netherfield is taken by a young man of large fortune from the north of England; that he came down on Monday in a chaise and four to see the place, and was so much delighted with it, that he agreed with Mr. Morris immediately; that he is to take possession before Michaelmas, and some of his servants are to be in the house by the end of next week.\"\n" + 
    		"\"What is his name?\"";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_sentence);
        TextView t = (TextView) this.findViewById(R.id.given_content);
        t.setText(learn_content);
        Pattern p = Pattern.compile(
                "([a-z0-9']+(-[a-z0-9]+)*)|[,.()#@\\$!?\\\"]+|\\n+",
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
                Space divider = new Space(getApplicationContext());
                divider.setMinimumHeight(40);
                stubViewGroup.addView(divider);
                stubViewGroup.addView(flowlayout);
                continue;
            }
            flowlayout.addView(new VocabularyInput(getApplicationContext(),
                    token));
        }
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
