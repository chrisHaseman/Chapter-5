package com.emerjbl.followAlong;

import android.content.Context;

import android.util.AttributeSet;

import android.view.inputmethod.EditorInfo;

import android.widget.EditText;

public class EditTextNext extends EditText {
    public EditTextNext(Context c) {
        this(c, null, 0);
    }
    public EditTextNext(Context c, AttributeSet attrs) {
        this(c, attrs, 0);
    }
    public EditTextNext(Context c, AttributeSet attrs, int defStyle) {
        super(c, attrs, defStyle);
    }
    /*@Override
    public int getImeActionId() {
        return EditorInfo.IME_ACTION_NEXT;
    }
    @Override
    public CharSequence getImeActionLabel() {
        return "Next";
    }*/
}
