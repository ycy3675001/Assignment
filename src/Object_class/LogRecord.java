package Object_class;
import java.io.*;
import java.util.*;

public class LogRecord{
	
	private  ArrayList<ArrayList<String>> train ;
	
	public ArrayList<ArrayList<String>> setTrain(File filename,ArrayList<ArrayList<String>> train) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String str;
		while((str = reader.readLine())!=null) {
			String [] s1=str.split(",");
			ArrayList<String> s = new ArrayList<String>();
			for(int i=0;i<s1.length;i++)
				s.add(s1[i]);
			train.add(s);
		}
		
		reader.close();
		return train ;
	}
	
	public  ArrayList<ArrayList<String>> getTrain() {
		return this.train;
	}

	public static void main(String [] args) throws IOException {
		LogRecord ob=new LogRecord();
		String pathname="D:\\workplace\\DataMing_project\\src\\train\\log_train.csv";
		 ob.train=new ArrayList<ArrayList<String>>();
		File filename=new File(pathname);	
		ob.setTrain(filename,ob.train);
		System.out.println("done");
	
	}



}