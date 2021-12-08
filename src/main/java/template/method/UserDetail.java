package template.method;

public class UserDetail {

	private String account;
	
	private String userName;
	
	private boolean isAuthentication;
	
	public UserDetail(String account) {
		this.account = account;
	}
	
	public UserDetail(String account, String userName) {
		this.account = account;
		this.userName = userName;
		this.isAuthentication = true;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAuthentication() {
		return isAuthentication;
	}

	@Override
	public String toString() {
		return "UserDetail [account=" + account + ", userName=" + userName + ", isAuthentication=" + isAuthentication
				+ "]";
	}
	
}
