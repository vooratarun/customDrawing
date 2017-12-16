package voora.com.customcalc.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;

import voora.com.customcalc.R;
import voora.com.customcalc.listeners.KeyClickListener;
import voora.com.customcalc.listeners.OperandClickListener;
import voora.com.customcalc.listeners.OperatorClickListener;


/**
 * Main calculator Activity follow basic MVP and implements KeyClickListener on which operator and
 * operand clicks will be transferred to Presenter.
 */

public class MainActivity extends AppCompatActivity implements KeyClickListener,IMainView {

    private static final String SAVED_INPUT = "SAVED_INPUT";

    private TextView inputBox;
    private IMainPresenter presenter;

    private ArrayList<Integer> operandList = new ArrayList<Integer>() {{
        add(R.id.zero);
        add(R.id.one);
        add(R.id.two);
        add(R.id.three);
        add(R.id.four);
        add(R.id.five);
        add(R.id.six);
        add(R.id.seven);
        add(R.id.eight);
        add(R.id.nine);
    }};

    private ArrayList<Integer> operatorList = new ArrayList<Integer>() {{
        add(R.id.add);
        add(R.id.subtract);
        add(R.id.multiply);
        add(R.id.divide);
        add(R.id.modulo);
        add(R.id.power);
        add(R.id.equals);
        add(R.id.cl);
        add(R.id.ce);
        add(R.id.point);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        inputBox = findViewById(R.id.inputBox);

        presenter = new MainPresenter(this);

        if( savedInstanceState != null) {
            presenter.setInputString(savedInstanceState.getString(SAVED_INPUT,""));
            setInputBoxText(presenter.getInputString());
        }

        setOperandListeners();
        setOperatorListeners();
    }



    /**
     * Sets listeners for all the numbers. Handled by custom OperandClickListener.
     */
    private void setOperandListeners() {
        for(Integer id : operandList) {
            TextView operand = findViewById(id);
            operand.setTag(String.valueOf(id));
            int value = Integer.valueOf(operand.getText().toString());
            operand.setOnClickListener(new OperandClickListener(value,this));
        }
    }

    /**
     * Sets listeners for all the Operator. Handles by Custom OperatorListener.
     * Operators includes
     */
    private void setOperatorListeners() {
        for(Integer id : operatorList) {
            TextView operator = findViewById(id);
            operator.setTag(String.valueOf(id));
            String value = operator.getText().toString();
            operator.setOnClickListener(new OperatorClickListener(value,this));
        }
    }

    @Override
    public void onOperandClick(int value) {
      presenter.onOperandClick(value);
    }

    @Override
    public void onOperatorClick(String operator) {
        presenter.onOperatorClick(operator);
    }

    @Override
    public void onCEClick() {
       presenter.onCEClick();
    }

    @Override
    public void onClearClick() {
       presenter.onClearClick();
    }

    @Override
    public void onPointClick() {
       presenter.onPointClick();
    }

    @Override
    public void onEqualClick() {
        presenter.onEqualClick();
    }

    @Override
    public void setInputBoxText(@NonNull String text) {
        if(TextUtils.isEmpty(text)) {
            inputBox.setText("0");
        } else {
            inputBox.setText(text);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVED_INPUT, presenter.getInputString());
        super.onSaveInstanceState(outState);
    }
}