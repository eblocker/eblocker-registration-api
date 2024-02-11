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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceRegistrationResponseTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws IOException {
        DeviceRegistrationResponse response = new DeviceRegistrationResponse(
                "email@address",
                "deviceName",
                "deviceId",
                "encodedDeviceCertificate".getBytes(),
                "encodedLicenseCertificate".getBytes(),
                "licenseType",
                "autoRenewal",
                false,
                new String[]{"confirmationMsgKey1", "confirmationMsgKey2"},
                null
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, response);

        System.out.println("JSON:\n"+new String(out.toByteArray()));

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        DeviceRegistrationResponse reloaded = mapper.readValue(in, DeviceRegistrationResponse.class);

        assertArrayEquals(response.getEncodedLicenseCertificate(), reloaded.getEncodedLicenseCertificate());
        assertArrayEquals(response.getEncodedDeviceCertificate(), reloaded.getEncodedDeviceCertificate());
        assertEquals(response.getDeviceId(), reloaded.getDeviceId());
        assertEquals(response.getDeviceName(), reloaded.getDeviceName());
        assertEquals(response.getEmailAddress(), reloaded.getEmailAddress());
        assertEquals(response.getLicenseType(), reloaded.getLicenseType());
        assertEquals(response.getAutoRenewal(), reloaded.getAutoRenewal());
        assertEquals(response.isNeedsConfirmation(), reloaded.isNeedsConfirmation());
        assertArrayEquals(response.getConfirmationMsgKeys(), reloaded.getConfirmationMsgKeys());

    }

    @Test
    void test_compatibility() throws IOException {
        DeviceRegistrationResponse response = new DeviceRegistrationResponse(
                "email@address",
                "deviceName",
                "deviceId",
                "encodedDeviceCertificate".getBytes(),
                "encodedLicenseCertificate".getBytes(),
                "licenseType",
                "autoRenewal",
                null,
                null,
                null
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, response);

        System.out.println("JSON:\n"+new String(out.toByteArray()));

    }

}