package voora.com.customcalc.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewAnimator;

import voora.com.customcalc.R;

/**
 * Created by tarun on 12/17/17.
 */

public class SendCommentButton extends ViewAnimator implements View.OnClickListener {

    private final int STATE_SEND = 0;
    private final int STATE_DONE  = 1;

    private int state = STATE_SEND;

    public SendCommentButton(Context context) {
        super(context);
        init();
    }

    public SendCommentButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.send_comment_button,this,true);
    }

    public void setCurrentState(int state) {
        if(this.state == state) return;

        this.state = state;

        if(state == STATE_DONE) {
            setInAnimation(getContext(),R.anim.slide_in_done);
            setOutAnimation(getContext(),R.anim.slide_out_send);
            postDelayed(() -> setCurrentState(STATE_SEND),500);
        } else if( state == STATE_SEND) {
            setInAnimation(getContext(),R.anim.slide_in_send);
            setOutAnimation(getContext(),R.anim.slide_out_done);
        }
        showNext();
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {

    }

    @Override
    public void onClick(View v) {

    }
}
