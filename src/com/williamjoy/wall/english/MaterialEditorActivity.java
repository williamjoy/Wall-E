package com.williamjoy.wall.english;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MaterialEditorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_editor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.material_editor, menu);
        return true;
    }

    public void onButtonCommitContentClicked(View v) {
        String inputText = ((EditText) this
                .findViewById(R.id.editTextInputContent)).getText().toString();

        Intent intent = new Intent(getApplicationContext(),
                LearnSentenceActivity.class);
        intent.putExtra("LEARN_CONTENT", inputText.replace('’', '\''));
        startActivityForResult(intent, 0);
    }

}
