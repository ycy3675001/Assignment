package Feature;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class sour_event_count {
	
	public static ArrayList<ArrayList<String>> train =new ArrayList<ArrayList<String>>();
	int enrollment_id;	
	public ArrayList<ArrayList<String>> readData(File filename) throws IOException{
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
	
	public Map<String,int[][]>  count (ArrayList<ArrayList<String>> s) throws IOException {
		Map<String,int[][]> map= new LinkedHashMap<String,int[][]>();

		for(int i=1;i<s.size();i++) {		
				String key=s.get(i).get(0);
				int type[][]=map.get(key);
				if(type == null)
					type=new int[2][7];				
				type[Integer.parseInt(s.get(i).get(2))][Integer.parseInt(s.get(i).get(3))]++;					
				map.put(key, type);
		}
		return map;
	}
	
	public static void main(String [] args) throws IOException {
		sour_event_count ob=new sour_event_count();
		String pathname="D:\\workplace\\DataMing_project\\src\\train\\log_train.csv";
		File filename=new File(pathname);
		String outFilePath="D:\\workplace\\DataMing_project\\src\\feature_data\\source_activ_count.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
		Map<String,int[][]> result=new LinkedHashMap<String,int[][]>();	
		train=ob.readData(filename);
		result=ob.count(train);	
		for(Entry<String, int[][]> entry : result.entrySet()) {	
			StringBuilder sb= new StringBuilder();
			sb.append(entry.getKey()).append(",");
			for(int i=0;i<2;i++) {				
				for(int j=0;j<7;j++) {				
					sb.append(entry.getValue()[i][j]).append(",");				
				}
			}
			bw.write(sb.append("\n").toString());
			
			
		}
		bw.close();
		System.out.println("done");
	}	
		
}
