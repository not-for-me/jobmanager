package ac.jobmanage;

import java.io.IOException;
import java.util.ArrayList;

public class Producer extends Thread {
	static final int SLEEP_TIME = 5000;
	private int jobLastID = 0;

	public void run() {
		int countValue = 0;
		JobDAO jobConnection = new JobDAO();
		
		while(true) {
			System.out.println("\n----------------------------------------");
			System.out.println("Producer Thread: Time to Sleep!");
			System.out.println("----------------------------------------\n");
			
			try {Thread.sleep(SLEEP_TIME);} catch (InterruptedException e) { }
			System.out.println("\n----------------------------------------");
			System.out.println("Producer Thread: Wake Up!");
			System.out.println("----------------------------------------\n");
			
			// Check DB if there is a job whose state is "QUEUED"			
			countValue = jobConnection.getCountQueuedJob(jobLastID);
			
			// Get jobs from DB & Put it into ArrayList
			if(countValue > 0) {
				ArrayList<JobDTO> jobList = null;	
				try {
					jobList = jobConnection.getQueuedJobs(jobLastID);
				} 
				catch (IOException e) {e.printStackTrace();} 
				catch (ClassNotFoundException e) {e.printStackTrace();}
				
				int currentJobID = 0;
				for(JobDTO jobInfo : jobList) {		// Get other information from db
					jobInfo.setSw_path(jobConnection.getSWPath(jobInfo.getHost_id()));
					jobInfo.setModel_path(jobConnection.getModelPath(jobInfo.getModel_id()));
					jobInfo.setTrainset_path(jobConnection.getTrainsetPath(jobInfo.getTrainset_id()));
					
					// Put a Job into a Job Queue
					JobManager.getJobQueue().offer(jobInfo);
					currentJobID = jobInfo.getJob_id();
					
					// Update job state
					jobConnection.setJobStateToSubmitted(currentJobID);
					if(currentJobID > jobLastID)
						jobLastID = currentJobID;
				}
			}
			else {
				System.out.println("\n----------------------------------------");
				System.out.println("Database is empty in Producer Thread");
				System.out.println("----------------------------------------\n");
			}
			countValue = jobConnection.getCountStopJob();
			
			if(countValue > 0) {
				ArrayList<Integer> stopList = jobConnection.getStopJobs();
				
				for(Integer stopID : stopList) {
					System.out.println("\n----------------------------------------");
					System.out.println("Add Job ID " + stopID + " into StopHashSet" );
					System.out.println("----------------------------------------\n");
					JobManager.getStopSet().add(stopID);
				}
			}
			else {
				System.out.println("\n----------------------------------------");
				System.out.println("No Stop State in Database");
				System.out.println("----------------------------------------\n");
			}
		}
	}
}
