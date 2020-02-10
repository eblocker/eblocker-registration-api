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

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductInfoTest {
    private static ObjectMapper mapper = new ObjectMapper();
    private ProductInfo entity;

    @Before
    public void initFixtures() {
        entity = new ProductInfo(
                "productId",
                "productName",
                new String[]{"F01", "F02", "F03"}
        );
    }

    @Test
    public void testConstructor() {
        entity = new ProductInfo(
                "productId",
                "productName",
                null);
        assertEquals(0, entity.getProductFeatures().length);
    }

    @Test
    public void test() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, entity);

        System.out.println("JSON:\n" + new String(out.toByteArray()));

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ProductInfo reloaded = mapper.readValue(in, ProductInfo.class);

        assertEquals(entity.getProductId(), reloaded.getProductId());
        assertEquals(entity.getProductName(), reloaded.getProductName());
        assertArrayEquals(entity.getProductFeatures(), reloaded.getProductFeatures());
    }

    @Test
    public void testToString() {
        assertTrue(entity.toString().contains("productId"));
    }

    @Test
    public void testRemoveProductFeature() {
        entity.removeProductFeature("F01");
        entity.removeProductFeature("XYZ");
        assertTrue(entity.getProductFeatures().length == 2);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals() {
        assertFalse(entity.equals(null));
        assertFalse(entity.equals("test"));
        assertTrue(entity.equals(entity));

        ProductInfo entity2 = new ProductInfo(
                "productId",
                "productName",
                new String[]{"F01", "F02", "F03"}
        );
        assertTrue(entity2.equals(entity));

        entity2 = new ProductInfo(
                null,
                "productName",
                new String[]{"F01", "F02", "F03"}
        );
        ProductInfo entity3 = new ProductInfo(
                null,
                "productName",
                new String[]{"F01", "F02", "F03"}
        );
        assertFalse(entity2.equals(entity));
        assertTrue(entity3.equals(entity2));

        entity2 = new ProductInfo(
                "productId",
                null,
                new String[]{"F01", "F02", "F03"}
        );
        entity3 = new ProductInfo(
                "productId",
                null,
                new String[]{"F01", "F02", "F03"}
        );
        assertFalse(entity2.equals(entity));
        assertTrue(entity3.equals(entity2));

        entity2 = new ProductInfo(
                "productId-XZ",
                "productName",
                new String[]{"F01", "F02", "F03"}
        );
        assertFalse(entity2.equals(entity));

        entity2 = new ProductInfo(
                "productId",
                "productName-XZ",
                new String[]{"F01", "F02", "F03"}
        );
        assertFalse(entity2.equals(entity));

        entity.removeProductFeature("F01");
        assertFalse(entity2.equals(entity));
    }

    @Test
    public void testHashCode () {
        assertEquals(1526013430, entity.hashCode());

        entity = new ProductInfo(
                null,
                "productName",
                new String[]{"F01", "F02", "F03"}
        );
        assertEquals(-226973920, entity.hashCode());

        entity = new ProductInfo(
                "productId",
                null,
                new String[]{"F01", "F02", "F03"}
        );
        assertEquals(-1277136420, entity.hashCode());
    }
}