package com.williamjoy.wall.english.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class VocabularyInput extends EditText {
    private String targetToken;
    private String prevText;
    private boolean editable = true;
    private TextView nextFocus = this;
    private TextView prevFocus = this;

    public TextView getPrevFocus() {
        return prevFocus;
    }

    public void setAutoComplete(boolean autoComplete) {

        this.setInputType(InputType.TYPE_CLASS_TEXT
                | (autoComplete ? InputType.TYPE_TEXT_VARIATION_NORMAL
                        : InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS));
    }

    public VocabularyInput(Context context, String targetToken) {
        super(context);
        this.targetToken = targetToken.trim();
        if (targetToken.matches("[- .,/)(%\\$@#!?\\\"]+")) {
            this.editable = false;
            this.setText(targetToken);
        }
        this.prevText = this.getText().toString();
        this.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        this.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS
                | EditorInfo.IME_ACTION_NEXT);
        if (this.isEditable()) {
            this.setGravity(Gravity.CENTER_HORIZONTAL);
            this.setSingleLine();
            this.setSelectAllOnFocus(true);
            this.setTextColor(Color.RED);
            this.setMinWidth(targetToken.length() * 20);
            this.setTypeface(Typeface.MONOSPACE);
            this.setOnFocusChangeListener(new OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus)
                        validateVocabulary();
                    else
                        snapshort();
                }
            });
            this.setOnKeyListener(new OnKeyListener() {

                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DEL && prevText.isEmpty()) {
                        prevFocus.requestFocus();
                    }
                    snapshort();
                    return false;
                }
            });
            this.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                        int count, int after) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                        int before, int count) {
                    /*
                     * If appending a space , focus on next widget
                     */
                    if (s.toString().endsWith(" ") && count > 0) {
                        nextFocus.requestFocus();
                        validateVocabulary();
                    }
                    snapshort();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            this.setOnEditorActionListener(new OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId,
                        KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        nextFocus.requestFocus(FOCUS_RIGHT);
                        handled = true;
                    }
                    return handled;
                }
            });
        } else {
            this.setFocusable(false);
            this.setTextColor(Color.MAGENTA);
        }
    }

    protected void snapshort() {
        this.prevText = getText().toString();
    }

    protected void validateVocabulary() {
        String input = this.getText().toString().trim();
        if (input.equalsIgnoreCase(targetToken)) {
            this.setTextColor(Color.GREEN);
            this.setText(targetToken);
        } else {
            this.setTextColor(Color.RED);
            if (!input.equalsIgnoreCase(this.getText().toString())) {
                this.setText(input);
            }
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

    public void setPrevFocus(TextView prevFocus) {
        this.prevFocus = prevFocus;
    }

}