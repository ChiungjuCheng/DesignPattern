package template.method;

import java.util.Map;
import java.util.Optional;

public class UserServiceRepository {

	private final static Map<String, String> userDetailMap = Map.of("account1", "user1", "account2", "account2");

	public Optional<String> findUserNameByAccount(String account) {

		String userName = userDetailMap.get(account);

		return Optional.ofNullable(userName);

	}
}
