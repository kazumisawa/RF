package test;

public class RandomSequence {
    public static double humanGCcontent = 0.4; // Misawa (2011) BMC Genomics
    
    public static void main(String[] argv){
    	System.out.print(humanRandomSequence(100));
    }
    
    public static String humanRandomSequence(int len){
    	return(randomSequence(len, humanGCcontent));
    }
    
    public static String randomSequence(int len, double GCcontent){
    	StringBuffer result = new StringBuffer();
    	double[] content = new double[4];// content of TCAG
    	content[0] = (1-GCcontent)/2; // T content
    	content[1] = GCcontent/2;     // C content
    	content[2] = (1-GCcontent)/2; // A content
    	content[3] = GCcontent/2;     // G content
    	char[] nuc = new char[4]; //nucleotide
    	nuc[0] = 'T';
    	nuc[1] = 'C';
    	nuc[2] = 'A';
    	nuc[3] = 'G';
    	for(int i=0;i<len;i++){
    		result.append(nuc[disrand.value(content)]);
    	}
    	return result.toString();
    }
}
