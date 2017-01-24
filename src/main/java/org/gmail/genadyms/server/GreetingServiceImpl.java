package org.gmail.genadyms.server;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.gmail.genadyms.client.GreetingService;
import org.gmail.genadyms.server.domain.TEMP_HibernateUtil;
import org.gmail.genadyms.server.domain.datamodel.Patient;
import org.gmail.genadyms.server.domain.datamodel.Tag;
import org.gmail.genadyms.shared.FieldVerifier;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	static {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Tag tag = new Tag();
//		tag.setName("hello5");
//
//		session.save(tag);
//		session.getTransaction().commit();
		EntityManager em = Persistence.createEntityManagerFactory("gwt").createEntityManager();
		em.getTransaction().begin();
		Tag tag = new Tag();
		tag.setName("hello9");
		tag.setId(18L);
		em.merge(tag);
		em.getTransaction().commit();
	}

	public String greetServer(String input) throws IllegalArgumentException {

		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
