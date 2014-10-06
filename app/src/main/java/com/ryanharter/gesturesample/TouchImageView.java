package com.ryanharter.gesturesample;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Special case of an ImageView that reacts to touch feedback for
 * things like scale and translation.
 */
public class TouchImageView extends ImageView
        implements ScaleGestureDetector.OnScaleGestureListener {

    /** The custom gesture detector we use to track scaling. */
    private ScaleGestureDetector mScaleDetector;

    /** The scale value of our internal image view. */
    private float mScaleValue = 1.0f;

    public TouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Set the scale type to MATRIX so that the scaling works.
        setScaleType(ScaleType.MATRIX);

        // Add a scale GestureDetector, with this as the listener.
        mScaleDetector = new ScaleGestureDetector(context, this);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        // Pass our events to the scale gesture detector first.
        boolean handled = mScaleDetector.onTouchEvent(event);

        // If the scale gesture detector didn't handle the event,
        // pass it to super.
        if (!handled) {
            handled = super.onTouchEvent(event);
        }

        return handled;
    }

    /*
     * ScaleGestureDetector callbacks
     */
    @Override public boolean onScale(ScaleGestureDetector detector) {
        // Get the modified scale value
        mScaleValue *= detector.getScaleFactor();

        // Set the image matrix scale
        Matrix m = new Matrix(getImageMatrix());
        m.setScale(mScaleValue, mScaleValue);
        setImageMatrix(m);

        return true;
    }

    @Override public boolean onScaleBegin(ScaleGestureDetector detector) {
        // Return true here to tell the ScaleGestureDetector we
        // are in a scale and want to continue tracking.
        return true;
    }

    @Override public void onScaleEnd(ScaleGestureDetector detector) {
        // We don't care about end events, but you could handle this if
        // you wanted to write finished values or interact with the user
        // when they are finished.
    }
}
