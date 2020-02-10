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

public enum UpdateMode {

    UNKNOWN(0),
    AUTO(1),
    MANUAL(2),
    ;

    private final int mode;

    UpdateMode(int mode) {
        this.mode = mode;
    }

    public static UpdateMode getFailSafeValue(Integer mode) {
        if (mode == null) {
            return UNKNOWN;
        }
        for (UpdateMode updateMode: UpdateMode.values()) {
            if (mode.equals(updateMode.mode)) {
                return updateMode;
            }
        }
        return UNKNOWN;
    }

    public int getMode() {
        return mode;
    }
}
