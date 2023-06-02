package test;
import java.util.*;

public class FindMaximalSegment {
	public static double threshold = 10;

	public static void main(String[] argv){
		int len=1000*1000;
		double[] data = new double[len];
		for(int i=0;i<len;i++){
		    if(Math.random()<0.3){
		        data[i]=1;
            }else{
	            data[i]=-1;
	        }
		}
		double[][] areas = areas(data, FindMaximalSegment.threshold);
		/*
		double[] window = new double[len];
		for(int i=0;i<len;i++){
	        window[i]=0;
	    }

		int count = areas.length;//�����X�R�A�̃G���A�̐�
	    for(int j=0;j<count;j++){
	        for(int i=(int) areas[j][0];i<=areas[j][1];i++){
	            window[i]=areas[j][2];
	        }
	    }
	    for(int i=0;i<len;i++){
	        System.out.println(i + "\t" + data[i] + "\t" + window[i]);
	    }
	    */
		int max = 12;
		double[] count = new double[max];
		int num = areas.length;//�����X�R�A�̃G���A�̐�
		for(int j=0;j<num;j++){
			if(areas[j][2]<max){
				count[(int) areas[j][2]]++;
			}
		}
		for(int j=0;j<max;j++){
	        System.out.println(j + "\t" + count[j]);
		}

	}
	
	public static double[][] areas(double[] data, double threshold){
		//�����X�R�A�����̈��Ԃ�
		int len = data.length;
		//start�ɂ͍����X�R�A�̗̈��start position
		Vector<Double> start = new Vector<Double>();
		//end�ɂ͍����X�R�A�̗̈��end position
		Vector<Double> end = new Vector<Double>();
		//score�ɂ͍����X�R�A�̗̈�̃X�R�A
		Vector<Double> score = new Vector<Double>();
		
		for(int i=0;i<len;i++){
		    if(data[i]>0){ //the start of window
		    	int goodj=i;//�n�C�X�R�A���������I���_
		        double localMax=data[i];
		        double tmp = localMax;
		        int j=0;
		        for(j=i+1;j<len;j++){
		            tmp+=data[j];
		            if(tmp>localMax){
		                localMax=tmp; //��������Ό���
		                goodj=j;//�n�C�X�R�A���������I���_
		            }
		            if(tmp<0) break; //�E�B���h�E���X�R�A�v�����ɂȂ�ΒE�o
		        }
		        if(localMax>threshold){ //���͂񂷂����P
			        start.add(new Double(i)); //�̈�̊J�n�_
			        end.add(new Double(goodj)); //���̗̈�̏I��_
			        score.add(localMax); //���̗̈�̃X�R�A		        	
		        }
		        i=j;//���̃E�B���h�E����
		    }
		}
		int size = start.size();
		double[][] result = new double[size][3];
		for(int i=0;i<size;i++){
			result[i][0] = (start.elementAt(i)).doubleValue();
			result[i][1] = (end.  elementAt(i)).doubleValue();
			result[i][2] = (score.elementAt(i)).doubleValue();
		}
		return result;
	}

}
