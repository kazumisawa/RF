package test;

class disrand{  // class for discontinuous random numbers 
    public static int value(double[] list){
        double total=0;
        int len = list.length;
        int i;
        for(i=0;i<len;i++) total += list[i];
        double ran = Math.random()*total;
        i=0;
        do{
            ran -= list[i++];
        }while(ran>0);
        return (i-1);
    }
}
