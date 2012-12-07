/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.geo;

import com.elega9t.commons.geo.impl.HostIpDotInfoGeoLocationProvider;
import com.elega9t.commons.geo.model.GeoLocationInfo;

import java.io.IOException;
import java.net.InetAddress;

public class GeoLocationFactory {

    private static final GeoLocationProvider geoLocationProvider = new HostIpDotInfoGeoLocationProvider();

    public static GeoLocationInfo lookup(String ip) throws IOException {
        return geoLocationProvider.lookup(ip);
    }

    public static GeoLocationInfo lookup(InetAddress inetAddress) throws IOException {
        return lookup(inetAddress.getHostAddress());
    }

}
