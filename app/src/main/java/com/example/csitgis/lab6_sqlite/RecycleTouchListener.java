package com.example.csitgis.lab6_sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecycleTouchListener {
    private ClickListener clickListener;
    private GestureDetector gestureDetector;
    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public RecycleTouchListener(Context context,
                                final RecyclerView recyclerView,
                                final ClickListener clickListener){
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && clickListener != null){
                            clickListener.onLongClick(child,
                                    recyclerView.getChildAdapterPosition(child));
                        }
                    }
                });
    }
}
