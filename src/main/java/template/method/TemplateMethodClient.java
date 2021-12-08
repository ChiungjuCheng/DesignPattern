package template.method;

public class TemplateMethodClient {

	public static void main(String[] args) {

		AuthenticationFilter authenticationFilter = new AccountAuthenticationFilter();
		
		authenticationFilter.attemptAuthentication(new UserDetail("account1"));
				
	}
}
