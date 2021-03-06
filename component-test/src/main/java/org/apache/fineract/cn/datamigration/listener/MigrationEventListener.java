/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.cn.datamigration.listener;

import org.apache.fineract.cn.datamigration.api.v1.events.DatamigrationEventConstants;
import org.apache.fineract.cn.lang.config.TenantHeaderFilter;
import org.apache.fineract.cn.office.api.v1.EventConstants;
import org.apache.fineract.cn.test.listener.EventRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class MigrationEventListener {

  private final EventRecorder eventRecorder;

  @Autowired
  public MigrationEventListener(@SuppressWarnings("SpringJavaAutowiringInspection") final EventRecorder eventRecorder) {
    super();
    this.eventRecorder = eventRecorder;

  }

  @JmsListener(
      subscription = DatamigrationEventConstants.DESTINATION,
      destination = DatamigrationEventConstants.DESTINATION,
      selector = DatamigrationEventConstants.SELECTOR_INITIALIZE
  )
  public void onInitialization(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                               final String payload) {
    this.eventRecorder.event(tenant, DatamigrationEventConstants.INITIALIZE, payload, String.class);
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_POST_OFFICE
  )
  public void onCreateOffice(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                             final String payload)
          throws Exception {
   System.out.println ("office {} created: "+ payload);
    this.eventRecorder.event(tenant, EventConstants.OPERATION_POST_OFFICE, payload, String.class);
  }
}
