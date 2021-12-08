package template.method;

import javax.naming.AuthenticationException;

public class AccountAuthenticationFilter extends AbstractAuthenticationFilter {

	private UserServiceRepository theUserServiceRepository = new UserServiceRepository();

	/**
	 * 驗證邏輯
	 */
	@Override
	public UserDetail authentication(UserDetail unAunthenticationUserDetail) throws AuthenticationException {

		String account = unAunthenticationUserDetail.getAccount();

		String userName = theUserServiceRepository.findUserNameByAccount(account)
				.orElseThrow(() -> new AuthenticationException());

		return new UserDetail(account, userName);
	}

	@Override
	public void sussessfulProcess(UserDetail authenticationUserDetail) {
		System.out.println(authenticationUserDetail + " has authentication !");
	}

	@Override
	public void failProcess(Exception e) {
		System.out.println("authentication fail !");
	}

}
