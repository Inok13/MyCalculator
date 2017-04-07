package ru.startandroid.mycalculator;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Home on 27.03.2017.
 */

public class AsyncCounter extends AsyncTask<String, Void, Double> {

    @Override
    protected Double doInBackground(String... field) {
        List<String> fields = transform(field);
        double result = 0;
        try{
            result = pD(count(fields));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    Double pD(String s){
        try {
            return Double.parseDouble(s);
        }catch (NumberFormatException e){
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    private String count(List<String> list){

        chunker(list);

        while (list.size() > 1){

            try {
                for (int i = 0; i < 2; i++) {
                    Iterator<String> itS = list.iterator();
                    if (i == 0) {
                        while (itS.hasNext()) {

                            String next = itS.next();

                            if (next.equals("*") || next.equals("/")) {
                                if (next.equals("*")) {
                                    int it = list.indexOf(next);
                                    int x = it - 1;
                                    int y = it + 1;
                                    double res = multiply(pD(list.get(x)), pD(list.get(y)));
                                    list.set(x, String.valueOf(res));
                                    itS.remove();
                                    itS.next();
                                    itS.remove();
                                } else {
                                    int it = list.indexOf(next);
                                    int x = it - 1;
                                    int y = it + 1;
                                    double res = divide(pD(list.get(x)), pD(list.get(y)));
                                    list.set(x, String.valueOf(res));
                                    itS.remove();
                                    itS.next();
                                    itS.remove();
                                }

                            }
                        }
                    } else {
                        while (itS.hasNext()) {
                            String next2 = itS.next();
                            if (next2.equals("+")) {
                                int it = list.indexOf(next2);
                                int x = it - 1;
                                int y = it + 1;
                                double res = plus(pD(list.get(x)), pD(list.get(y)));
                                list.set(x, String.valueOf(res));
                                itS.remove();
                                itS.next();
                                itS.remove();

                            } else if (next2.equals("-")) {
                                int it = list.indexOf(next2);
                                int x = it - 1;
                                int y = it + 1;
                                double res = minus(pD(list.get(x)), pD(list.get(y)));
                                list.set(x, String.valueOf(res));
                                itS.remove();
                                itS.next();
                                itS.remove();
                            }
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return list.get(0);
    }
    private List<String> chunker(List<String> fullList){
        boolean isBracketsThere = true;
        while (isBracketsThere) {
            int start = 0;
            int end = 0;
            Iterator<String> it = fullList.iterator();
            boolean watching = false;
            boolean started = false;
            String withoutR = new String();
            String withoutL;
            int index = 0;
            try {
                while (it.hasNext()) {
                    String next = it.next();

                    if(next.equals(""))
                        it.remove();

                    if(fullList.size() == 1)
                        return fullList;

                    if(fullList.get(index).equals("(") && !fullList.get(index + 1).endsWith(")")
                            && !fullList.get(index + 1).startsWith("(") && fullList.get(index + 2).equals(")")){
                        it.remove();
                        next = it.next();
                        it.next();
                        it.remove();
                    }
                    if(next.equals("(") && !fullList.get(index + 1).startsWith("(")){
                        it.remove();
                        next = it.next();
                        String newNext = "(".concat(next);
                        fullList.set(index, newNext);
                        next = newNext;
                    }else if(next.equals(")") && !fullList.get(index - 1).endsWith(")")){
                        it.remove();
                        next = fullList.get(index - 1);
                        index -= 1;
                        String newNext = next.concat(")");
                        fullList.set(index, newNext);
                        next = newNext;
                    }
                    if (next.startsWith("(")) {
                        withoutR = next.replace("(", "");
                        start = index;
                        watching = true;
                    }
                    if (watching && next.endsWith(")")) {
                        fullList.set(start, withoutR);
                        withoutL = next.replace(")", "");
                        end = index;
                        fullList.set(end, withoutL);
                        started = true;
                    }
                    if (started) {
                        List<String> totalResult = fullList.subList(start, end + 1);
                        String res = count(totalResult);
                        fullList.set(start, res);
                        index = start;
                    }
                    index ++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!started && !watching) {
                isBracketsThere = false;
            }
        }
        return fullList;
    }
    private List<String> transform(String[] stringArray){
        List<String> result = new ArrayList<>();

        for(int i = 0; i < stringArray.length; i++){
            result.add(stringArray[i]);
        }
        return result;
    }
    private double plus(double x, double y){
        return x + y;
    }
    private double minus(double x, double y){
        return x - y;
    }
    private double multiply(double x, double y){
        return x * y;
    }
    private double divide(double x, double y){
        return x / y;
    }
}
