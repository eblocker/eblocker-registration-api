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
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class UpsellInfo {

    private final String productName;
    private final String startingPrice;
    private final String moreInfoLink;
    private final String buyNowLink;

    @JsonCreator
    public UpsellInfo(
            @JsonProperty("productName") String productName,
            @JsonProperty("startingPrice") String startingPrice,
            @JsonProperty("moreInfoLink") String moreInfoLink,
            @JsonProperty("buyNowLink") String buyNowLink
            ) {
        this.productName = productName;
        this.startingPrice = startingPrice;
        this.moreInfoLink = moreInfoLink;
        this.buyNowLink = buyNowLink;
    }

    public String getProductName() {
        return productName;
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public String getMoreInfoLink() {
        return moreInfoLink;
    }

    public String getBuyNowLink() {
        return buyNowLink;
    }

}
