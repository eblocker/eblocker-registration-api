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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MobileConnectionCheckTest {
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws IOException {
        MobileConnectionCheck mobileConnectionCheck = new MobileConnectionCheck(
                UUID.randomUUID().toString(),
                MobileConnectionCheck.State.NEW,
                new Date(),
                "top-secret".getBytes(),
                MobileConnectionCheck.Protocol.TCP,
                "127.0.0.1",
                3004
        );

        String json = mapper.writeValueAsString(mobileConnectionCheck);

        //System.out.println(json);

        MobileConnectionCheck reloaded = mapper.readValue(json, MobileConnectionCheck.class);

        assertEquals(mobileConnectionCheck.getId(), reloaded.getId());
        assertEquals(mobileConnectionCheck.getState(), reloaded.getState());
        assertEquals(mobileConnectionCheck.getLastUpdated(), reloaded.getLastUpdated());
        assertArrayEquals(mobileConnectionCheck.getSecret(), reloaded.getSecret());
        assertEquals(mobileConnectionCheck.getProtocol(), reloaded.getProtocol());
        assertEquals(mobileConnectionCheck.getIpAddress(), reloaded.getIpAddress());
        assertEquals(mobileConnectionCheck.getPort(), reloaded.getPort());
    }

}