package cambridge.materials.galliumnitride.app.ledlab;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

//A button that sets the height to equal the width to keep it square and also sets the text font to 'aleo'

public class SquareFromWidthAleoButton extends AppCompatButton {

    public SquareFromWidthAleoButton(Context context){
        super(context);
        init();
    }

    public SquareFromWidthAleoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareFromWidthAleoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/aleo.otf");
        setTypeface(tf);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}
