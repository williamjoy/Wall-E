package com.williamjoy.wall.english.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class VocabularyInput extends EditText {
    private String targetToken;
    private boolean editable = true;
    private TextView nextFocus;

    public VocabularyInput(Context context, String targetToken) {
        super(context);
        this.targetToken = targetToken.trim();
        if (targetToken.matches("[- .,/)(%\\$@#!?\\\"]+")) {
            this.editable = false;
        }
        this.setText(targetToken);
        // this.setInputType(EditorInfo.TYPE_DATETIME_VARIATION_NORMAL);
        if (this.isEditable()) {
            this.setSingleLine();
            this.setSelectAllOnFocus(true);
            this.setTextColor(Color.RED);
            this.setMinimumWidth(50);
     
            this.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                        int count, int after) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                        int before, int count) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().endsWith(" ")) {
                        if (nextFocus != null) {
                            nextFocus.requestFocus();
                            Log.v("Tokens-", s.toString() + ";Next->"
                                    + nextFocus.getText());
                            validateVocabulary();
                        }
                    }
                }
            });
        } else {
            this.setFocusable(false);
            this.setTextColor(Color.MAGENTA);
        }
    }

    protected void validateVocabulary() {
        String input = this.getText().toString().trim();
        if (input.equalsIgnoreCase(targetToken)) {
            this.setTextColor(Color.GREEN);
            this.setText(targetToken);
        } else {
            this.setTextColor(Color.RED);
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public String getTargetToken() {
        return targetToken;
    }

    public void setTargetToken(String targetToken) {
        this.targetToken = targetToken;
    }

    public TextView getNextFocus() {
        return nextFocus;
    }

    public void setNextFocus(TextView nextFocus) {
        this.nextFocus = nextFocus;
    }

}