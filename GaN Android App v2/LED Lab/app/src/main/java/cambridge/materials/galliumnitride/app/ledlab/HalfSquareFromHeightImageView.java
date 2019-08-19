package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

//An image view that sets the height to equal that of the width to keep the view square

public class HalfSquareFromHeightImageView extends AppCompatImageView {

    public HalfSquareFromHeightImageView(Context context){
        super(context);
    }

    public HalfSquareFromHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HalfSquareFromHeightImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        setMeasuredDimension(height/2, height);
    }
}
