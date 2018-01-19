package cn.wangjiannan.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveMQConsumer {
	private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumer.class);
	//  defualt user & password both are null
	// 默认连接用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	// 默认连接密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	// 默认连接地址
	//  DEFAULT_BROKER_URL =failover://tcp://localhost:61616
	// private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final String BROKEURL = "failover://tcp://www.wangjiannan.cn:61616";

	public static void main(String[] args) {
		Connection connection = null;

		// 1.初始化连接工厂
		ConnectionFactory contectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		try {
			// 2.通过连接工厂获取连接并启动连接
			connection = contectionFactory.createConnection();
			connection.start();
			// 3.创建会话session
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 4.创建一个名称为HelloWorld的消息队列
			Destination destination = session.createQueue("HelloWorld");
			// 5.创建消息消费者
			MessageConsumer messageConsumer = session.createConsumer(destination);
			TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);// (long timeout)
			if (textMessage != null) {
				System.out.println("收到的消息:" + textMessage.getText());
			}
		} catch (JMSException e) {
			logger.error("", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					logger.error("", e);
				}
			}
		}
	}
}
