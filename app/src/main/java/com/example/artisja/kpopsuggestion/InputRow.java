package com.example.artisja.kpopsuggestion;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.artisja.kpopsuggestion.HomeActivity.context;

/**
 * Created by artisja on 5/21/17.
 */

public class InputRow extends RelativeLayout{

    View rootView;
    EditText inputEdit;
    TextView rowLabel;
    private String label,hint;

    public InputRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs,context);
    }

    private void init(AttributeSet attrs,Context context) {
        rootView.inflate(context,R.layout.input_row,this);
        inputEdit = (EditText) findViewById(R.id.name_edit);
        rowLabel = (TextView) findViewById(R.id.name_label);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.InputRow,
                0, 0);

        try {
            label = a.getString(R.styleable.InputRow_label);
        } finally {
            a.recycle();
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        rowLabel.setText(label);
    }

    public void setHint(String hint) {
        this.hint = hint;
        inputEdit.setHint(hint);
    }
}
