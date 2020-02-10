/*
 * Copyright 2020 eBlocker Open Source UG (haftungsbeschraenkt)
 *
 * Licensed under the EUPL, Version 1.2 or - as soon they will be
 * approved by the European Commission - subsequent versions of the EUPL
 * (the "License"); You may not use this work except in compliance with
 * the License. You may obtain a copy of the License at:
 *
 *   https://joinup.ec.europa.eu/page/eupl-text-11-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.eblocker.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MobileConnectionCheck {

    public enum State {
        NEW,
        PENDING,
        SUCCESS,
        FAILED,
        ERROR
    }

    public enum Protocol {
        UDP,
        TCP
    }

    private final String id;
    private final State state;
    private final Date lastUpdated;
    private final byte[] secret;
    private final Protocol protocol;
    private final String ipAddress;
    private final int port;

    @JsonCreator
    public MobileConnectionCheck(@JsonProperty("id") String id,
                                 @JsonProperty("state") State state,
                                 @JsonProperty("lastUpdated") Date lastUpdated,
                                 @JsonProperty("secret") byte[] secret,
                                 @JsonProperty("protocol") Protocol protocol,
                                 @JsonProperty("ipAddress") String ipAddress,
                                 @JsonProperty("port") int port) {
        this.id = id;
        this.state = state;
        this.lastUpdated = lastUpdated;
        this.secret = secret;
        this.protocol = protocol;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public byte[] getSecret() {
        return secret;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MobileConnectionCheck)) {
            return false;
        }
        return id.equals(((MobileConnectionCheck)obj).getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
