package voora.com.customcalc.listeners;

import android.view.View;

public class OperatorClickListener implements View.OnClickListener {


    private String operator;
    private KeyClickListener keyClickListener;

    public OperatorClickListener(String operator,KeyClickListener keyClickListener) {
        this.operator = operator;
        this.keyClickListener = keyClickListener;
    }

    @Override
    public void onClick(View v) {

        switch (operator) {
            case "CE":
                keyClickListener.onCEClick();
                break;
            case "CL":
                keyClickListener.onClearClick();
                break;
            case ".":
                keyClickListener.onPointClick();
                break;
            case "=" :
                keyClickListener.onEqualClick();
                break;
            default:
                keyClickListener.onOperatorClick(operator);
        }
    }
}