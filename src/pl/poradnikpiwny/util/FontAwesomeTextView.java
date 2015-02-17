package pl.poradnikpiwny.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontAwesomeTextView extends TextView 
{
    public FontAwesomeTextView(Context context) 
    {
    	super(context);
    }
    
    public FontAwesomeTextView(Context context, AttributeSet attrs) 
    {
    	this(context, attrs, 0);
    }
    
    public FontAwesomeTextView(Context context, AttributeSet attrs, int defStyle) 
    {
    	super(context, attrs, defStyle);
    	if (!isInEditMode()) {
    		setTypeface(Typeface.createFromAsset(getContext().getAssets(), "FontAwesome.ttf"));
    	}
    }
}