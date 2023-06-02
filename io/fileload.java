package io;

import java.io.*;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class fileload extends load{
    public fileload(String filename)  throws FileNotFoundException,IOException {
        data = loadLine0( filename );
    }

    public fileload() throws FileNotFoundException,IOException {
        data = loadLine0( );
    }

    public static String[] loadLine0(String filename) throws FileNotFoundException,IOException {
        return LoadFromReader(new FileReader(filename));
    }

    public static String[] loadLine0() throws FileNotFoundException,IOException {
        return LoadFromReader(new InputStreamReader(System.in) );
    }

    public static String[] LoadFromReader(Reader in0) throws FileNotFoundException,IOException {
        LineNumberReader d0 = new LineNumberReader(in0);
        LinkedList<String> v1 = new LinkedList<String>();
        try{
            while(true){
                String str = d0.readLine();
                if(str == null) break; 
                v1.add(str);
            }
        }catch(EOFException e){   // end of file
            in0.close();
        }catch(NullPointerException e){}
        d0.close();
        in0.close();
        String[] result = new String[v1.size()];
        for(int i=0;i<v1.size();i++){
            result[i] = (String) v1.get(i);
        }
        return result;
    }
}