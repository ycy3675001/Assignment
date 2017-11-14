package Object_class;
import java.io.*;
import java.util.*;
public class Integrate_data{
	static String dic= "D:\\workplace\\DataMing_project\\src\\feature_data\\";
	public static void main(String [] args) throws IOException{
		ArrayList<File> fileArry=new ArrayList<File>();
		String pathname[]=new String[7];
		pathname[0]=dic+"login_number_1.csv";pathname[1]=dic + "logIn_source.csv";pathname[2]=dic + "eventCount.csv";pathname[3]=dic + "source_activ_count.csv";pathname[4]=dic + "mor_noon_night.csv";pathname[5]=dic + "Eachday.csv";pathname[6]=dic + "Eachweek.csv";
		for(int i=0;i<7;i++)
			fileArry.add(new File(pathname[i]));			
		String outFilePath="D:\\workplace\\DataMing_project\\src\\feature_data\\Allfeature.csv";
		System.out.println(fileArry.size());
		/**  write the data  **/
		BufferedReader []buf=new BufferedReader[fileArry.size()];
		for(int i=0;i<fileArry.size();i++) {
			buf[i]=new BufferedReader(new FileReader(fileArry.get(i)));
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));	
		for(int i=1;i<=120543;i++) {
			StringBuilder sb= new StringBuilder();
			for(BufferedReader br : buf) {
				String s=br.readLine();
				sb.append(s).append(",");
			}
			bw.write(sb.append("\n").toString());
		}
		for(BufferedReader br : buf)
			br.close();
		bw.close();			
		System.out.println("done");
				
	}
}
/*for(File file : fileArry) {
BufferedReader reader = new BufferedReader(new FileReader(file));
BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
String []str=reader.readLine().split(",");	
for(int i=0;i<str.length;i++)
	sb.append(str[i]).append(",");
bw.write(sb.toString());	
reader.close();
bw.close();
}
*/

