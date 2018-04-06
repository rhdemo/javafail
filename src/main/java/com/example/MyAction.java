package com.example;

import java.io.IOException;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileReader;;

/**
 *  MyAction
 */
public class MyAction {

  private static final Logger LOGGER = LogManager.getLogger(MyAction.class);

  public static JsonObject main(JsonObject args) {
    JsonObject response = new JsonObject();
    
    long javaMemory = Runtime.getRuntime().maxMemory();
    LOGGER.info("Java Memory: " + javaMemory);
    String cgroupsMemory = "";
    try {
      BufferedReader br = new BufferedReader(new FileReader("/sys/fs/cgroup/memory/memory.memsw.limit_in_bytes"));
      cgroupsMemory= br.readLine();
      LOGGER.info("cGroups Memory: " + cgroupsMemory);
    } catch (IOException ie) {
      LOGGER.entry(ie);
    }
    String javaVersion = System.getProperty("java.version");
    response.addProperty("JavaMemory", javaMemory);
    response.addProperty("cgroupsMemory", cgroupsMemory);
    response.addProperty("JavaVersion", javaVersion);
    
    
    return response;
  }
}
