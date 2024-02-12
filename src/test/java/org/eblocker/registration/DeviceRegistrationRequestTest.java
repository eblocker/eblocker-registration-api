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

import static org.junit.jupiter.api.Assertions.*;

class DeviceRegistrationRequestTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws IOException {
        DeviceRegistrationRequest request = new DeviceRegistrationRequest(
                "email@address",
                "deviceName",
                "licenseKey",
                "deviceId",
                "hardwareId",
                "encodedDeviceCertificate".getBytes(),
                "encodedLicenseCertificate".getBytes(),
                false,
                null
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, request);

        System.out.println("JSON:\n" + new String(out.toByteArray()));

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        DeviceRegistrationRequest reloaded = mapper.readValue(in, DeviceRegistrationRequest.class);

        assertArrayEquals(request.getEncodedLicenseCertificate(), reloaded.getEncodedLicenseCertificate());
        assertArrayEquals(request.getEncodedDeviceCertificate(), reloaded.getEncodedDeviceCertificate());
        assertEquals(request.getDeviceId(), reloaded.getDeviceId());
        assertEquals(request.getDeviceName(), reloaded.getDeviceName());
        assertEquals(request.getEmailAddress(), reloaded.getEmailAddress());
        assertEquals(request.getLicenseKey(), reloaded.getLicenseKey());
        assertEquals(request.getSerialNumber(), reloaded.getSerialNumber());
        assertEquals(request.isConfirmed(), reloaded.isConfirmed());
        assertTrue(reloaded.isConfirmationAware());

    }

    @Test
    void test_noConfirmationInfo() throws IOException {
        DeviceRegistrationRequest request = new DeviceRegistrationRequest(
                "email@address",
                "deviceName",
                "licenseKey",
                "deviceId",
                "hardwareId",
                "encodedDeviceCertificate".getBytes(),
                "encodedLicenseCertificate".getBytes(),
                null,
                null
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, request);

        String json = new String(out.toByteArray());

        // Simulate eOS v1.7 or before by masking the "confirmed" property.
        json = json.replace("\"confirmed\"", "\"unknown-property\"");

        System.out.println("JSON:\n" + json);

        DeviceRegistrationRequest reloaded = mapper.readValue(json, DeviceRegistrationRequest.class);

        assertArrayEquals(request.getEncodedLicenseCertificate(), reloaded.getEncodedLicenseCertificate());
        assertArrayEquals(request.getEncodedDeviceCertificate(), reloaded.getEncodedDeviceCertificate());
        assertEquals(request.getDeviceId(), reloaded.getDeviceId());
        assertEquals(request.getDeviceName(), reloaded.getDeviceName());
        assertEquals(request.getEmailAddress(), reloaded.getEmailAddress());
        assertEquals(request.getLicenseKey(), reloaded.getLicenseKey());
        assertEquals(request.getSerialNumber(), reloaded.getSerialNumber());
        assertTrue(reloaded.isConfirmed());
        assertFalse(reloaded.isConfirmationAware());

    }


    @Test
    void test_toString() {
        DeviceRegistrationRequest request = new DeviceRegistrationRequest(
                "email@address",
                "deviceName",
                "licenseKey",
                "deviceId",
                "hardwareId",
                "encodedDeviceCertificate".getBytes(),
                "encodedLicenseCertificate".getBytes(),
                false,
                null
        );

        String s = request.toString();

        assertTrue(s.contains("email@address"));
    }

    @Test
    void test_toStringNoConfirmationInfo() {
        DeviceRegistrationRequest request = new DeviceRegistrationRequest(
                "email@address",
                "deviceName",
                "licenseKey",
                "deviceId",
                "hardwareId",
                "encodedDeviceCertificate".getBytes(),
                "encodedLicenseCertificate".getBytes(),
                null,
                null
        );

        String s = request.toString();

        assertTrue(s.contains("email@address"));
    }

}