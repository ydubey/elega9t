/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import static java.net.NetworkInterface.getNetworkInterfaces;

public class NetworkUtilities {

    private static String EXTERNAL_IP_REGEX = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$";

    public static List<InetAddress> getLocalInetAddresses() throws SocketException {
        List<InetAddress> addrList = new ArrayList<InetAddress>();
        Enumeration<NetworkInterface> networkInterfaces = getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()) {
            NetworkInterface ifc = networkInterfaces.nextElement();
            if(ifc.isUp()) {
                Enumeration<InetAddress> inetAddresses = ifc.getInetAddresses();
                while(inetAddresses.hasMoreElements()) {
                    InetAddress addr = inetAddresses.nextElement();
                    addrList.add(addr);
                }
            }
        }
        return addrList;
    }

    public static InetAddress getExternalIp() throws SocketException {
        List<InetAddress> localInetAddresses = getLocalInetAddresses();
        Pattern pattern = Pattern.compile(EXTERNAL_IP_REGEX);
        for (InetAddress localInetAddress : localInetAddresses) {
            String hostAddress = localInetAddress.getHostAddress();
            if(!hostAddress.startsWith("127.") && pattern.matcher(hostAddress).matches()) {
                return localInetAddress;
            }
        }
        return null;
    }

    public static void main(String[] args) throws SocketException {
        System.out.println(getExternalIp().getHostAddress());
    }

}
