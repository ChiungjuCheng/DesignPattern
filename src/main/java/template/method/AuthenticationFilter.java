package template.method;

import javax.naming.AuthenticationException;

public interface AuthenticationFilter {
	void attemptAuthentication(UserDetail unAunthenticationUserDetail);

	UserDetail authentication(UserDetail unAunthenticationUserDetail) throws AuthenticationException;

	void sussessfulProcess(UserDetail authenticationUserDetail);

	void failProcess(Exception e);

}
