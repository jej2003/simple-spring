/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.superbiz.ejb;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.superbiz.injection.jpa.Movie;

@Service
public class Movies implements IMovies {

	@PersistenceContext
	private EntityManager entityManager;

//	@Inject
//	@Named("printQueue")
//	Queue printQueue;

	@Inject
	@Named("PersistTemplate")
	JmsTemplate template;

	@Inject
	PlatformTransactionManager txManager;

	@Transactional
	public void send(Integer number) {
		for (int x = 0; x < number; x++) {
			templateJms(x);
//			Movie movie = new Movie("director" + x, "title" + x, x);
//			entityManager.persist(movie);
////			doJms(movie);
//			templateJms(movie);
		}
	}

	public void templateJms(final int x){
		template.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(Integer.toString(x));
			}
		});
	}

}
