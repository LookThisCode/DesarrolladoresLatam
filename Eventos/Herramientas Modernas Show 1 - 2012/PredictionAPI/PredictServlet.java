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

import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;

import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.model.Input;
import com.google.api.services.prediction.model.InputInput;
import com.google.api.services.prediction.model.Output;

import com.google.tryPredictionJava.web.IndexServlet;

public class PredictServlet extends HttpServlet {

  @SuppressWarnings("deprecation")
@Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, 
                                                            IOException {
    Entity credentials = null;
    try {
      // Devolver las  credenciales desde el datastore de App Engine.
      DatastoreService datastore = 
        DatastoreServiceFactory.getDatastoreService();
      Key credsKey = KeyFactory.createKey("Credentials", "Credentials");
      credentials = datastore.get(credsKey);
    } catch (EntityNotFoundException ex) {
      // Si no se pueden obtener las credenciales enviamos una exception al cliente javascript.
      response.setContentType("text/html");
      response.getWriter().println("exception: " + ex.getMessage());
    }

    // Extraer el token desde la credenciales 
    AccessTokenResponse tokens = new AccessTokenResponse();
    tokens.accessToken = (String) credentials.getProperty("accessToken");
    tokens.expiresIn = (Long) credentials.getProperty("expiresIn");
    tokens.refreshToken = (String) credentials.getProperty("refreshToken");
    String clientId = (String) credentials.getProperty("clientId");
    String clientSecret = (String) credentials.getProperty("clientSecret");
    tokens.scope = IndexServlet.scope;

    // Establcer el transporte HTTP y la factory JSON 
    HttpTransport httpTransport = new NetHttpTransport();
    JsonFactory jsonFactory = new JacksonFactory();

    // Obtener el modelo requerido
    String model_name = request.getParameter("model");

    // Analizar la descripción del modelo (models.json).
    Map<String, Object> models = 
      IndexServlet.parseJsonFile(IndexServlet.modelsFile);

    // Establecer referencias para el modelo.
    Map<String, Object> selectedModel = 
      (Map<String, Object>) models.get(model_name);
    
    // Obtener el ID de modelo, 
    // iterar a sobre el modelo  y construir la lista de strings para luego pasar la solicitud de predicción 
    String modelId = (String) selectedModel.get("model_id");
    List<Object> params = new ArrayList<Object>();
    List<Map<String, String> > fields = 
      (List<Map<String, String> >) selectedModel.get("fields");
    for (Map<String, String> field : fields) {
      // Poblar los valores en la llamada de predicción.
      String label = field.get("label");
      String value = request.getParameter(label);
      params.add(value);
    }

    // Establecer el acceso OAuth 2.0 
    GoogleAccessProtectedResource requestInitializer = 
      new GoogleAccessProtectedResource(tokens.accessToken, httpTransport, 
                                        jsonFactory, clientId, clientSecret, 
                                        tokens.refreshToken);

    // Finalmente completar los datos de predición llamada a la API  and trabajar los resultados en la Javascript AJAX cliente
    Prediction prediction = new Prediction(httpTransport, requestInitializer, 
                                           jsonFactory);
    Input input = new Input();
    InputInput inputInput = new InputInput();
    inputInput.setCsvInstance(params);
    input.setInput(inputInput);
    Output output = 
      prediction.trainedmodels().predict(modelId, input).execute();
    response.getWriter().println(output.toPrettyString());
  }
}
