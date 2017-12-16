package voora.com.customcalc.main;

import voora.com.customcalc.listeners.KeyClickListener;

/**
 * Created by tarun on 12/6/17.
 */

public interface IMainPresenter extends KeyClickListener {

    String getInputString();
    void setInputString(String inputString);
}
