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
package org.superbiz.moviefun.rest;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.superbiz.ejb.IMovies;

@Controller
@RequestMapping("/movies")
public class MoviesRest {

	@Inject
    IMovies movies;

    @RequestMapping(value = "/version", consumes = { "application/json" }, method = RequestMethod.GET)
    public String version(){
    	return "1.0";
    }

    @RequestMapping(value = "/{number}", consumes = { "application/json" }, method = RequestMethod.GET)
    public String addLots(@PathVariable("number") int howMany) throws Exception{
    	movies.send(howMany);
    	return "done";
    }

}