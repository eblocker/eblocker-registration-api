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
package org.eblocker.registration.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientRequestErrorTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Integer STATUS = 400;
    private static final String ERROR = "Bad Request";
    private static final String MESSAGE = "error.registration.badRequest";
    private static final Long TIMESTAMP = new Date().getTime();
    private static final List<String> PARAMS = Arrays.asList(new String[]{"foo", "bar"});
    @Test
    void test() throws IOException {
        ClientRequestError error = new ClientRequestError(
                STATUS,
                ERROR,
                MESSAGE,
                TIMESTAMP,
                PARAMS
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, error);

        System.out.println("JSON:\n" + new String(out.toByteArray()));

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ClientRequestError reloaded = mapper.readValue(in, ClientRequestError.class);

        assertEquals(STATUS, reloaded.getStatus());
        assertEquals(ERROR, reloaded.getError());
        assertEquals(MESSAGE, reloaded.getMessage());
        assertEquals(TIMESTAMP, reloaded.getTimestamp());
        assertEquals(PARAMS, reloaded.getParams());

    }

    @Test
    void test2() throws IOException {
        String json = "{\n" +
                "  \"status\" : "+STATUS+",\n" +
                "  \"error\" : \""+ERROR+"\",\n" +
                "  \"message\" : \""+MESSAGE+"\",\n" +
                "  \"timestamp\" : \""+TIMESTAMP+"\",\n" +
                "  \"params\" : "+PARAMS.stream().collect(Collectors.joining("\", \"", "[\"", "\"]"))+"\n" +
                "}";

        ClientRequestError reloaded = mapper.readValue(json, ClientRequestError.class);

        assertEquals(STATUS, reloaded.getStatus());
        assertEquals(ERROR, reloaded.getError());
        assertEquals(MESSAGE, reloaded.getMessage());
        assertEquals(TIMESTAMP, reloaded.getTimestamp());
        assertEquals(PARAMS, reloaded.getParams());

    }

    @Test
    void test3() throws IOException {
        String json = "{\n" +
                //"  \"status\" : "+STATUS+",\n" +
                //"  \"error\" : \""+ERROR+"\",\n" +
                "  \"message\" : \""+MESSAGE+"\",\n" +
                //"  \"timestamp\" : \""+TIMESTAMP+"\",\n" +
                "  \"params\" : "+PARAMS.stream().collect(Collectors.joining("\", \"", "[\"", "\"]"))+"\n" +
                "}";

        ClientRequestError reloaded = mapper.readValue(json, ClientRequestError.class);

        assertEquals(Integer.valueOf(0), reloaded.getStatus());
        Assertions.assertNull(reloaded.getError());
        assertEquals(MESSAGE, reloaded.getMessage());
        Assertions.assertNull(reloaded.getTimestamp());
        assertEquals(PARAMS, reloaded.getParams());

    }

    @Test
    void test4() throws IOException {
        String json = "{\n" +
                "  \"status\" : "+STATUS+",\n" +
                "  \"error\" : \""+ERROR+"\",\n" +
                "  \"message\" : \""+MESSAGE+"\",\n" +
                "  \"timestamp\" : \""+TIMESTAMP+"\",\n" +
                "  \"path\" : \""+"/some/path"+"\",\n" +
                "  \"params\" : "+PARAMS.stream().collect(Collectors.joining("\", \"", "[\"", "\"]"))+"\n" +
                "}";

        ClientRequestError reloaded = mapper.readValue(json, ClientRequestError.class);

        assertEquals(STATUS, reloaded.getStatus());
        assertEquals(ERROR, reloaded.getError());
        assertEquals(MESSAGE, reloaded.getMessage());
        assertEquals(TIMESTAMP, reloaded.getTimestamp());
        assertEquals(PARAMS, reloaded.getParams());

    }
}