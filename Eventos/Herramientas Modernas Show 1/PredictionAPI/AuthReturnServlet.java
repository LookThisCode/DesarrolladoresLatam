/*
 * Copyright 2012 Google Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Author: Marc Cohen
 * 
 */

package com.google.tryPredictionJava.web;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.google.tryPredictionJava.web.IndexServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthReturnServlet extends HttpServlet {

  @SuppressWarnings("unused")
  private static final Logger log = 
    LoggerFactory.getLogger(IndexServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, 
                       HttpServletResponse response) throws 
                         ServletException, IOException {

    log.info ("doGet para servicio /oauth");

    // Trabajando con los elementos json de los "secrets" para la operación de oAuth
    Map<String, String> clientSecrets = 
    (Map<String, String>) IndexServlet.parseJsonFile(IndexServlet.secretsFile).get("installed");
    
    String clientId = clientSecrets.get("client_id");
    String clientSecret = clientSecrets.get("client_secret");

    // Lectura de autenticación desde URL
    String code = request.getParameterValues("code")[0];

    // Necesitamos el http especifico de App Engine para el transporte y para el parser JSON 
    HttpTransport transport = new NetHttpTransport();
    JacksonFactory jsonFactory = new JacksonFactory();

    //Intercambio del código de autenticación y la actualización de los tokens
    AccessTokenResponse tokens = new GoogleAuthorizationCodeGrant(
      transport, jsonFactory, clientId, clientSecret, code, 
      IndexServlet.redirectUri).execute();

    // Completar las credenciales de objeto y almacenarlas en el datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity credentials = new Entity("Credentials", "Credentials");
    credentials.setProperty("accessToken", tokens.accessToken);
    credentials.setProperty("expiresIn", tokens.expiresIn);
    credentials.setProperty("refreshToken", tokens.refreshToken);
    credentials.setProperty("clientId", clientId);
    credentials.setProperty("clientSecret", clientSecret);
    datastore.put(credentials);

    // Con las credenciales redirección a la página principal
    response.sendRedirect("/");
  }
}
