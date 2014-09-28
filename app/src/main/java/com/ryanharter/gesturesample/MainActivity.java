package com.ryanharter.gesturesample;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by rharter on 9/27/14.
 */
public class MainActivity extends Activity {

    TouchImageView mImageView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (TouchImageView) findViewById(R.id.image_view);
        mImageView.setImageResource(R.drawable.ic_launcher);
    }
}
