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
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UpgradeProductInfoTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws IOException {
        UpgradeProductInfo entity = new UpgradeProductInfo(
                true,
                null
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, entity);

        System.out.println("JSON:\n" + new String(out.toByteArray()));

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        UpgradeProductInfo reloaded = mapper.readValue(in, UpgradeProductInfo.class);

        assertEquals(entity.isUpgradeAvailable(), reloaded.isUpgradeAvailable());
    }
}