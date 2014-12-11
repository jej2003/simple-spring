package org.superbiz.mdb;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;
import org.superbiz.injection.jpa.Movie;

public class PersistBean implements MessageListener {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	@Named("PrintTemplate")
	JmsTemplate template;

	@Override
	@Transactional
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage)message;
		int x;

		try {
			System.out.println("Starting Persist");
			x = Integer.parseInt(msg.getText());
			Movie movie = new Movie("director" + x, "title" + x, x);
			entityManager.persist(movie);
			final long id = movie.getId();
			System.out.println("persisted with id: " + id);
			template.send(new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(Long.toString(id));
				}
			});

			System.out.println("Leaving persist, should commit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
