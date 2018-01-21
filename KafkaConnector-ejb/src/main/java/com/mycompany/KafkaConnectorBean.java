/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.mycompany.kafkaconnector.api.KafkaCallback;
import com.mycompany.kafkaconnector.api.KafkaConnector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author martin
 */
@Singleton
@Startup
public class KafkaConnectorBean implements KafkaConnector {

    private Map<String, KafkaCallback> topics = new HashMap<String, KafkaCallback>();

    @PostConstruct
    public void init() {
        System.out.println("Init Kafka Onnnector");
    }

    @Override
    public void registerConsumer(final String callbackBeanName, final String topic) {
        try {
            InitialContext ic = new InitialContext();
            KafkaCallback lookup = (KafkaCallback) ic.lookup(callbackBeanName);
            topics.put(topic, lookup);
        } catch (NamingException ex) {
            Logger.getLogger(KafkaConnectorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Schedule(second = "*/15", minute = "*", hour = "*", persistent = false)
    public void lol() {
        for (String topic : topics.keySet()) {
            System.out.println(topic);
            topics.get(topic).kafkaMessageArived();

        }

    }

}
