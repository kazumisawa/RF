package test;

public class Test {
	static double[][] matrix = {
			{1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1},
			{-1,-1,1,-1,-1},
			{-1,-1,-1,1,-1},
			{-1,-1,-1,-1,-1}
	};
	public static void main(String[] argv){
		int max = 20;
		int[][] count = new int[3][max]; //結果
		double[] GCcontent = new double[3];
		GCcontent[0]=38.25/100;
		GCcontent[1]=41.01/100;
		GCcontent[2]=48.34/100;
		for(int k=0;k<3;k++){ //繰り返し
			int len = 1000*1000*10; //10M
//			String data =RandomSequence.humanRandomSequence(len);
			String data =RandomSequence.randomSequence(len, GCcontent[k]);
			int[] enumSequence = DNA2integer(data);
			double[] score = score(Test.matrix,enumSequence,1);
			double[][] areas = FindMaximalSegment.areas(score,1);
			int num = areas.length;//高いスコアのエリアの数
			for(int j=0;j<num;j++){
				if(areas[j][2]<max){
					count[k][(int) areas[j][2]]++;
				}
			}			
		}
		int[][] cum = new int[3][max];
		for(int k=0;k<3;k++){ //繰り返し
			for(int j=max-2;j>0;j--){
				cum[k][j] = count[k][j] + cum[k][j+1]; 
			}
		}
        System.out.println("score¥t" + GCcontent[0]+ "¥t" + GCcontent[1]+ "¥t" + GCcontent[2]);
		for(int j=2;j<max;j++){
			//スコアを“越えた”数だから1を引く
	        System.out.println(">" + (j-1) + "¥t" + cum[0][j]+ "¥t" + cum[1][j]+ "¥t" + cum[2][j]);
		}
	}

	public static int[] DNA2integer(String data){
	    // DNA配列を整数の配列にする。
		int len = data.length();
		int[] result = new int[len];
		for(int i=0;i<len;i++){
			char c = data.charAt(i);
			switch(c){
			case 'T' :result[i] = 0; break;
			case 't' :result[i] = 0; break;
			case 'U' :result[i] = 0; break;
			case 'u' :result[i] = 0; break;
			case 'C' :result[i] = 1; break;
			case 'c' :result[i] = 1; break;
			case 'A' :result[i] = 2; break;
			case 'a' :result[i] = 2; break;
			case 'G' :result[i] = 3; break;
			case 'g' :result[i] = 3; break;
			default:  result[i] = 4; 
			}
		}
		return result;
	}

	public static double[] score(double[][] matrix,int[] data, int lag){
		int len = data.length;
		double[] result = new double[len];
		for(int i=0;i<(len-lag);i++){
			result[i]=matrix[data[i]][data[i+lag]];
		}
		return result;
	}

	public static double[] score(String data, int lag){
		int len = data.length();
		double[] result = new double[len];
		for(int i=0;i<(len-lag);i++){
			if( data.charAt(i)==data.charAt(i+lag) ){
				result[i]=1;
			}else{
				result[i]=-1;
			}
		}
		return result;
	}
}
