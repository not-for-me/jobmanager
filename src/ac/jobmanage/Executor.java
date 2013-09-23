package ac.jobmanage;

import java.util.ArrayList;
import java.util.Iterator;
import ac.prediction.thread.test.*;
import ac.prediction.model.HMM;
import ac.prediction.model.HMMOutput;
import ac.prediction.model.ANN;
import ac.prediction.model.ANNOutput;

public class Executor extends Thread {
	private static final int SLEEP_TIME = 15000;
	private final static int HMM = 1;
	private final static int ANN = 2;

	//private final static int RUNNING = 1;
	//private final static int STOP = 2;
	//private final static int STOP_DONE = 3;
	private final static int DONE = 4;

	public void run() {
		while(true) {
			System.out.println("\n----------------------------------------");
			System.out.println("Executor Thread: Time to Sleep!");
			System.out.println("----------------------------------------\n");

			try{Thread.sleep(SLEEP_TIME);}catch(InterruptedException ie){}
			System.out.println("\n----------------------------------------");
			System.out.println("Executor Thread: Wake Up!");
			System.out.println("----------------------------------------\n");

			while(true) {
				// Check Queue if Job is or not
				if(JobManager.getJobQueue().peek() != null) {
					// Get a Job from queue
					JobDTO jobInfo = JobManager.getJobQueue().poll();
					JobDAO jobConnection = new JobDAO();

					if( JobManager.getStopSet().contains(new Integer(jobInfo.getJob_id())) ) {
						jobConnection.setJobStateKILLED(jobInfo.getJob_id());
						JobManager.getStopSet().remove(new Integer(jobInfo.getJob_id())); 
					}
					else {			// Check required Manager
						if(jobInfo.getModel_id() == HMM) {
							HMM hmm = new HMM();
							hmm.setMATLABPath(jobInfo.getSw_path());
							hmm.setModelPath(jobInfo.getModel_path());
							hmm.setTrainSetPath(jobInfo.getTrainset_path());
							hmm.setIn(jobInfo.getHMMInput());
							System.out.println("\n----------------------------------------");
							hmm.printInput();
							System.out.println("----------------------------------------\n");
							
							System.out.println("\n----------------------------------------");
							System.out.println("Start to Run Job Id: " + jobInfo.getJob_id());
							System.out.println("----------------------------------------\n");
							jobConnection.setJobStateToRunning(jobInfo.getJob_id());
							hmmThread1 hmmThread = new hmmThread1(hmm);							
							hmmThread.start();

							while (true) {
								if( JobManager.getStopSet().contains(new Integer(jobInfo.getJob_id())) ) {
									hmmThread.stopModel();									
									System.out.println("\n----------------------------------------");
									System.out.println("Job Status: \"KILLED\"");
									System.out.println("----------------------------------------\n");
									jobConnection.setJobStateKILLED(jobInfo.getJob_id());
									JobManager.getStopSet().remove(new Integer(jobInfo.getJob_id())); 
									break;
								}									
								else if(hmmThread.getHMM().getStatus() == DONE) {
									System.out.println("\n----------------------------------------");
									System.out.println("Job Status: \"DONE\"");
									System.out.println("----------------------------------------\n");
									
									System.out.println("\n----------------------------------------");
									hmm.printOutput();
									System.out.println("----------------------------------------\n");
									
									Iterator<HMMOutput> hmmIter = hmm.getOut().iterator();
									ArrayList<com.my.hellogxt3.shared.PredictionOutput> outputWebArrayList = new ArrayList<com.my.hellogxt3.shared.PredictionOutput>();
									while(hmmIter.hasNext()) {
										HMMOutput hmmOutput = hmmIter.next();
										com.my.hellogxt3.shared.PredictionOutput hmmOutputWeb = new com.my.hellogxt3.shared.PredictionOutput(hmmOutput.getTimestamp(), 
																														hmmOutput.getP_val(), hmmOutput.getR_val());	
										hmmOutputWeb.printPredictionOutput();
										outputWebArrayList.add(hmmOutputWeb);
									}
									jobConnection.setJobStateToDone(jobInfo.getJob_id());
									jobConnection.setPredictionOutputToDB(jobInfo.getJob_id(), outputWebArrayList);
									break;
								}
							}	// While()
						}	// Job Model Choose
						else if(jobInfo.getModel_id() == ANN){
							ANN ann = new ANN();
							ann.setMATLABPath(jobInfo.getSw_path());
							ann.setModelPath(jobInfo.getModel_path());
							ann.setTrainSetPath(jobInfo.getTrainset_path());
							ann.setIn(jobInfo.getANNInput());
							System.out.println("\n----------------------------------------");
							ann.printInput();
							System.out.println("----------------------------------------\n");
							
							System.out.println("\n----------------------------------------");
							System.out.println("Start to Run Job Id: " + jobInfo.getJob_id());
							System.out.println("----------------------------------------\n");
							jobConnection.setJobStateToRunning(jobInfo.getJob_id());
							annThread1 annThread = new annThread1(ann);							
							annThread.start();
							
							while (true) {
								if( JobManager.getStopSet().contains(new Integer(jobInfo.getJob_id())) ) {
									annThread.stopModel();							
									System.out.println("\n----------------------------------------");
									System.out.println("Job Status: \"KILLED\"");
									System.out.println("----------------------------------------\n");
									jobConnection.setJobStateKILLED(jobInfo.getJob_id());
									JobManager.getStopSet().remove(new Integer(jobInfo.getJob_id()));
									break;
								}									
								else if(annThread.getANN().getStatus() == DONE) {
									System.out.println("\n----------------------------------------");
									System.out.println("Job Status: \"DONE\"");
									System.out.println("----------------------------------------\n");
									
									System.out.println("\n----------------------------------------");
									ann.printOutput();
									System.out.println("----------------------------------------\n");

									Iterator<ANNOutput> annIter =ann.getOut().iterator();
									ArrayList<com.my.hellogxt3.shared.PredictionOutput> outputWebArrayList = new ArrayList<com.my.hellogxt3.shared.PredictionOutput>();
									while(annIter.hasNext()) {
										ANNOutput annOutput = annIter.next();
										com.my.hellogxt3.shared.PredictionOutput annOutputWeb = new com.my.hellogxt3.shared.PredictionOutput(annOutput.getTimestamp(), 
																														annOutput.getP_val(), annOutput.getR_val());
										annOutputWeb.printPredictionOutput();
										outputWebArrayList.add(annOutputWeb);
									}
									jobConnection.setJobStateToDone(jobInfo.getJob_id());
									jobConnection.setPredictionOutputToDB(jobInfo.getJob_id(), outputWebArrayList);
									break;
								}
							}	// While()
						}	// Job Model Choose
					}	// Job Stop
				}	// Job Queue
				else{
					System.out.println("\n----------------------------------------");
					System.out.println("Executor Thread: Empty Job Queue");
					System.out.println("----------------------------------------\n");
					break;
				}
			}	// Inner While - job
		}	// Outer While - for Thread
	}	// run method
}
