package io;

public class load{
    String[] data;
    public load(){ data= new String[0]; };
    public int size(){ return data.length;}
    public String loadLine(int i){
        if(i<0) return null;
        if(size()<=i) return null;
        return data[i];
    }
}

