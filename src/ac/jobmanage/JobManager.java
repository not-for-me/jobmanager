package ac.jobmanage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JobManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Queue<JobDTO> jobQueue = null;
	private static HashSet<Integer> stopSet = null;
	private static final int SLEEP_TIME = 3;
	public JobManager() {
		super();
		jobQueue = new LinkedList<JobDTO>();
	}

	public static Queue<JobDTO> getJobQueue() {
		if(jobQueue == null) {
			jobQueue = new LinkedList<JobDTO>();
		}

		return jobQueue;
	}

	public static HashSet<Integer> getStopSet() {
		if(stopSet == null) {
			stopSet = new HashSet<Integer>();
		}

		return stopSet;
	}
	
	public void init(){
		System.out.printf("%s 이 초기화 되었습니다.\n", getServletName());
		this.jobManage();
	}

	public void jobManage() {
	//	String recoveryMode = this.getServletContext().getInitParameter("recovery");
		
		Producer produce = new Producer();
		Executor execute = new Executor();
		
		//Thread Manage
		produce.start();
		try{Thread.sleep(SLEEP_TIME);}catch(InterruptedException ie){}
		execute.start();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
