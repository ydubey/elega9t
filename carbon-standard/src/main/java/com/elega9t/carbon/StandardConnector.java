/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.carbon;

import com.elega9t.carbon.api.Connector;
import com.elega9t.commons.entity.LifecycleListenerRunnableEntity;
import com.elega9t.commons.entity.RunnableEntityException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

import static com.elega9t.carbon.Constants.Connector.Defaults.CONNECT_TIMEOUT;

public class StandardConnector extends LifecycleListenerRunnableEntity implements Connector {

    private final int port;
    private int connectTimeout = CONNECT_TIMEOUT;

    private ServerSocket serverSocket;

    public StandardConnector(String id, String name, int port) {
        super(id, name);
        this.port = port;
    }

    @Override
    protected void startEntity() throws RunnableEntityException {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(connectTimeout);
            try {
                Socket client = serverSocket.accept();
                logger.log(Level.INFO, "Connection accepted from " + client.getInetAddress().getHostAddress());
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                logger.log(Level.WARNING, "while handling client", e);
            }
        } catch (IOException e) {
            throw new RunnableEntityException("While starting connector on port [" + port + "]", e);
        }
    }

    @Override
    protected void stopEntity() throws RunnableEntityException {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RunnableEntityException("While stopping connector [" + getUniqueName() +"]", e);
        }
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

}
