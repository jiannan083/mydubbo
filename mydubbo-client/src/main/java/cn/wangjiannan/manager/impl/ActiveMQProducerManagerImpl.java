package cn.wangjiannan.manager.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import cn.wangjiannan.manager.ActiveMQProducerManager;

@Component
public class ActiveMQProducerManagerImpl implements ActiveMQProducerManager {

	@Autowired
	private JmsTemplate queueTemplate;

	@Autowired
	private JmsTemplate topicTemplate;

	@Override
	public void testQueueTemplate(String message) {
		queueTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});
	}

	@Override
	public void testTopicTemplate(String message) {
		topicTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});
	}
}
