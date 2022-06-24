package com.manager;

public class ManagerForm {
    private Integer id=new Integer(-1);  //管理员ID号
    private String name="";   //管理员名称
    private String pwd="";  //管理员密码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
   
    
}
