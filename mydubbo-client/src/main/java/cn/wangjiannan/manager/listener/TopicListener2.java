package cn.wangjiannan.manager.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicListener2 implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(TopicListener2.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				logger.info("TopicListener2收到消息:{}", textMessage.getText());
			} catch (JMSException e) {
				logger.error("", e);
			}
		}

	}
}
