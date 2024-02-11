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

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductFeatureTest {

    @Test
    void test() {

        assertEquals(ProductFeature.WOL, ProductFeature.defaultProductFeature());

        assertEquals(ProductFeature.BAS, ProductFeature.getFailSafeValue("BAS"));
        assertEquals(ProductFeature.PRO, ProductFeature.getFailSafeValue("PRO"));
        assertEquals(ProductFeature.FAM, ProductFeature.getFailSafeValue("FAM"));
        assertEquals(ProductFeature.LFT, ProductFeature.getFailSafeValue("LFT"));
        assertEquals(ProductFeature.AUP, ProductFeature.getFailSafeValue("AUP"));
        assertEquals(ProductFeature.FMX, ProductFeature.getFailSafeValue("FMX"));

        assertEquals(ProductFeature.BAS, ProductFeature.getFailSafeValue("bas"));
        assertEquals(ProductFeature.WOL, ProductFeature.getFailSafeValue(""));
        assertEquals(ProductFeature.WOL, ProductFeature.getFailSafeValue(null));
    }
}