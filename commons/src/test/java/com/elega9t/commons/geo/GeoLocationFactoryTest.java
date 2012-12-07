/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.geo;

import com.elega9t.commons.geo.model.GeoLocationInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeoLocationFactoryTest {

    @Test
    public void canLookupUsIp() throws Exception {
        String ip = "12.215.42.19.43";
        GeoLocationInfo geoLocationInfo = GeoLocationFactory.lookup(ip);
        assertEquals(ip, geoLocationInfo.getIp());
        assertEquals("UNITED STATES", geoLocationInfo.getCountry());
        assertEquals("US", geoLocationInfo.getCountryCode());
        assertEquals("Aurora, TX", geoLocationInfo.getCity());
    }

    @Test
    public void canLookupUkIp() throws Exception {
        String ip = "80.238.1.133";
        GeoLocationInfo geoLocationInfo = GeoLocationFactory.lookup(ip);
        assertEquals(ip, geoLocationInfo.getIp());
        assertEquals("UNITED KINGDOM", geoLocationInfo.getCountry());
        assertEquals("GB", geoLocationInfo.getCountryCode());
        assertEquals("London", geoLocationInfo.getCity());
    }

    @Test
    public void canLookupLoopBack() throws Exception {
        GeoLocationInfo geoLocationInfo = GeoLocationFactory.lookup("127.0.0.1");
        assertEquals("127.0.0.1", geoLocationInfo.getIp());
        assertEquals("(Private Address)", geoLocationInfo.getCountry());
        assertEquals("XX", geoLocationInfo.getCountryCode());
        assertEquals("(Private Address)", geoLocationInfo.getCity());
    }

}
