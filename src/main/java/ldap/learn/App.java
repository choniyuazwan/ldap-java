package ldap.learn;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Properties;

public class App {

	public static void main(String[] args) throws Exception {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "password");

		DirContext connection = null;

		try {
			connection = new InitialDirContext(env);
			System.out.println("successfully connected to Ldap - " + connection);
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String username = "computingforgeeks";

//		String searchFilter = "(&(|(objectClass=user)(objectClass=person))(sAMAccountName=${username}))";
//		String searchFilter = "(&(objectClass=person)(sAMAccountName=" + username + "))";
		String searchFilter = "(&(objectClass=person)(uid=" + username + "))";

		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration<?> results = connection.search("dc=example,dc=com", searchFilter, searchControls);
		
		while (results.hasMore()) {
			SearchResult result = (SearchResult) results.next();
			Attributes attrs = result.getAttributes();
			System.out.println(attrs.get("cn"));
			System.out.println(attrs);
		}
	}
}