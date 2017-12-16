package voora.com.customcalc.listeners;

import android.view.View;

public class OperandClickListener implements View.OnClickListener  {

    private int value;
    private KeyClickListener keyClickListener;

    public OperandClickListener(Integer value,KeyClickListener keyClickListener) {
        this.value = value;
        this.keyClickListener = keyClickListener;
    }

    @Override
    public void onClick(View v) {
            keyClickListener.onOperandClick(value);
    }
}
    