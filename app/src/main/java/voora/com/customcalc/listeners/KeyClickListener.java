package voora.com.customcalc.listeners;

/**
 * Created by tarun on 12/6/17.
 */

public interface KeyClickListener {
    void onOperandClick(int value);
    void onOperatorClick(String operator);
    void onCEClick();
    void onClearClick();
    void onPointClick();
    void onEqualClick();
}
