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

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TosContainer {
    private final String version;
    private final Date date;
    private final Map<String, String> text;

    @JsonCreator
    public TosContainer(
            @JsonProperty("version") String version,
            @JsonProperty("date") Date date,
            @JsonProperty("text") Map<String, String> text){
        this.version = version;
        this.date = date;
        this.text = text;
    }

    public String getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, String> getText() {
        return text;
    }
}
