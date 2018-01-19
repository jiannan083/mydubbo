package cn.wangjiannan.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
 * 
 * 第二步：使用ConnectionFactory对象创建一个Connection对象。
 * 
 * 第三步：开启连接，调用Connection对象的start方法。
 * 
 * 第四步：使用Connection对象创建一个Session对象。
 * 
 * 第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
 * 
 * 第六步：使用Session对象创建一个Producer对象。
 * 
 * 第七步：创建一个Message对象，创建一个TextMessage对象。
 * 
 * 第八步：使用Producer对象发送消息。
 * 
 * 第九步：关闭资源。
 * 
 * @author wangjiannan
 * @date 2018年1月5日 下午2:33:08
 */
public class ActiveMQProducer {
	private static final Logger logger = LoggerFactory.getLogger(ActiveMQProducer.class);
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
			// 第一个参数：是否开启事务。true：开启事务，第二个参数忽略。  
			// 第二个参数：当第一个参数为false时，才有意义。消息的应答模式。1、自动应答2、手动应答。一般是自动应答。  
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 4.使用Session对象创建一个名称为HelloWorld的消息队列
			Destination destination = session.createQueue("HelloWorld");
			// 5.使用Session对象创建消息生产者
			MessageProducer messageProducer = session.createProducer(destination);
			// 6.创建一条文本消息
			TextMessage message = session.createTextMessage("ActiveMQ 发送消息04");
			// 7.通过消息生产者Producer对象发送消息
			messageProducer.send(message);
			// 8.提交消息,只有commit之后，消息才会进入队列
			session.commit();
		} catch (JMSException e) {
			logger.error("", e);
		} finally {
			if (connection != null) {
				try {
					// 9.关闭资源
					connection.close();
				} catch (JMSException e) {
					logger.error("", e);
				}
			}
		}
	}
}
