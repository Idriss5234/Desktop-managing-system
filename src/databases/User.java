package databases;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String userId;
	private ArrayList<Dossier> home = new ArrayList();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userId;
	}
	public void setUserid(String userid) {
		this.userId = userid;
	}
	public ArrayList<Dossier> getHome() {
		return home;
	}
	public void setHome(ArrayList<Dossier> home) {
		this.home = home;
	}
	public User(String username, String password, String userid, ArrayList<Dossier> home) {
		super();
		this.username = username;
		this.password = password;
		this.userId = userid;
		this.home = home;
	}
	
	
	

}
