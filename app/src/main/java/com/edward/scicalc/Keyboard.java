package com.edward.scicalc;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Keyboard extends LinearLayout implements View.OnClickListener
{
    private ImageButton mButtonDelete;
    private Button mButtonClear;
    private Button mButton0;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButtonOpenB;
    private Button mButtonCloseB;
    private Button mButtonPi;
    private Button mButtonPow;
    private Button mButtonSin;
    private Button mButtonCos;
    private Button mButtonTan;
    private Button mButtonLog;
    private Button mButtonMultiply;
    private Button mButtonDivide;
    private Button mButtonPlus;
    private Button mButtonMinus;
    private Button mButtonDot;
    private Button mButtonEqual;

    SparseArray<String> keyValues = new SparseArray<>();

    InputConnection inputConnection;

    EditText editText;

    TextView textView;

    public Keyboard(Context context) {
        this(context, null, 0);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);

        initButtons();

        initListener();

        setValues();
    }

    private void initButtons() {
        mButtonDelete = (ImageButton) findViewById(R.id.button_backspace);
        mButtonClear = (Button) findViewById(R.id.button_clear);
        mButton0 = (Button) findViewById(R.id.button_0);
        mButton1 = (Button) findViewById(R.id.button_1);
        mButton2 = (Button) findViewById(R.id.button_2);
        mButton3 = (Button) findViewById(R.id.button_3);
        mButton4 = (Button) findViewById(R.id.button_4);
        mButton5 = (Button) findViewById(R.id.button_5);
        mButton6 = (Button) findViewById(R.id.button_6);
        mButton7 = (Button) findViewById(R.id.button_7);
        mButton8 = (Button) findViewById(R.id.button_8);
        mButton9 = (Button) findViewById(R.id.button_9);
        mButtonOpenB = (Button) findViewById(R.id.button_openb);
        mButtonCloseB = (Button) findViewById(R.id.button_closeb);
        mButtonPi = (Button) findViewById(R.id.button_pi);
        mButtonPow = (Button) findViewById(R.id.button_pow);
        mButtonSin = (Button) findViewById(R.id.button_sin);
        mButtonCos = (Button) findViewById(R.id.button_cos);
        mButtonTan = (Button) findViewById(R.id.button_tan);
        mButtonLog = (Button) findViewById(R.id.button_log);
        mButtonMultiply = (Button) findViewById(R.id.button_multiply);
        mButtonDivide = (Button) findViewById(R.id.button_divide);
        mButtonPlus = (Button) findViewById(R.id.button_plus);
        mButtonMinus = (Button) findViewById(R.id.button_minus);
        mButtonDot = (Button) findViewById(R.id.button_dot);
        mButtonEqual = (Button) findViewById(R.id.button_equal);
    }

    private void initListener() {
        mButtonDelete.setOnClickListener(this);
        mButtonClear.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton0.setOnClickListener(this);
        mButtonOpenB.setOnClickListener(this);
        mButtonCloseB.setOnClickListener(this);
        mButtonPi.setOnClickListener(this);
        mButtonPow.setOnClickListener(this);
        mButtonSin.setOnClickListener(this);
        mButtonCos.setOnClickListener(this);
        mButtonTan.setOnClickListener(this);
        mButtonLog.setOnClickListener(this);
        mButtonMultiply.setOnClickListener(this);
        mButtonDivide.setOnClickListener(this);
        mButtonPlus.setOnClickListener(this);
        mButtonMinus.setOnClickListener(this);
        mButtonDot.setOnClickListener(this);
        mButtonEqual.setOnClickListener(this);
    }

    private void setValues() {
        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "2");
        keyValues.put(R.id.button_3, "3");
        keyValues.put(R.id.button_4, "4");
        keyValues.put(R.id.button_5, "5");
        keyValues.put(R.id.button_6, "6");
        keyValues.put(R.id.button_7, "7");
        keyValues.put(R.id.button_8, "8");
        keyValues.put(R.id.button_9, "9");
        keyValues.put(R.id.button_0, "0");
        keyValues.put(R.id.button_openb, "(");
        keyValues.put(R.id.button_closeb, ")");
        keyValues.put(R.id.button_pi, "pi");
        keyValues.put(R.id.button_pow, "^");
        keyValues.put(R.id.button_sin, "sin(");
        keyValues.put(R.id.button_cos, "cos(");
        keyValues.put(R.id.button_tan, "tan(");
        keyValues.put(R.id.button_log, "log(");
        keyValues.put(R.id.button_multiply, "*");
        keyValues.put(R.id.button_divide, "/");
        keyValues.put(R.id.button_plus, "+");
        keyValues.put(R.id.button_minus, "-");
        keyValues.put(R.id.button_dot, ".");
    }

    @Override
    public void onClick(View v) {

        if (inputConnection == null) return;

        if (v.getId() == R.id.button_backspace) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else if (v.getId() == R.id.button_clear) {
            editText.setText("");
        } else if (v.getId() == R.id.button_equal) {
            editText.setText(textView.getText());
        } else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
