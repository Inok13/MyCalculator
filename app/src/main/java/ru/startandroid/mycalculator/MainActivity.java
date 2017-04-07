package ru.startandroid.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView workField;
    private StringBuilder field = new StringBuilder();
    private String result = new String();
    private int bracketCounter = 0;
    private double total = 0;
    private final float textSize = 55;
    private final int numbOfChars = 9;
    private AsyncCounter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workField = (TextView) findViewById(R.id.tvWorkSpace);
        workField.setTextSize(textSize);
    }

    public void onClick(View v){
        try {
            switch (v.getId()) {
                case R.id.btn0:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("0");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn1:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("1");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn2:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("2");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn3:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("3");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn4:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("4");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn5:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("5");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn6:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("6");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn7:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("7");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn8:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("8");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btn9:
                    equCheck(field);
                    if(zeroCheck(field))
                        break;
                    if (field.length() != 0 && field.charAt(field.length() - 1) == ')')
                        break;
                    if(digitsAmountCheck(field))
                        break;
                    field.append("9");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnPlus:
                    if (field.length() == 0)
                        break;

                    if (field.toString().contains(" = "))
                        field = new StringBuilder(result);

                    if (checkerForSings(field))
                        break;

                    field.append(" + ");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnMinus:
                    if (field.length() == 0)
                        break;
                    if (field.toString().contains(" = "))
                        field = new StringBuilder(result);

                    if (checkerForSings(field))
                        break;

                    field.append(" - ");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnMult:
                    if (field.length() == 0)
                        break;

                    if (field.toString().contains(" = "))
                        field = new StringBuilder(result);

                    if (checkerForSings(field))
                        break;

                    field.append(" * ");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnDiv:
                    if (field.length() == 0)
                        break;
                    if (field.toString().contains(" = "))
                        field = new StringBuilder(result);

                    if (checkerForSings(field))
                        break;

                    field.append(" / ");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnPoint:
                    if (field.length() == 0)
                        break;

                    if (field.toString().contains(" = "))
                        break;
                    if (checkerForSings(field))
                        break;
                    if(pointCheck(field))
                        break;
                    field.append(".");
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnBack:
                    int itLoop;

                    if (field.toString().contains(" = "))
                        break;

                    if (field.length() == 0)
                        break;
                    if (field.charAt(field.length() - 1) == ' ') {

                        if (field.charAt(field.length() - 2) == '(') {
                            itLoop = 2;
                            bracketCounter --;
                        }
                        else
                            itLoop = 3;
                        for (int i = 0; i < itLoop; i++) {
                            field.deleteCharAt(field.length() - 1);
                        }

                    } else if (field.charAt(field.length() - 1) == ')') {
                        if (field.charAt(field.length() - 2) == ' ')
                            itLoop = 2;
                        else
                            itLoop = 1;
                        for (int i = 0; i < itLoop; i++) {
                            field.deleteCharAt(field.length() - 1);
                        }
                        bracketCounter ++;
                    } else {
                        if(field.charAt(field.length() - 1) == '(')
                            bracketCounter --;

                        field.deleteCharAt(field.length() - 1);
                    }

                    negativeDynamicParameterChecking();
                    break;
                case R.id.btnNegative:
                    if(negativeCheck(field))
                        break;
                    field.append("-");
                    positiveDynamicParameterChecking();
                    break;

                case R.id.btnFaceBracket:

                    if (field.length() == 0){
                       field.append("(");
                        bracketCounter++;
                        break;
                    }
                    if(field.toString().contains(" = "))
                        break;
                    if(field.length() != 0 && field.charAt(field.length() - 1) == '.')
                        break;
                    if(field.charAt(field.length() - 1) == '-')
                        break;

                    if (field.length() != 0 && field.charAt(field.length() - 1) != ' ' &&
                            field.charAt(field.length() - 1) != '(') {
                        field.append(" * (");
                        bracketCounter++;
                    } else if (field.length() != 0 && field.charAt(field.length() - 1) != ' ') {
                        field.append(" (");
                        bracketCounter++;
                    }else {
                        field.append("(");
                        bracketCounter++;
                    }
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnBackBracket:
                    if (field.length() == 0)
                        break;
                    if (bracketCounter == 0)
                        break;
                    if (field.charAt(field.length() - 1) == ' ')
                        break;
                    if (bracketCheck(field))
                        break;
                    if (field.charAt(field.length() - 1) == '(')
                        break;
                    if (field.charAt(field.length() - 1) == ')') {
                        bracketCounter--;
                        field.append(" )");
                    } else {
                        bracketCounter--;
                        field.append(")");
                    }
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnEqu:
                    if (field.length() == 0)
                        break;
                    if (bracketCounter > 0 || bracketCounter < 0) {
                        break;
                    }
                    if(field.toString().contains("="))
                        break;

                    counter = new AsyncCounter();
                    total = counter.execute(field.toString().split(" ")).get();
                    int roundTotal = (int) total;
                    if (total - roundTotal == 0) {
                        result = String.valueOf(roundTotal);
                    } else
                        result = roundString(String.valueOf(total));

                    field.append(" = " + result);
                    positiveDynamicParameterChecking();
                    break;
                case R.id.btnC:
                    field.delete(0, field.length());
                    result = "";
                    bracketCounter = 0;
                    negativeDynamicParameterChecking();
                    break;
            }

            workField.setText(field.toString());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (Exception e){
             e.printStackTrace();
        }
    }
    int variableNumbOfChars = numbOfChars;
    float variableTextSize = textSize;

    private void positiveDynamicParameterChecking(){
        char[] fieldCharArray = field.toString().toCharArray();
        int fieldLength = fieldCharArray.length;

        if(fieldLength > variableNumbOfChars) {
            variableNumbOfChars += numbOfChars;
            if (variableTextSize > 20)
                variableTextSize /= 1.2;
            workField.setTextSize(variableTextSize);
        }

    }
    private void negativeDynamicParameterChecking(){
        char[] fieldCharArray = field.toString().toCharArray();
        int fieldLength = fieldCharArray.length;

        if(fieldLength <= numbOfChars) {
            workField.setTextSize(textSize);
            variableNumbOfChars = numbOfChars;
            variableTextSize = textSize;
        }

        if((fieldLength + numbOfChars) < variableNumbOfChars){
            if(variableNumbOfChars > numbOfChars){
                variableNumbOfChars -= numbOfChars;
            }
            variableTextSize *= 1.2;
            workField.setTextSize(variableTextSize);
        }
    }
    private void equCheck(StringBuilder field){
        if(field.toString().contains(" = "))
            field.delete(0, field.length());
    }
    private boolean bracketCheck(StringBuilder field){
        String[] array = field.toString().split(" ");

        if(array[array.length - 1].startsWith("("))
            return true;
        return false;
    }
    private boolean checkerForSings(StringBuilder field){
        char lastOne = field.charAt(field.length() - 1);

        if(lastOne == ' ' || lastOne == '(' || lastOne == '.')
            return true;

        return false;
    }
    private boolean pointCheck(StringBuilder field){
        String[] array = field.toString().split(" ");
        if(array[array.length - 1].endsWith(")"))
            return true;
        if(array[array.length - 1].contains("."))
            return true;

        return false;
    }
    private boolean zeroCheck(StringBuilder field){
        String[] array = field.toString().split(" ");
        String lastOne = array[array.length - 1];

        if(lastOne.equals("0"))
            return true;
        if(lastOne.equals("(0"))
            return true;

        return false;
    }
    private String roundString(String s){

        int roundLength = 13;
        if(s.length() < roundLength)
            return s;

        char[] array = new char[roundLength];
        for (int i = 0; i < array.length; i++){
            array[i] = s.toCharArray()[i];
        }
        String result = new String(array);

        return result;
    }
    private boolean negativeCheck(StringBuilder field){
        if (field.length() != 0 ) {
            char lastOne = field.charAt(field.length() - 1);

            if(lastOne != ' ' && lastOne != '(' )
                return true;
        }
        if(field.toString().contains("="))
            return true;

        return false;
    }
    private boolean digitsAmountCheck(StringBuilder field){
        String[] array = field.toString().split(" ");
        int standardNumber = 25;
        if(array[array.length - 1].length() > standardNumber)
            return true;
        return false;
    }
}
