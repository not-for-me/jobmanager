package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.my.hellogxt3.shared.HMMInput;
import com.my.hellogxt3.shared.PredictionOutput;

public class DBTest {
	public static void main(String[] args) {

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://117.16.146.121:3306/aircleaner?autoDeserialize=true";
		String user = "subair";
		String password = "subair";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rset = statement
					.executeQuery("SELECT output FROM t104_job_info where job_id=2");
			while (rset.next()) {
				byte[] regBytes = rset.getBytes(1);
				ByteArrayInputStream regArrayStream = new ByteArrayInputStream(regBytes);
				ObjectInputStream ois = new ObjectInputStream(regArrayStream);
				
				//ArrayList<HMMInput> obj = (ArrayList<HMMInput>) ois.readObject();
				ArrayList<PredictionOutput> obj = (ArrayList<PredictionOutput>) ois.readObject();
				
				/*
				InputStream is = rset.getBlob(1).getBinaryStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				ArrayList<HMMInput> obj = (ArrayList<HMMInput>) ois.readObject();
				*/
				//java.util.ArrayList<HMMInput> obj = (java.util.ArrayList) rset.getObject(1);
				for (int i=0; i<obj.size(); i++) {
					System.out.println(obj.get(i).getTimestamp() +", "+ obj.get(i).getR_val() +", " + obj.get(i).getP_val());

					/*
					double avg = obj.get(i).getAvg();
					System.out.println(obj.get(i).getTimeStamp());
					System.out.printf("%.3f\n", obj.get(i).getAvg());
					System.out.println("---" + avg);
					System.out.println(obj.get(i).getMax());
					System.out.println(obj.get(i).getMin());
					*/
					
				}
				
				//List<HMMInput> obj = (List<HMMInput>) rset.getObject(1);
				/*
				byte[] buf = rset.getBytes(2);
				if (buf != null) {
					ObjectInputStream objectIn = new ObjectInputStream(
							new ByteArrayInputStream(buf));
					List<HMMInput> obj = (List<HMMInput>) objectIn.readObject();

					// check if the list is correct ..
					System.out.println("the input size is :" + obj.size());

					for (HMMInput input : obj) {
						System.out.println(input.getTimeStamp() + ":"
								+ input.getAvg() + "," + input.getMax() + ","
								+ input.getMin());
					}
				}
				*/
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}