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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceRegistrationRequest {

    private final String emailAddress;

    private final String deviceName;

    private final String licenseKey;

    private final String deviceId;
    
    private final String serialNumber;

    private final byte[] encodedDeviceCertificate;

    private final byte[] encodedLicenseCertificate;

    private final boolean confirmed;

    private final boolean confirmationAware;

    private final String tosVersion;

    @JsonCreator
    public DeviceRegistrationRequest(
            @JsonProperty("emailAddress") String emailAddress,
            @JsonProperty("deviceName") String deviceName,
            @JsonProperty("licenseKey") String licenseKey,
            @JsonProperty("deviceId") String deviceId,
            @JsonProperty("serialNumber") String serialNumber,
            @JsonProperty("encodedDeviceCertificate") byte[] encodedDeviceCertificate,
            @JsonProperty("encodedLicenseCertificate") byte[] encodedLicenseCertificate,
            @JsonProperty("confirmed") Boolean confirmed,
            @JsonProperty("tosVersion") String tosVersion
    ) {
        this.emailAddress = emailAddress;
        this.deviceName = deviceName;
        this.licenseKey = licenseKey;
        this.deviceId = deviceId;
        this.serialNumber = serialNumber;
        this.encodedDeviceCertificate = encodedDeviceCertificate;
        this.encodedLicenseCertificate = encodedLicenseCertificate;
        this.confirmationAware = confirmed != null;
        this.confirmed = confirmed == null ? true : confirmed;
        this.tosVersion = tosVersion;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public String getDeviceId() {
        return deviceId;
    }
    
    public String getSerialNumber() {
    	return serialNumber;
    }

    public byte[] getEncodedDeviceCertificate() {
        return encodedDeviceCertificate;
    }

    public byte[] getEncodedLicenseCertificate() {
        return encodedLicenseCertificate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @JsonIgnore
    public boolean isConfirmationAware() {
        return confirmationAware;
    }

    public String getTosVersion() {
        return tosVersion;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("DeviceRegistrationRequest[");
        s.append("emailAddress").append("=").append(emailAddress).append(", ");
        s.append("deviceName").append("=").append(deviceName).append(", ");
        s.append("licenseKey").append("=").append(licenseKey).append(", ");
        s.append("deviceId").append("=").append(deviceId).append(", ");
        s.append("hardwareId").append("=").append(serialNumber).append(", ");
        s.append("confirmed").append("=").append(confirmed).append(", ");
        s.append("tosVersion").append("=").append(tosVersion);
        s.append("]");
        return s.toString();
    }
}


