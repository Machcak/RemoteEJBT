/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;


import com.mycompany.kafkaconnector.api.KafkaConnector;
import com.mycompany.kafkaconnector.api.KafkaCallback;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author martin
 */
@Startup 
@Singleton(name = "SecondSssionBean")
public class SecondSssionBean implements KafkaCallback {

    @PostConstruct
    public void businessMethod() {
        try {
            InitialContext ic = new InitialContext();
            KafkaConnector lookup = (KafkaConnector) ic.lookup("com.mycompany.kafkaconnector.api.KafkaConnector");
            lookup.registerConsumer("com.mycompany.kafkaconnector.api.KafkaCallback", "Topic" + this.getClass().getName());
        } catch (NamingException ex) {
            Logger.getLogger(SecondSssionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

//    @Override
//    public void kafkaMessageArived() {
//        System.out.println("Kafka message arrived");
//    }

    @Override
    public void kafkaMessageArived() {
        System.out.println("Message arrived"+ this.getClass().getName());
    }

}
