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
//START SNIPPET: code
package org.superbiz.mdb;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.superbiz.injection.jpa.Movie;

public class PrintBean implements MessageListener {

	@PersistenceContext
    private EntityManager entityManager;

    public void onMessage(Message message) {
    	System.out.println("Entering print");
    	final TextMessage textMessage = (TextMessage) message;

		try {
			long id = Long.parseLong(textMessage.getText());
			Movie movie = entityManager.find(Movie.class, id);
			if(movie == null){
				System.out.println("/***************** BROKEN ***************/");
				System.out.println("/*******************" + id + "*****************/");
				System.out.println("/***************** BROKEN ***************/");

			} else {
				System.out.println("updating: "+ movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		    	System.out.println("Left print");
    }
}
//END SNIPPET: code
