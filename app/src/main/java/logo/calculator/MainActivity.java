package logo.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.Arrays;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }

    public static String infix="0" ;
    public int maxs=100;
    String[] Stack=new String [maxs] ;
    String[] Stack_another=new String[maxs];
    double[] p=new double[10];
    public double e=0;
    private static double cal(char op, double p1, double p2) {
        switch(op) {
            case '+': return p1 + p2;
            case '-': return p1 - p2;
            case '*': return p1 * p2;
            case '/': return p1 / p2;
            case '%': return p1 % p2;
            default:  throw new ArithmeticException(op + " not defined");
        }
    }

    public static String eval(String expr,double[] opd) {
        double [] opd2=new double[opd.length+1];
        opd2[opd.length]=0;
        for(int i=0;i<opd.length;i++) {
            opd2[i] = opd[i];
        }
        LinkedList<String> stack = new LinkedList<>();
        for(char c : Infix.toPostfix(expr).toCharArray()) {
            if("+-*/%".indexOf(c) != -1) {
                double p2 = opd2[Integer.parseInt(stack.removeLast())];
                double p1 = opd2[Integer.parseInt(stack.removeLast())];
                opd2[opd.length]=cal(c, p1, p2);
                stack.add(String.valueOf(opd.length));

            } else { stack.add(String.valueOf(c));
            }
        }
        return String.valueOf(opd2[Integer.parseInt(stack.getLast())]);
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
    protected void Back(View V){
        EditText Ex=(EditText) findViewById(R.id.show);
        infix= Ex.getText().toString();
        if(infix.length()==1){
            infix = "0";
            Ex.setText("" + infix);
        }
        else {
            infix = infix.substring(0, infix.length() - 1);
            Ex.setText("" + infix);
        }
    }
    protected void Clear(View V){
        EditText Ex=(EditText) findViewById(R.id.show);
        Ex.setText("0");
        e=0;
        infix="0";
    }
    public int x=0;

    double[] functionparse(String[] opd3){//將陣列轉換數字型別 ok
        double[] opd4=new double[opd3.length];
        for(int i=0;i<opd3.length;i++){
            opd4[i]=Double.parseDouble(opd3[i]);
        }
        return opd4;
    }
    protected void KeyInput(View V) {
        Button bt=(Button) V;
        if(x==1) {
            infix = infix.substring(0, infix.length() - 1);
            if ("1234567890".indexOf((String) bt.getText()) != -1) {
                Clear(V);
            }
            x = 0;
        }


        //opd1=opd1*10+(new Integer().toString())).intValue();
        infix=infix+ (String) bt.getText();
        EditText tx=(EditText) findViewById(R.id.show);
        tx.setText(""+infix);
        if(infix.substring(0)=="0"){
            infix=infix.substring(1,infix.length());
            infix = infix.substring(0, infix.length() - 1);
        }

       if (infix.indexOf("=") != -1) {
           String str=infix.substring(1,infix.length()-1);
           String xs;
           String[] opd2;
           double[] opd4;
           String[] opd3;
           opd2=str.split("[^-/\\*\\+]");
           opd3=str.split("\\+|-|\\*|/");
           opd4=functionparse(opd3);
           String str2="";
           int n=opd2.length;
           int tt=0,ttt=0;
           while(n>0){
               if(opd2[tt].equals("")){}
               else {
                   str2 = str2 + ttt + opd2[tt];
                   ttt++;
               }
               tt++;
               n--;
           }
           str2 = str2 + ttt;

           xs=eval(str2,opd4);
            EditText tx1=(EditText) findViewById(R.id.show);
            tx1.setText(""+xs);
            x=1;
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




