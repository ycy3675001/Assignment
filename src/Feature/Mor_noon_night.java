package Feature;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.ParseException;

public class Mor_noon_night {


	public static ArrayList<ArrayList<String>> train =new ArrayList<ArrayList<String>>();
	
	public static int getHour(String time) {
		String detail=time.split("T")[1];
		int hour=Integer.parseInt(detail.split(":")[0]);
		return hour;
			
	}

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
	
	public Map<String,int[]>  count (ArrayList<ArrayList<String>> s) throws IOException, ParseException {
		Map<String,int[]> map= new LinkedHashMap<String,int[]>();
		for(int i=1;i<s.size();i++) {		
				String key=s.get(i).get(0);
				String time=s.get(i).get(1);				
				int type[]=map.get(key);
				if(type == null)
					type=new int[3];	
				int hour=getHour(time);	
				//0,1,2 correspond morning ,noon,night
				if(hour>=6 && hour<12)
					type[0]++;
				if(hour>=12 && hour<18)
					type[1]++;
				if(hour < 6 || hour > 18)
					type[2]++;
				map.put(key, type);
		}
		return map;
	}
	
	
	public static void main(String [] args) throws IOException, ParseException {
		Mor_noon_night ob=new Mor_noon_night();
		String pathname="D:\\workplace\\DataMing_project\\src\\train\\log_train.csv";
		File filename=new File(pathname);
		String outFilePath="D:\\workplace\\DataMing_project\\src\\feature_data\\mor_noon_night.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
		Map<String,int[]> result=new LinkedHashMap<String,int[]>();	
		train=ob.readData(filename);
		result=ob.count(train);	
		for(Entry<String, int[]> entry : result.entrySet()) {	
			StringBuilder sb= new StringBuilder();
			sb.append(entry.getKey());
			for(int i=0;i<3;i++) {
			sb.append(",").append(entry.getValue()[i]);
			}
			bw.write(sb.append("\n").toString());		
		}
		bw.close();
		System.out.println("done");
	}	
		
}
