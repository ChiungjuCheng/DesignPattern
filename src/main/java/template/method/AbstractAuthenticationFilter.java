package template.method;

import java.util.logging.Logger;

import javax.naming.AuthenticationException;

abstract public class AbstractAuthenticationFilter implements AuthenticationFilter {

	Logger logger = Logger.getLogger(AbstractAuthenticationFilter.class.getName());

	/**
	 * Template Method
	 */
	public void attemptAuthentication(UserDetail unAunthenticationUserDetail) {

		try {
			if (unAunthenticationUserDetail == null) {
				throw new IllegalArgumentException("UserDetail is null");
			}

			UserDetail authenticationUserDetail = authentication(unAunthenticationUserDetail);

			sussessfulProcess(authenticationUserDetail);

		} catch (Exception e) {
			logger.info(e.getMessage());
			failProcess(e);
		}

	}

	abstract public UserDetail authentication(UserDetail unAunthenticationUserDetail) throws AuthenticationException;

	abstract public void sussessfulProcess(UserDetail authenticationUserDetail);

	abstract public void failProcess(Exception e);

}
