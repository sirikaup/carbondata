/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.carbondata.core.util;

import java.io.Serializable;
import java.util.Map;

import org.apache.carbondata.core.exception.InvalidConfigurationException;

/**
 * This class maintains carbon session information details
 */
public class CarbonSessionInfo implements Serializable, Cloneable {

  private static final long serialVersionUID = 7738818814501121256L;
  // contains carbon session param details
  private SessionParams sessionParams;
  private SessionParams threadParams;

  public SessionParams getSessionParams() {
    return sessionParams;
  }

  public void setSessionParams(SessionParams sessionParams) {
    this.sessionParams = sessionParams;
  }

  public SessionParams getThreadParams() {
    return threadParams;
  }

  public void setThreadParams(SessionParams threadParams) {
    this.threadParams = threadParams;
  }

  public CarbonSessionInfo() {
    this.sessionParams = new SessionParams();
    this.threadParams = new SessionParams();
  }

  public CarbonSessionInfo clone() throws CloneNotSupportedException {
    super.clone();
    CarbonSessionInfo newObj = new CarbonSessionInfo();
    for (Map.Entry<String, String> entry : sessionParams.getAll().entrySet()) {
      try {
        newObj.getSessionParams().addProperty(entry.getKey(), entry.getValue(), false);
      } catch (InvalidConfigurationException ex) {
        ex.printStackTrace();
      }
    }
    for (Map.Entry<String, String> entry : threadParams.getAll().entrySet()) {
      try {
        newObj.getThreadParams().addProperty(entry.getKey(), entry.getValue(), false);
      } catch (InvalidConfigurationException ex) {
        ex.printStackTrace();
      }
    }
    return newObj;
  }
}
