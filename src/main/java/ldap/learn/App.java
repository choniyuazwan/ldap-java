package ldap.learn;

import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class App {

	DirContext connection;

	/* create connection during object creation */
	public void newConnection() {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "password");
		try {
			connection = new InitialDirContext(env);
			System.out.println("Hello World! successfully connected to Ldap - " + connection);
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NamingException {

		 App app = new App();
		 app.newConnection();
		  
	}
}