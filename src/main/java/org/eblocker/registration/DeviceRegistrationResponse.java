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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceRegistrationResponse implements DeviceRegistrationResponseBase {

    private final String emailAddress;

    private final String deviceId;

    private final String deviceName;
    
    private final byte[] encodedDeviceCertificate;

    private final byte[] encodedLicenseCertificate;

    private final String licenseType;

    private final String autoRenewal;

    private final boolean needsConfirmation;

    private final String[] confirmationMsgKeys;

    private final CustomerInfo customerInfo;
    
    @JsonCreator
    public DeviceRegistrationResponse(
            @JsonProperty("emailAddress") String emailAddress,
            @JsonProperty("deviceName") String deviceName,
            @JsonProperty("deviceId") String deviceId,
            @JsonProperty("encodedDeviceCertificate") byte[] encodedDeviceCertificate,
            @JsonProperty("encodedLicenseCertificate") byte[] encodedLicenseCertificate,
            @JsonProperty("licenseType") String licenseType,
            @JsonProperty("autoRenewal") String autoRenewal,
            @JsonProperty("needsConfirmation") Boolean needsConfirmation,
            @JsonProperty("confirmationMsgKeys") String[] confirmationMsgKeys,
            @JsonProperty("customerInfo") CustomerInfo customerInfo
    ) {
        this.emailAddress = emailAddress;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.encodedDeviceCertificate = encodedDeviceCertificate;
        this.encodedLicenseCertificate = encodedLicenseCertificate;
        this.licenseType = licenseType;
        this.autoRenewal = autoRenewal;
        this.needsConfirmation = needsConfirmation == null ? false : needsConfirmation;
        this.confirmationMsgKeys = confirmationMsgKeys == null ? new String[]{} : confirmationMsgKeys;
        this.customerInfo = customerInfo;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public byte[] getEncodedDeviceCertificate() {
        return encodedDeviceCertificate;
    }

    @Override
    public byte[] getEncodedLicenseCertificate() {
        return encodedLicenseCertificate;
    }

    @Override
    public String getLicenseType() {
        return licenseType;
    }

    @Override
    public String getAutoRenewal() {
        return autoRenewal;
    }

    public boolean isNeedsConfirmation() {
        return needsConfirmation;
    }

    public String[] getConfirmationMsgKeys() {
        return confirmationMsgKeys;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
}


