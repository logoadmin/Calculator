package logo.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }

    public static String infix="0" ;
    public int top=-1;
    public int top_another=-1;
    public int maxs=100;
    String[] Stack=new String [maxs] ;
    String[] Stack_another=new String[maxs];
    public String opd2;
    public String[] opd3;
    public double[] opd4;
    public double[] used;
    public double e=0;
    private static double cal(char op, double p1, double p2) {
        switch(op) {
            case '+': return p1 + p2;
            case '-': return p1 - p2;
            case '*': return p1 * p2;
            case '/': return p1 / p2;
            default:  throw new ArithmeticException(op + " not defined");
        }
    }

    public static double eval(String expr) {
        LinkedList<Double> stack = new LinkedList<>();
        for(char c : Infix.toPostfix(expr).toCharArray()) {
            if("+-*/".indexOf(c) != -1) {
                double p2 = stack.removeLast();
                double p1 = stack.removeLast();
                stack.add(cal(c, p1, p2));
            } else { stack.add(Double.parseDouble(String.valueOf(c))); }
        }
        return stack.getLast();
    }


    /*
    public double scan(String[] opd3,String opd1,double[] opd4,String[] opd2){
        if(opd1.length()>0) {
            if (opd1.indexOf("=") != -1) {
                opd2=opd1.split("[^-/=\\*\\+]");
                opd3=opd1.split("\\D");
                opd4=functionparse(opd3);
                e = count(opd2,opd4,used);
                e = count2(opd2,opd4);
            }
        }
        else{
            e=0;
            EditText tx=(EditText) findViewById(R.id.show);
            tx.setText(""+e);
        }
        return e;
    }
    double[] functionparse(String[] opd3){//將陣列轉換數字型別 ok
        double[] opd4=new double[opd3.length];
        for(int i=0;i<opd3.length;i++){
            opd4[i]=Double.parseDouble(opd3[i]);
        }
        return opd4;
    }
    public double count(String[] opd2,double[] opd4,double[] used){
        used=new double[opd4.length];
        int x=0;
        for(int i=0;i<opd4.length;i++){
            if(opd2[i+2].equals("*")){opd4[i]=opd4[i]*opd4[i+1];opd4[i+1]=opd4[i];}
            if(opd2[i+2].equals("/")){opd4[i]=opd4[i]/opd4[i+1];opd4[i+1]=opd4[i];}
            if(opd2[i+2].equals("%")){opd4[i]=opd4[i]%opd4[i+1];opd4[i+1]=opd4[i];}

        }
        e=opd4[opd4.length-1];
        return e;
    }
    public double count2(String[] opd2,double[] opd4) {



        for (int i = 0; i < opd4.length; i++) {
            if ((opd2[i + 2].equals("-") || opd2[i + 2].equals("+")) && (opd2[i + 3].equals("*") || opd2[i + 3].equals("/") || opd2[i + 3].equals("%"))) {
                if (opd2[i + 2].equals("+") ) {
                    opd4[i + 2] = opd4[i] + opd4[i + 2];
                }
                if (opd2[i + 2].equals("-") ) {
                    opd4[i + 2] = opd4[i] - opd4[i + 2];
                }
            }
//若是+號並且下一個符號是+或-或=進行+法運算
            if (opd2[i + 2].equals("+") && (opd2[i + 3].equals("+") || opd2[i + 3].equals("-") || opd2[i + 3].equals("="))) {
                opd4[i + 1] = opd4[i] + opd4[i + 1];
            }
//若是-號並且下一個符號是+或-或=進行+法運算
            if (opd2[i + 2].equals("-") && (opd2[i + 3].equals("+") || opd2[i + 3].equals("-") || opd2[i + 3].equals("="))) {
                opd4[i + 1] = opd4[i] - opd4[i + 1];
            }
//若是+或-號並且下一個符號是*或/或%進行向上覆蓋

        }
        e = opd4[used.length - 1];
        return e;
    }
    public void display(double e){
        EditText tx1=(EditText) findViewById(R.id.show);
        tx1.setText(""+ this.e);
    }

    */

    protected void KeyInput(View V) {
        Button bt=(Button) V;
        //opd1=opd1*10+(new Integer().toString())).intValue();
        infix=infix+ (String) bt.getText();
        EditText tx=(EditText) findViewById(R.id.show);
        tx.setText(""+infix);

       if (infix.indexOf("=") != -1) {
           String str=infix.substring(0,infix.length()-1);
           e=eval(str);
            EditText tx1=(EditText) findViewById(R.id.show);
            tx1.setText(""+e);
        }

        //e=scan(opd3,opd1,opd4,opd2);
        /*if(e!=0) {
            EditText tx1=(EditText) findViewById(R.id.show);
            tx1.setText(""+infix);
        }*/
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




