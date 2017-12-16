package voora.com.customcalc.main;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import voora.com.customcalc.calc.PostFixCalculator;
import voora.com.customcalc.calc.PostFixConverter;
import voora.com.customcalc.calc.PreProcessor;

/**
 * Created by tarun on 12/6/17.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView mainView;
    public String inputString = "";
    private static String TAG = "CALC";

    @Nullable
    PostFixConverter pConverter;

    @Nullable
    PostFixCalculator pCalc;

    @Nullable
    PreProcessor preProcessor;


    MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public String getInputString() {
        return inputString;
    }

    @Override
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void onOperandClick(int value) {
        inputString += String.valueOf(value);
        mainView.setInputBoxText(inputString);
    }

    @Override
    public void onOperatorClick(String operator) {
        inputString += String.valueOf(operator);
        mainView.setInputBoxText(inputString);
    }

    @Override
    public void onCEClick() {
        if(inputString.length() == 0) {
            inputString = "";
        } else {
            inputString = inputString.substring(0, inputString.length() - 1);
        }
        mainView.setInputBoxText(inputString);
    }

    @Override
    public void onClearClick() {
        inputString = "";
        mainView.setInputBoxText(inputString);
    }

    @Override
    public void onPointClick() {
        inputString += ".";
        mainView.setInputBoxText(inputString);
    }

    @Override
    public void onEqualClick() {

        String result = "0";

        if(TextUtils.isEmpty(inputString)) {
            mainView.setInputBoxText(result);
            return;
        }

        Log.d(TAG, "input String :"+ inputString );

        // Before converting to postfix .. it checks for Syntactic errors in input.
        if(preProcessor == null) {
            preProcessor = new PreProcessor();
        }
        inputString = preProcessor.preProcessString(inputString);

        Log.d(TAG, " preprocessed input String :"+ inputString );

        if(inputString.equals(PreProcessor.ERROR)) {
            mainView.setInputBoxText(inputString);
            return;
        }

        // Convert modified inputString to postFix expression.. it returns a list.
        if(pConverter == null) {
            pConverter = new PostFixConverter(inputString);
        } else {
            pConverter.resetData(inputString);
        }
        pConverter.convertToPostFix();

        Log.d(TAG, "Postfix expression list :"+ pConverter.getPostfixList() );

        // It takes the postFix literals list and calculate final calculate.
        if(pCalc == null) {
            pCalc = new PostFixCalculator(pConverter.getPostfixList());
        } else {
            pCalc.resetData(pConverter.getPostfixList());
        }


         try {
             result = pCalc.calculate();
             inputString = result;

         } catch (Exception e) {
            e.printStackTrace();
            result ="CALCULATION ERROR";
            inputString = "";
        }

        Log.d(TAG," calculate , inputString : " + result +" " + inputString);
        mainView.setInputBoxText(result);

    }
}
