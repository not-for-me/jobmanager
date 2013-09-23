package ac.jobmanage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import ac.prediction.model.HMMInput;
import ac.prediction.model.ANNInput;

public class JobDAO {
	private Connection conn = null;
	private static String className = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://117.16.146.121/aircleaner?autoDeserialize=true";
	private static String userID = "subair";
	private static String passwd = "subair";
	private final static int HMM = 1;
	private final static int ANN = 2;

	public JobDAO()
	{
		try {
			try {   
				Class.forName(className);   
			}catch(ClassNotFoundException e) {   
				System.err.println("error = " + e);   
			}   
			conn = DriverManager.getConnection(dbURL, userID, passwd);			
		}
		catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

	public int getCountQueuedJob(int jobLastID) {
		int countValue = 0;
		PreparedStatement pstmt = null;
		String query = "SELECT COUNT(*) FROM t104_job_info WHERE job_stat = \"QUEUED\" AND job_id > ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, jobLastID);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				countValue = rs.getInt(1);										

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return countValue;		
	}

	//public int getCountStopJob(int stopLastID) {
	public int getCountStopJob() {
		int countValue = 0;
		PreparedStatement pstmt = null;
		//String query = "SELECT COUNT(*) FROM t104_job_info WHERE job_stat = \"STOP\" AND job_id > ? ";
		String query = "SELECT COUNT(*) FROM t104_job_info WHERE job_stat = \"STOP\"";

		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setInt(1, stopLastID);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				countValue = rs.getInt(1);										

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return countValue;		
	}

	public String getSWPath(int host_id) {
		String path = "";
		PreparedStatement pstmt = null;
		String query = "SELECT sw_path FROM t103_resource_info WHERE host_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, host_id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				path = rs.getString(1);										

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return path;		
	}

	public String getModelPath(int model_id) {
		String path = "";
		PreparedStatement pstmt = null;
		String query = "SELECT path FROM t102_prediction_info WHERE model_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, model_id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				path = rs.getString(1);										

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return path;		
	}

	public String getTrainsetPath(int trainset_id) {
		String path = "";
		PreparedStatement pstmt = null;
		String query = "SELECT file_path FROM t101_trainset_info WHERE model_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, trainset_id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
				path = rs.getString(1);										

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return path;		
	}

	public void setJobStateToSubmitted(int job_id) {
		PreparedStatement pstmt = null;
		String query = "UPDATE t104_job_info SET job_stat = 'SUBMITTED' WHERE job_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, job_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setJobStateToRunning(int job_id) {
		PreparedStatement pstmt = null;
		String query = "UPDATE t104_job_info SET job_stat = 'RUNNING' WHERE job_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, job_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setJobStateKILLED(int job_id) {
		PreparedStatement pstmt = null;
		String query = "UPDATE t104_job_info SET job_stat = 'KILLED' WHERE job_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, job_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setJobStateToFailed(int job_id) {
		PreparedStatement pstmt = null;
		String query = "UPDATE t104_job_info SET job_stat = 'FAILED' WHERE job_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, job_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setJobStateToDone(int job_id) {
		PreparedStatement pstmt = null;
		String query = "UPDATE t104_job_info SET job_stat = 'DONE' WHERE job_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, job_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getStopJobs() {
		PreparedStatement pstmt = null;
		ArrayList<Integer> stopIDArrayList = new ArrayList<Integer>();
		String query = "SELECT job_id FROM t104_job_info WHERE job_stat = \"STOP\"";
		
		try {
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
				stopIDArrayList.add(rs.getInt(1));

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stopIDArrayList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<JobDTO> getQueuedJobs(int jobLastID) throws IOException, ClassNotFoundException {
		ArrayList<JobDTO> jobList = new ArrayList<JobDTO>();		
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM t104_job_info WHERE job_stat = \"QUEUED\" AND job_id > ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, jobLastID);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				JobDTO jobInstance = new JobDTO();
				
				jobInstance.setJob_id(rs.getInt(1));
				jobInstance.setHost_id(rs.getInt(2));
				jobInstance.setModel_id(rs.getInt(3));
				jobInstance.setTrainset_id(rs.getInt(4));
				jobInstance.setReq_data_id(rs.getInt(5));
				jobInstance.setJob_owner(rs.getString(6).trim());
				jobInstance.setOwner_mail(rs.getString(7).trim());
				jobInstance.setJob_name(rs.getString(8).trim());
				jobInstance.setJob_pri(rs.getInt(9));
				jobInstance.setJob_stat(rs.getString(10).trim());
				jobInstance.setJob_type(rs.getInt(11));
				jobInstance.setReg_date(rs.getString(12).trim());					
				byte[] buf = rs.getBytes(13);
				
				if (buf != null) {
					ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));	

					switch(jobInstance.getJob_type())  {
					case HMM:
						System.out.println("\n----------------------------------------");
						System.out.println("Job Type HMM Input");
						System.out.println("----------------------------------------\n");
						
						ArrayList<com.my.hellogxt3.shared.HMMInput> hmmInputWebArrayList = (ArrayList<com.my.hellogxt3.shared.HMMInput>) objectIn.readObject();
						System.out.println("\n----------------------------------------");
						System.out.println("Size of Input: " + hmmInputWebArrayList.size());
						System.out.println("----------------------------------------\n");
						ArrayList<HMMInput> hmmInputArrayList = new ArrayList<HMMInput>();
						Iterator<com.my.hellogxt3.shared.HMMInput> hmmIter = hmmInputWebArrayList.iterator();		
						while(hmmIter.hasNext()) {
							com.my.hellogxt3.shared.HMMInput hmmInputWeb =  hmmIter.next();
							hmmInputWeb.printHMMInput();
							HMMInput hmmInput = new HMMInput(hmmInputWeb.getTimeStamp(), hmmInputWeb.getAvg(),
														hmmInputWeb.getMax(), hmmInputWeb.getMin());
							hmmInputArrayList.add(hmmInput);
						}
						jobInstance.setHMMInput(hmmInputArrayList);
						break;
					case ANN:
						System.out.println("\n----------------------------------------");
						System.out.println("Job Type ANN Input");
						System.out.println("----------------------------------------\n");
						
						ArrayList<com.my.hellogxt3.shared.ANNInput> annInputWebArrayList = (ArrayList<com.my.hellogxt3.shared.ANNInput>) objectIn.readObject();
						System.out.println("\n----------------------------------------");
						System.out.println("Size of Input: " + annInputWebArrayList.size());
						System.out.println("----------------------------------------\n");
						ArrayList<ANNInput> annInputArrayList = new ArrayList<ANNInput>();
						Iterator<com.my.hellogxt3.shared.ANNInput> annIter = annInputWebArrayList.iterator();
						
						System.out.println("\n----------------------------------------");
						while(annIter.hasNext()) {
							com.my.hellogxt3.shared.ANNInput annInputWeb = annIter.next();
							annInputWeb.printANNInput();
							ANNInput annInput = new ANNInput(annInputWeb.getTimeStamp(), annInputWeb.getAvg_30s(),
														annInputWeb.getMax(), annInputWeb.getMin(), annInputWeb.getAvg());
							annInputArrayList.add(annInput);
						}
						System.out.println("----------------------------------------\n");
						
						jobInstance.setANNInput(annInputArrayList);
						break;
					default:
						System.out.println("\n----------------------------------------");
						System.out.println("ERROR: NOT DEFINED JOB TYPE");
						System.out.println("----------------------------------------\n");
						break;
					}
				}	// If block
				jobList.add(jobInstance);
			}

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jobList;
	}

	public void setPredictionOutputToDB(int job_id, ArrayList<com.my.hellogxt3.shared.PredictionOutput> output) {
		PreparedStatement pstmt = null;
		String query = "UPDATE t104_job_info SET output = ? WHERE job_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setObject(1, output);
			pstmt.setInt(2, job_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
