package Feature;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import Object_class.*;

public class Source_count{
	public Map<String,int[]>  count (ArrayList<ArrayList<String>> s) throws IOException {
		Map<String,int[]> map= new LinkedHashMap<String,int[]>();
		String ser="0";
		for(int i=1;i<s.size();i++) {		
				String key=s.get(i).get(0);
				int type[]=map.get(key);
				if(type == null)
					type=new int[2];		
				if((s.get(i).get(2))== ser)
						type[0]++;
				else
						type[1]++;
					
				
				map.put(key, type);
		}
		return map;
		
	}
		
		

public static void main(String [] args) throws IOException {
	Source_count tb=new Source_count();
	String pathname="D:\\workplace\\DataMing_project\\src\\train\\log_train.csv";
	String outFilePath="D:\\workplace\\DataMing_project\\src\\feature_data\\sourceCount.csv";
	BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
	Map<String,int[]> result=new LinkedHashMap<String,int[]>();	
	result=tb.count(Ob_train.train);
	
	for(Entry<String, int[]> entry : result.entrySet()) {
		StringBuilder sb= new StringBuilder();
		sb.append(entry.getKey()).append(",").append(entry.getValue()[0]).append(",").append(entry.getValue()[1]);
		bw.write(sb.append("\n").toString());	
	}
	bw.close();
	System.out.println("done");
	
}

}