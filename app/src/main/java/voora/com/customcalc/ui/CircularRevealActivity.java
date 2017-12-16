package voora.com.customcalc.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import voora.com.customcalc.R;

public class CircularRevealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::startRevealView);
    }

    private void startRevealView(View view) {

        CircularRevealView revealView = findViewById(R.id.revealView);

        int[] outLocation = new int[2];
        view.getLocationOnScreen(outLocation);

        Point point = new Point(outLocation[0],outLocation[1]);
        revealView.revealView(point.x,point.y,view.getWidth()/2);


    }
}
