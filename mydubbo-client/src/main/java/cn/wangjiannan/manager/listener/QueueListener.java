package cn.wangjiannan.manager.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueListener implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				logger.info("QueueListener收到消息:{}", textMessage.getText());
			} catch (JMSException e) {
				logger.error("", e);
			}
		}

	}

}
