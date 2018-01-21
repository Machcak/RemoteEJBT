/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kafkaconnector.api;

import javax.ejb.Remote;

/**
 *
 * @author martin
 */
@Remote
public interface KafkaConnector {

    void registerConsumer(final String callbacnBeanName, final String topic);

}
