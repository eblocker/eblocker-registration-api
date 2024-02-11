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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LicenseTypeTest {

    @Test
    void test() {

        assertFalse(LicenseType.COMMUNITY.isSubscription());
        assertTrue(LicenseType.SUBSCRIPTION.isSubscription());

        assertEquals(LicenseType.COMMUNITY, LicenseType.defaultLicenseType());

        assertEquals(LicenseType.COMMUNITY, LicenseType.getFailSafeValue("COMMUNITY"));
        assertEquals(LicenseType.COMMUNITY, LicenseType.getFailSafeValue("community"));
        assertEquals(LicenseType.COMMUNITY, LicenseType.getFailSafeValue("CoMmUnItY"));

        assertEquals(LicenseType.SUBSCRIPTION, LicenseType.getFailSafeValue("SUBSCRIPTION"));
        assertEquals(LicenseType.SUBSCRIPTION, LicenseType.getFailSafeValue("subscription"));
        assertEquals(LicenseType.SUBSCRIPTION, LicenseType.getFailSafeValue("sUbScRiPtIoN"));

        assertEquals(LicenseType.COMMUNITY, LicenseType.getFailSafeValue("foo bar"));
        assertEquals(LicenseType.COMMUNITY, LicenseType.getFailSafeValue(""));
        assertEquals(LicenseType.COMMUNITY, LicenseType.getFailSafeValue(null));

    }
}