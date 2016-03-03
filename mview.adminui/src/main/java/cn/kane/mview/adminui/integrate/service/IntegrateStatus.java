package cn.kane.mview.adminui.integrate.service;

public enum IntegrateStatus {

	NEW("NEW","COMMIT",null,"DISABLE"),
	COMMITED("COMMITED","PUBLISH","REBACK-NEW","DISABLE"),
	PUBLISHED("PUBLISHED",null,"ROLLBACK",null),
	ROLLBACKED("ROLLBACKED",null,null,null),
	DISABLED("DISABLED",null,null,null)
	;
	
	private String status ;
	private String forwardName ;
	private String backwardName ;
	private String disableName ;
	private IntegrateStatus(String status,String forwardName,String backwardName,String disableName){
		this.status = status ;
	}

	public String status(){
		return this.status ;
	}
	
	public String forwardName(){
		return this.forwardName ;
	}
	
	public String backwardName(){
		return this.backwardName ;
	}
	
	public String disableName(){
		return this.disableName ;
	}
}
