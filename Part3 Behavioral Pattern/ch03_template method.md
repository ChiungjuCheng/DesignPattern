# Template method 範本方法
定義一個演算法的骨架，並將一些實作步驟延遲到子類別中。使得子類別可以不改變其父類別所擁有的演算法架構就可以重新定義該演算法的某些特定步驟。

# 問題
* 相似的邏輯且有重複的code
* 有些步驟的Detail不太一樣，且有多種
* 有些做法還沒有決定。

# UML
![Template method UML](/picture/template_method.png)
圖片來自GURU  

**Abstract Class** 
有一個TemplateMethod 會以固定的順序呼叫各種方法來達成目的，其中會呼叫一些還不能定義的方法(可能有多種實現的方法或是還沒有辦法決定該怎麼實現)，並暫時使用abstract方法，並交給Concrete class去實作。

**Concrete Class** 
負責實現所有在Abstract Class宣告的abstract方法，且那些方法會在templateMethod被呼叫。  

**Hook operation**
是一個optional的方法，Abstract Class有default的方法，Concrete Class 可以決定要不要使用它來達到延伸的功能。

# Demo
為了做出負責驗證的類別，首先使用AbstractAuthenticationFilter定義出固定的驗證流程，接著宣告一個繼承他的Concrete類別AccountAuthenticationFilter。

  
AbstractAuthenticationFilter - Abstract Class 
```java
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
```

AccountAuthenticationFilter - Concrete Class
```java
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

```

Client
```java
AuthenticationFilter authenticationFilter = new AccountAuthenticationFilter();
		
authenticationFilter.attemptAuthentication(new UserDetail("account1"));
```

# Template method V.S general interface implement 
這個design pattern基本上是仰賴繼承來實現的，因為不是用composite的方法，所以在決定使用這個pattern要謹慎。

通常繼承的目的是為了實現多型，讓類別之間解耦，但是這個pattern則是必須仰賴父類別的TemplateMethod，因為他已經定義了一連串固定的邏輯和方法呼叫的順序，因此當使用時，必須先確定邏輯上的順序是否為固定的。

# Template Method and Strategy
Template Method基於繼承，class level，Strategy則是基於composition，object level。

# Composition V.S Inheritance
Composition versus Inheritance
A Comparative Look at Two Fundamental Ways to Relate Classes  
https://www.artima.com/articles/composition-versus-inheritance

Why composition is often better than inheritance   
http://joostdevblog.blogspot.com/2014/07/why-composition-is-often-better-than.html

中文翻譯  
https://tw.twincl.com/programming/*662v

# Hollywood principle
Concrete Class定義好方法後，交給Abstrac Class來呼叫
https://www.newton.com.tw/wiki/%E5%A5%BD%E8%90%8A%E5%A1%A2%E5%8E%9F%E5%89%87

# Real world Source Code


# 影片參考
https://www.youtube.com/watch?v=7ocpwK9uesw&t=2522s