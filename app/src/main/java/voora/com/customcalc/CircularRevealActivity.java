package voora.com.customcalc;

import android.graphics.Point;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import voora.com.customcalc.R;
import voora.com.customcalc.ui.BgRevealView;
import voora.com.customcalc.ui.CircularRevealView;
import voora.com.customcalc.ui.TextDrawable;

public class CircularRevealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((ImageView)findViewById(R.id.ipower))
                .setImageDrawable(new TextDrawable(new RectShape()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::startRevealView);
    }

    private void startRevealView(View view) {

        CircularRevealView revealView = findViewById(R.id.revealView);

        int[] startingLocation = new int[2];
        view.getLocationOnScreen(startingLocation);
        startingLocation[0] += view.getWidth() / 2;


        Point point = new Point(startingLocation[0],startingLocation[1]);
        //revealView.revealView(point.x,point.y,view.getWidth()/2);


        BgRevealView bgRevealView = findViewById(R.id.bgRevealView);
        bgRevealView.startFromLocation(startingLocation);

    }
}
