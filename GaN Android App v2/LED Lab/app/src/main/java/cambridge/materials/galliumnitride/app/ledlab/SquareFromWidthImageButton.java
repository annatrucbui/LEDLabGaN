package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

//An image button that sets the height to that of the width to keep the button square

public class SquareFromWidthImageButton extends AppCompatImageButton {

    public SquareFromWidthImageButton(Context context){
        super(context);
    }

    public SquareFromWidthImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFromWidthImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
