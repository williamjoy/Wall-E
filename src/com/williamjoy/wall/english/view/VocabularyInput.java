package com.williamjoy.wall.english.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class VocabularyInput extends EditText {
    private int status = 0;
    private String targetToken;
    private boolean locked=false;

    public VocabularyInput(Context context, String targetToken) {
        super(context);
        this.targetToken = targetToken;
        this.setText(targetToken);
        this.setTextColor(Color.RED);
        this.setMinimumWidth(50);
        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (s.toString().endsWith(" ")) {
                    Log.v("Tokens-", s.toString());
                }
            }
        });

    }

    public String getTargetToken() {
        return targetToken;
    }

    public void setTargetToken(String targetToken) {
        this.targetToken = targetToken;
    }

}
