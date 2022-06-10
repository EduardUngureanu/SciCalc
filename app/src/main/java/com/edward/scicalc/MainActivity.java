package com.edward.scicalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    EditText editText;

    Activity activity;

    TextView textView;

    Keyboard keyboard;

    DoubleEvaluator evaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAttributes();

        disableSystemKeyboard();

        initKeyboardInput();

        initEvaluator();
    }

    private void initAttributes() {
        activity = this;

        editText = (EditText) findViewById(R.id.editText);

        textView = (TextView) findViewById(R.id.textView);

        keyboard = (Keyboard) findViewById(R.id.keyboard);
    }

    private void initKeyboardInput() {
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        keyboard.setEditText(editText);

        keyboard.setTextView(textView);
    }

    private void disableSystemKeyboard() {
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setShowSoftInputOnFocus(false);
        editText.setTextIsSelectable(true);
    }

    private void initEvaluator() {
        evaluator = new DoubleEvaluator();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                try
                {
                    double result = evaluator.evaluate(String.valueOf(editText.getText()));
                    String resultStr;
                    if (result == (int) result)
                        resultStr = String.valueOf((int) result);
                    else
                        resultStr = String.valueOf(result);
                    textView.setText(resultStr);
                } catch (IllegalArgumentException e)
                {
                    textView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                return;
            }
        });
    }
}
