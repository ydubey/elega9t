/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.geo.impl;

import com.elega9t.commons.geo.GeoLocationProvider;
import com.elega9t.commons.geo.model.GeoLocationInfo;
import com.elega9t.commons.util.IOUtilities;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HostIpDotInfoGeoLocationProvider implements GeoLocationProvider {

    @Override
    public GeoLocationInfo lookup(String ip) throws IOException {
        URL url = new URL("http://api.hostip.info/get_json.php?ip=" + URLEncoder.encode(ip, "UTF-8"));
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        String json = IOUtilities.toString(inputStream);
        inputStream.close();
        try {
            JSONObject object = new JSONObject(json);
            return new GeoLocationInfo(object.getString("ip"), object.getString("country_name"), object.getString("country_code"), object.getString("city"));
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

}
