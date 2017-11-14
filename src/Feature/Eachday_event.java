package Feature;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Eachday_event {

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

	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public Map<String, int[]> count(ArrayList<ArrayList<String>> s) throws IOException, ParseException {
		Map<String, int[]> map = new LinkedHashMap<String, int[]>();
		for (int i = 1; i < s.size(); i++) {
			String key = s.get(i).get(1);
			String time1 = s.get(i).get(4);
			//String time = time1[0] + "-" + time1[1] + "-" + time1[2];
			String start = s.get(i).get(2).split("T")[0];
			int type[] = map.get(key);
			if (type == null)
				type = new int[30];
			int day = daysBetween(time1, start);
			type[day]++;
			map.put(key, type);

		}
		return map;
	}

	public static void main(String[] args) throws IOException, ParseException {
		Eachday_event ob = new Eachday_event();
		String pathname = "D:\\workplace\\DataMing_project\\src\\train\\log_course_date.csv";
		File filename = new File(pathname);
		String outFilePath = "D:\\workplace\\DataMing_project\\src\\feature_data\\EventatEachday.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
		Map<String, int[]> result = new LinkedHashMap<String, int[]>();
		;
		train = ob.readData(filename);
		result = ob.count(train);
		for (Entry<String, int[]> entry : result.entrySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(entry.getKey());
			for (int i = 0; i < 30; i++) {
				sb.append(",").append(entry.getValue()[i]);
			}
			bw.write(sb.append("\n").toString());
		}
		bw.close();
		System.out.println("done");
	}

}
