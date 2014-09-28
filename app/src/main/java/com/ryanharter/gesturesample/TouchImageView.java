package com.ryanharter.gesturesample;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Special case of an ImageView that reacts to touch feedback for
 * things like scale and translation.
 */
public class TouchImageView extends FrameLayout
        implements ScaleGestureDetector.OnScaleGestureListener {

    /** Our internal image view, to leverage other's code. */
    private ImageView mImageView;

    /** The custom gesture detector we use to track scaling. */
    private ScaleGestureDetector mScaleDetector;

    /** The scale value of our internal image view. */
    private float mScaleValue = 1.0f;

    public TouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create an ImageView with a matrix scale type, then add it
        // as a child view.
        mImageView = new ImageView(context, attrs);
        mImageView.setScaleType(ImageView.ScaleType.MATRIX);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mImageView);

        // Add a scale GestureDetector, with this as the listener.
        mScaleDetector = new ScaleGestureDetector(context, this);
    }

    /*
     * Simple convenient setter to set the image resource.
     */
    public void setImageResource(int resId) {
        mImageView.setImageResource(resId);
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
        Matrix m = new Matrix(mImageView.getImageMatrix());
        m.setScale(mScaleValue, mScaleValue);
        mImageView.setImageMatrix(m);

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
