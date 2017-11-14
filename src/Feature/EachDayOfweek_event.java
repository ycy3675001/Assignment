package Feature;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EachDayOfweek_event {

	public static ArrayList<ArrayList<String>> train = new ArrayList<ArrayList<String>>();
	int enrollment_id;

	public ArrayList<ArrayList<String>> readData(File filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String str;
		while ((str = reader.readLine()) != null) {
			String[] s1 = str.split(",");
			ArrayList<String> s = new ArrayList<String>();
			for (int i = 0; i < s1.length; i++)
				s.add(s1[i]);
			train.add(s);
		}

		reader.close();
		return train;
	}

	public static int dayOfweek(String log_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(log_date));
		int week = cal.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	public Map<String, int[]> count(ArrayList<ArrayList<String>> s) throws IOException, ParseException {
		Map<String, int[]> map = new LinkedHashMap<String, int[]>();
		for (int i = 1; i < s.size(); i++) {
			String key = s.get(i).get(0);
			String time = s.get(i).get(1).split("T")[0];
			int type[] = map.get(key);
			if (type == null)
				type = new int[8];
			int day = dayOfweek(time);
			type[day]++;
			map.put(key, type);
		}
		return map;
	}

	public static void main(String[] args) throws IOException, ParseException {
		EachDayOfweek_event ob = new EachDayOfweek_event();
		String pathname = "D:\\workplace\\DataMing_project\\src\\train\\log_train.csv";
		File filename = new File(pathname);
		String outFilePath = "D:\\workplace\\DataMing_project\\src\\feature_data\\weekweek.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
		Map<String, int[]> result = new LinkedHashMap<String, int[]>();
		train = ob.readData(filename);
		result = ob.count(train);
		for (Entry<String, int[]> entry : result.entrySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(entry.getKey());
			for (int i = 1; i < 8; i++) {
				sb.append(",").append(entry.getValue()[i]);
			}
			bw.write(sb.append("\n").toString());
		}
		bw.close();
		System.out.println("done");
	}

}
