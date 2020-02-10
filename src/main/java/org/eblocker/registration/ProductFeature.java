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

import java.util.Collections;
import java.util.List;

public enum ProductFeature {
    WOL, // Without license

    BAS, // Eblocker Base

    PRO, // Eblocker Pro

    FAM, // Eblocker Family

    LFT, // lifetime validity

    EVL, // evaluation license

    FMX, // extended validity for Family products

    EXP, // extended expert features

    EVL_BAS, // evaluation license for eBlocker Base

    EVL_PRO, // evaluation license for eBlocker Base

    EVL_FAM, // evaluation license for eBlocker Base

    ;

    public static ProductFeature getFailSafeValue(String name) {
        for (ProductFeature productFeature: ProductFeature.values()) {
            if (productFeature.name().equalsIgnoreCase(name)) {
                return productFeature;
            }
        }
        return defaultProductFeature();
    }

    public static ProductFeature defaultProductFeature() {
        return WOL;
    }

    public static List<ProductFeature> defaultFeatures() {
        return Collections.singletonList(defaultProductFeature());
    }

}
