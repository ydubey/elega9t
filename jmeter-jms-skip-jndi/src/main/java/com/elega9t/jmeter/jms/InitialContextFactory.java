package com.elega9t.jmeter.jms;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.*;
import java.util.Enumeration;
import java.util.Hashtable;

//sh jmeter.sh -Djava.library.path=/opt/mqm/java/lib64/ -Dcom.ibm.mq.cfg.jmqi.libpath=/opt/mqm/java/lib64
public class InitialContextFactory implements javax.naming.spi.InitialContextFactory {

    private static final String CONNECTION_FACTORY_NAME = "CONNECTION_FACTORY";
    private static final String QUEUE_NAME_PREFIX = "QUEUE_";

    private static final String CHANNEL_PARAMETER = "channel";
    private static final String HOST_NAME_PARAMETER = "hostName";
    private static final String PORT_PARAMETER = "port";
    private static final String QUEUE_MANAGER_PARAMETER = "queueManager";
    private static final String TRANSPORT_TYPE_PARAMETER = "transportType";

    private CachingConnectionFactory queueConnectionFactory = null;
    private QueueConnection queueConnection = null;

    @Override
    public Context getInitialContext(final Hashtable<?, ?> environment) throws NamingException {
        return new Context() {

            @Override
            public Object lookup(Name name) throws NamingException {
                Enumeration<String> parts = name.getAll();
                StringBuilder nameBuilder = new StringBuilder();
                while(parts.hasMoreElements()) {
                    nameBuilder.append(parts.nextElement());
                    if(parts.hasMoreElements()) {
                        nameBuilder.append(";");
                    }
                }
                return lookup(nameBuilder.toString());
            }

            @Override
            public Object lookup(String name) throws NamingException {
                try {
                    if(name.equals(CONNECTION_FACTORY_NAME)) {
                        if(queueConnectionFactory == null) {
                            MQQueueConnectionFactory mqQueueConnectionFactory = new com.ibm.mq.jms.MQQueueConnectionFactory();
                            mqQueueConnectionFactory.setChannel(environment.get(CHANNEL_PARAMETER).toString());
                            mqQueueConnectionFactory.setHostName(environment.get(HOST_NAME_PARAMETER).toString());
                            mqQueueConnectionFactory.setPort(Integer.parseInt(environment.get(PORT_PARAMETER).toString()));
                            mqQueueConnectionFactory.setQueueManager(environment.get(QUEUE_MANAGER_PARAMETER).toString());
                            mqQueueConnectionFactory.setTransportType(Integer.parseInt(environment.get(TRANSPORT_TYPE_PARAMETER).toString()));

                            queueConnectionFactory = new CachingConnectionFactory(mqQueueConnectionFactory);

                            queueConnection = queueConnectionFactory.createQueueConnection();
                        }
                        return queueConnectionFactory;
                    } else if(name.startsWith(QUEUE_NAME_PREFIX)) {
                        QueueSession session = null;
                        try {
                            session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                            return session.createQueue(name.substring(QUEUE_NAME_PREFIX.length()));
                        } finally {
                            if(session != null) {
                                session.close();
                            }
                        }
                    } else {
                        throw new NamingException("Invalid name. Should be \'" + CONNECTION_FACTORY_NAME + "\' or should start with \'" + QUEUE_NAME_PREFIX + "\'");
                    }
                } catch (JMSException ex) {
                    throw new NamingException(ex.getMessage());
                }
            }

            @Override
            public void bind(Name name, Object obj) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void bind(String name, Object obj) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void rebind(Name name, Object obj) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void rebind(String name, Object obj) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void unbind(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void unbind(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void rename(Name oldName, Name newName) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void rename(String oldName, String newName) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void destroySubcontext(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void destroySubcontext(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Context createSubcontext(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Context createSubcontext(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object lookupLink(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object lookupLink(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public NameParser getNameParser(Name name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public NameParser getNameParser(String name) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Name composeName(Name name, Name prefix) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String composeName(String name, String prefix) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object addToEnvironment(String propName, Object propVal) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object removeFromEnvironment(String propName) throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Hashtable<?, ?> getEnvironment() throws NamingException {
                return System.getProperties();
            }

            @Override
            public void close() throws NamingException {
            }

            @Override
            public String getNameInNamespace() throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };
    }

}
