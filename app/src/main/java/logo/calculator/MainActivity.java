package logo.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

    }


    protected void KeyInput(View ) {

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = (GridLayout) findViewById(R.id.keys);
        int kbHeight =(int)(keysGL.getHeight()/ keysGL.getRowCount());
        int kbWidth =(int)(keysGL.getWidth()/ keysGL.getColumnCount());
        Button btn;
        for( int i=0; i< keysGL.getChildCount();i++)
        {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
        }
    }


}




