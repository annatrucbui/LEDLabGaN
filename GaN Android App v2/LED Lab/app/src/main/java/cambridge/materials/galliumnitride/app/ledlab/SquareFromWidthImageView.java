package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

//An image view that sets the height to equal that of the width to keep the view square

public class SquareFromWidthImageView extends AppCompatImageView {

    public SquareFromWidthImageView(Context context){
        super(context);
    }

    public SquareFromWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFromWidthImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
