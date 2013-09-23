package ac.jobmanage;

import java.util.ArrayList;
import ac.prediction.model.HMMInput;
import ac.prediction.model.ANNInput;
import ac.prediction.model.HMMOutput;
import ac.prediction.model.ANNOutput;

public class JobDTO {
	private int job_id;
	private int host_id;
	private int model_id;
	private int trainset_id;
	private int req_data_id;
	private String job_owner;
	private String owner_mail;
	private String job_name;
	private int job_pri;
	private String job_stat;
	private int job_type;
	private String reg_date;
	private String trainset_path;
	private String model_path;
	private String sw_path;
	private int period;
	ArrayList<HMMInput> hmmInput;
	ArrayList<HMMOutput> hmmOutput;
	ArrayList<ANNInput> annInput;
	ArrayList<ANNOutput> annOutput;

	public JobDTO(){
		this.job_id = 0;
		this.host_id = 0;
		this.model_id = 0;
		this.trainset_id = 0;
		this.req_data_id = 0;
		this.job_owner = "";
		this.owner_mail = "";
		this.job_name = "";
		this.job_pri = 0;
		this.job_stat = "";
		this.job_type = 0;
		this.reg_date = "";		
		this.trainset_path = "";
		this.model_path = "";
		this.sw_path = "";
		this.period = 0;
		this.hmmInput = null;
		this.hmmOutput = null;
		this.annInput = null;
		this.annOutput = null;
	}

	public JobDTO(int job_id, int host_id, int model_id, int trainset_id, int req_data_id, String job_owner,
			String owner_mail, String job_name, int job_pri, String job_stat, int job_type, 
			String reg_date, int period, ArrayList<HMMInput> hmmInput, ArrayList<HMMOutput> hmmOutput, ArrayList<ANNInput> annInput, ArrayList<ANNOutput> annOutput){
		this.job_id = job_id;
		this.host_id = host_id;
		this.model_id = model_id;
		this.trainset_id = trainset_id;
		this.req_data_id = req_data_id;
		this.job_owner = job_owner;
		this.owner_mail = owner_mail;
		this.job_name = job_name;
		this.job_pri = job_pri;
		this.job_stat = job_stat;
		this.job_type = job_type;
		this.reg_date = reg_date;
		this.period = period;
		this.hmmInput = hmmInput;
		this.hmmOutput = hmmOutput;
		this.annInput = annInput;
		this.annOutput = annOutput;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getHost_id() {
		return host_id;
	}

	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	public int getModel_id() {
		return model_id;
	}

	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}

	public int getTrainset_id() {
		return trainset_id;
	}

	public void setTrainset_id(int trainset_id) {
		this.trainset_id = trainset_id;
	}	

	public int getReq_data_id() {
		return req_data_id;
	}

	public void setReq_data_id(int req_data_id) {
		this.req_data_id = req_data_id;
	}

	public String getJob_owner() {
		return job_owner;
	}

	public void setJob_owner(String job_owner) {
		this.job_owner = job_owner;
	}

	public String getOwner_mail() {
		return owner_mail;
	}

	public void setOwner_mail(String owner_mail) {
		this.owner_mail = owner_mail;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public int getJob_pri() {
		return job_pri;
	}

	public void setJob_pri(int job_pri) {
		this.job_pri = job_pri;
	}

	public String getJob_stat() {
		return job_stat;
	}

	public void setJob_stat(String job_stat) {
		this.job_stat = job_stat;
	}

	public int getJob_type() {
		return job_type;
	}

	public void setJob_type(int job_type) {
		this.job_type = job_type;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getTrainset_path() {
		return trainset_path;
	}

	public void setTrainset_path(String trainset_path) {
		this.trainset_path = trainset_path;
	}

	public String getModel_path() {
		return model_path;
	}

	public void setModel_path(String model_path) {
		this.model_path = model_path;
	}

	public String getSw_path() {
		return sw_path;
	}

	public void setSw_path(String sw_path) {
		this.sw_path = sw_path;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public ArrayList<HMMInput> getHMMInput() {
		return hmmInput;
	}

	public void setHMMInput(ArrayList<HMMInput> hmmInput) {
		this.hmmInput = hmmInput;
	}

	public ArrayList<HMMOutput> getHMMOutput() {
		return hmmOutput;
	}

	public void setHMMOutput(ArrayList<HMMOutput> hmmOutput) {
		this.hmmOutput = hmmOutput;
	}

	public ArrayList<ANNInput> getANNInput() {
		return annInput;
	}

	public void setANNInput(ArrayList<ANNInput> annInput) {
		this.annInput = annInput;
	}

	public ArrayList<ANNOutput> getANNOutput() {
		return annOutput;
	}

	public void setANNOutput(ArrayList<ANNOutput> annOutput) {
		this.annOutput = annOutput;
	}	

	public void printJobInfo() {
		System.out.println("\n----------------------------------------");
		System.out.println("Job ID: " + this.getJob_id());
		System.out.println("Host ID: " + this.getHost_id());
		System.out.println("MOdel ID: " + this.getModel_id());
		System.out.println("Training Set ID: " + this.getTrainset_id());
		System.out.println("Request ID: " + this.getReq_data_id());					
		System.out.println("Job Owner: " + this.getJob_owner());
		System.out.println("Job Owner Mail: " + this.getOwner_mail());
		System.out.println("Job Name: " + this.getJob_name());
		System.out.println("Job Priority: " + this.getJob_pri());
		System.out.println("Job Status: " + this.getJob_stat());		
		System.out.println("Job Type: " + this.getJob_type());									
		System.out.println("Job Reg Date: " + this.getReg_date());
		System.out.println("----------------------------------------\n");
	}
}
