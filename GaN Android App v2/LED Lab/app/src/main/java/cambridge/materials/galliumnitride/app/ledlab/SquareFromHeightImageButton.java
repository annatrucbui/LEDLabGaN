package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

//Image button that always sets width to that of the height to keep the button square

public class SquareFromHeightImageButton  extends AppCompatImageButton {

    public SquareFromHeightImageButton(Context context){
        super(context);
    }

    public SquareFromHeightImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFromHeightImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        setMeasuredDimension(height, height);
    }
}
