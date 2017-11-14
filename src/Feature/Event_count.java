package Feature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import Object_class.*;

public class Event_count{
	public Map<String,int[]>  count (ArrayList<ArrayList<String>> s) throws IOException {
		Map<String,int[]> map= new LinkedHashMap<String,int[]>();
		for(int i=1;i<s.size();i++) {		
				String key=s.get(i).get(0);
				int type[]=map.get(key);
				if(type == null)
					type=new int[7];
					
				type[Integer.parseInt(s.get(i).get(3))]++;						
				map.put(key, type);
		}
		return map;
	}
		
		

public static void main(String [] args) throws IOException {
	Event_count tb = new Event_count();
	Map<String,int[]> result=new LinkedHashMap<String,int[]>();	
	tb.count(Ob_train.train);
	String outFilePath="D:\\workplace\\DataMing_project\\src\\feature_data\\eventCount.csv";
	BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
	for(Entry<String, int[]> entry : result.entrySet()) {
		StringBuilder sb= new StringBuilder();
		sb.append(entry.getKey()).append(",").append(entry.getValue()[1]).append(",").append(entry.getValue()[2]).append(",").append(entry.getValue()[3]).append(",").append(entry.getValue()[4]).append(",").append(entry.getValue()[5]).append(",").append(entry.getValue()[6]).append(",").append(entry.getValue()[7]);
		bw.write(sb.append("\n").toString());	
	}
	bw.close();
	System.out.println("done");
}

}