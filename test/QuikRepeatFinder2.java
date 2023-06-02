package test;

import java.io.*;
//�t�@�C�������ɌĂ�ōs����������H��Ȃ��悤�ɂ���


public class QuikRepeatFinder2 {
	static double[][] matrix = {
			{1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1},
			{-1,-1,1,-1,-1},
			{-1,-1,-1,1,-1},
			{-1,-1,-1,-1,-1}
	};
//	static double threshold = 8;
	
	public static void main(String[] argv) throws FileNotFoundException,IOException {
		// �t�@�C�����o��
		Reader in0 = new FileReader(argv[0]);			
		int threshold = (new Integer(argv[1])).intValue();			
		BufferedReader d0 = new BufferedReader(in0);
        String title = "";
        StringBuffer data = new StringBuffer();
        int position = 0; //�擪����̗ݐς̈ʒu
        try{
            while(true){
                String str = d0.readLine();
            	if(str==null) throw new EOFException();  // end of file;
            	if(str.length()==0) continue;
                if(str.charAt(0)=='>'){ // >�������̂�flush
                	if( (title.length()>0)&&(data.length()>0)){
                		QuikRepeatFinder2.findRepeat(title,data.toString(),position, threshold);
                		position += data.length(); // ���̐擪�̈ʒu
                	}
                    title = str.substring(1);
                    data = new StringBuffer();
                }else{
                	data.append(str);
                	if(data.length()>1000*1000*10){// �傫���Ȃ�߂��Ȃ��悤10mega�Ő���
                		QuikRepeatFinder2.findRepeat(title,data.toString(),position, threshold);
                		position += data.length(); // ���̐擪�̈ʒu
                		data = new StringBuffer();
                	}
                }
            }
        }catch(EOFException e){   // end of file
        	//���܂��Ă����f�[�^������
        	QuikRepeatFinder2.findRepeat(title,data.toString(),position, threshold);
            in0.close();
        }catch(NullPointerException e){}
        d0.close();
        in0.close();        
	}
	
	public static void findRepeat(String title, String data, int start, int threshold){// �����z��̃X�^�[�g�ꏊ
		int[] enumSequence = DNA2integer(data);
		for(int unitLen=1;unitLen<=100;unitLen++){ // repeat unit�̒��������낢�뎎��
			double[] score = score(QuikRepeatFinder2.matrix,enumSequence,unitLen);				
			double[][] areas = FindMaximalSegment.areas(score, threshold);
			int num = areas.length;//�����X�R�A�̃G���A�̐�
			for(int j=0;j<num;j++){
				//areas[j][2]���X�R�A�̒l
				if(areas[j][2]>FindMaximalSegment.threshold){ //�X�R�A��threshold�ȏ�
					//�A�E�g�v�b�g
			        System.out.print(">len:" +unitLen + "-" + (int) areas[j][0] + "-" + (int) areas[j][1] + "\t");
					// repeat�̒��S�̈ʒu
//			        System.out.print(">" + (int)( ( areas[j][0] + areas[j][1] )/2 + start) + "\t");
			        System.out.println(title);
//*
			        for(int k=(int) areas[j][0];k<areas[j][1];k+=unitLen){
			        	System.out.println(data.substring(k, k+unitLen));
			        }
/**/
					//high score region�͏������Ă���
					for(int i=(int) areas[j][0];i<=areas[j][1];i++){
						enumSequence[i] = 4 ; // masked
					}
				}
			}		
		}
	}
	

	public static int[] DNA2integer(String data){
	    // DNA�z��𐮐��̔z��ɂ���B
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
