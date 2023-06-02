package io;
import java.util.*;
import java.io.*;


// Kazuharu Misawa Apr.2 2001
// Kazuharu Misawa Apr.2 2001
// checked on May 7
// Modified for <type> Jan 26 2012


public class fastaFormat{
    public static String[][] input(String filename) throws FileNotFoundException,IOException {
    	String[] data = fileload.loadLine0(filename);
        return change(data);
    }

    public static String[][] input() throws FileNotFoundException,IOException {
        String[] data = fileload.loadLine0();
        return change(data);
    }

    public static String[][] change(String[] data) throws FileNotFoundException,IOException {
        Vector<String> dataList = new Vector<String>();
        Vector<String> titleList = new Vector<String>();
        String title = "tmp";
        StringBuffer SB = new StringBuffer();
        for(int i=0;i<data.length;i++){
        	if(data[i]==null) continue;
        	if(data[i].length()==0) continue;
            if(data[i].charAt(0)=='>'){
                if(i>0) dataList.add(SB.toString()); // old data
                title = data[i].substring(1);
                titleList.add( title );
                SB = new StringBuffer();
            }else{
            	SB.append(data[i]);
            }
        }
        dataList.add(SB.toString()); // old data
        int count = titleList.size();
        String[][] result = new String[2][count];
        for(int i=0;i<count;i++){
            result[0][i] = (String) titleList.elementAt(i);
            result[1][i] = (String) dataList.elementAt(i);
        }
//        result[1] = gapSkip(result[1]); // skipping gap 
        return result;
    }
    public static String[][] translate(String[] data) throws FileNotFoundException,IOException {
    	return change(data);
    }
    
    public static String[][] translateOld(String[] data) throws FileNotFoundException,IOException {
        HashMap<String,String> dataList = new HashMap<String,String>();
        HashMap<Integer,String> titleList = new HashMap<Integer,String>();
        int count=0;
        String title = "tmp";
        for(int i=0;i<data.length;i++){
            if(data[i].charAt(0)=='>'){
                title = data[i].substring(1);
//                titleList.put( (new Integer(count)), title + (new Integer(count)).toString() );
                titleList.put( (new Integer(count)), title );
                count++;
            }else{
                if(dataList.containsKey(title)){
                    String former = (String) dataList.get(title);
                    dataList.put(title, former + data[i] );
                }else{
                    dataList.put(title, data[i]);
                }
            }
        }
        String[][] result = new String[2][count];
        for(int i=0;i<count;i++){
            title = (String) titleList.get( (new Integer(i)) );
            result[0][i] = title;
            result[1][i] = (String) dataList.get(title);
        }
//        result[1] = gapSkip(result[1]); // skipping gap 
        return result;
    }

    public static void output(String[][] org){
        String[] data = convert(org);
        for(int i=0;i<data.length;i++){
            System.out.println( data[i] );
        }
    }

    public static void output(HashMap table){
        int size = table.size();
        String[][] result = new String[2][size];
        Iterator itable = (table.keySet()).iterator();
        int count=0;
        while(itable.hasNext()){ 
            result[0][count] = (String) itable.next();
            result[1][count] = (String) table.get(result[0][count]);
            count++;
        }
        output(result);
    }

    static String[] convert(String[][] org){    // 
        Vector<String> v = new Vector<String>();
        for(int i=0;i<org[0].length;i++){
        	String tmp0="";
            if(org[0][i].charAt(0)!='>') tmp0 =">";
            v.addElement(tmp0 + org[0][i]);
            String[] tmp = slice60(org[1][i]);
            for(int j=0;j<tmp.length;j++){
                v.addElement( tmp[j] );
            }
        }
        String[] result = new String[v.size()];
        v.copyInto(result);
        return result;
    }

    static String[] slice60(String seq){  // to limit length by 60;
        String tmp = seq;
        Vector<String> v = new Vector<String>();
        while( tmp.length() >= 60 ){
            v.addElement(tmp.substring(0,60));
            tmp = tmp.substring(60);
        }
        if( tmp.length() > 0 ) v.addElement( tmp );
        String[] result = new String[v.size()];
        v.copyInto(result);
        return result;
    }

    // skip all-gap sites 
    public static String[] gapSkip(String[] data){
        int size = data.length;
        if(size<=1) return data;
        StringBuffer[] newdata = new StringBuffer[size];
        for(int i=0;i<size;i++){ 
            newdata[i] = new StringBuffer(); 
        }
        int len = data[0].length();
        for(int j=0;j<len;j++){
            boolean no_gap = false;
            for(int i=0;i<size;i++){
                if( data[i].charAt(j)!='-' ) no_gap=true;
            }
            for(int i=0;i<size;i++){
                if(no_gap) newdata[i].append(data[i].charAt(j));
            }
        }
        String[] result = new String[size];
        for(int i=0;i<size;i++){ 
            result[i] = newdata[i].toString(); 
        }
        return result;
    }
    
    // skip sites with gaps
    // complete deletion
    public static String[] gapdel(String[] data){
        int size = data.length;
        StringBuffer[] newdata = new StringBuffer[size];
        for(int i=0;i<size;i++){ 
            newdata[i] = new StringBuffer(); 
        }
        int len = data[0].length();
        for(int j=0;j<len;j++){
            boolean no_gap = true;
            for(int i=0;i<size;i++){
                if( data[i].charAt(j)=='-' ) no_gap=false;
            }
            for(int i=0;i<size;i++){
                if(no_gap) newdata[i].append(data[i].charAt(j));
            }
        }
        String[] result = new String[size];
        for(int i=0;i<size;i++){ 
            result[i] = newdata[i].toString(); 
        }
        return result;
    }
}
