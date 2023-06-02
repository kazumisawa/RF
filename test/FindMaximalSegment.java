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

		int count = areas.length;//高いスコアのエリアの数
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
		int num = areas.length;//高いスコアのエリアの数
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
		//高いスコアを持つ領域を返す
		int len = data.length;
		//startには高いスコアの領域のstart position
		Vector<Double> start = new Vector<Double>();
		//endには高いスコアの領域のend position
		Vector<Double> end = new Vector<Double>();
		//scoreには高いスコアの領域のスコア
		Vector<Double> score = new Vector<Double>();
		
		for(int i=0;i<len;i++){
		    if(data[i]>0){ //the start of window
		    	int goodj=i;//ハイスコアをだした終了点
		        double localMax=data[i];
		        double tmp = localMax;
		        int j=0;
		        for(j=i+1;j<len;j++){
		            tmp+=data[j];
		            if(tmp>localMax){
		                localMax=tmp; //増加すれば交換
		                goodj=j;//ハイスコアをだした終了点
		            }
		            if(tmp<0) break; //ウィンドウ内スコア計が負になれば脱出
		        }
		        if(localMax>threshold){ //かはんすうが１
			        start.add(new Double(i)); //領域の開始点
			        end.add(new Double(goodj)); //その領域の終り点
			        score.add(localMax); //その領域のスコア		        	
		        }
		        i=j;//次のウィンドウ候補へ
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
