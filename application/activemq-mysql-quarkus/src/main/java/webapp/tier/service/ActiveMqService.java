package webapp.tier.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import webapp.tier.bean.MsgBean;
import webapp.tier.util.MsgBeanUtils;

@ApplicationScoped
public class ActiveMqService implements Runnable {

	@Inject
	@RestClient
	DeliverService deliversvc;

	@Inject
	ConnectionFactory connectionFactory;

	@ConfigProperty(name = "activemq.splitkey")
	String splitkey;
	@ConfigProperty(name = "activemq.topic.name")
	String topicname;

	private static final Logger LOG = Logger.getLogger(ActiveMqService.class.getSimpleName());
	private final ExecutorService scheduler = Executors.newSingleThreadExecutor();

	void onStart(@Observes StartupEvent ev) {
		scheduler.submit(this);
		LOG.info("The application is starting...");
	}

	void onStop(@Observes ShutdownEvent ev) {
		scheduler.shutdown();
		LOG.info("The application is stopping...");
	}

	@Override
	public void run() {
		MysqlService mysqlsvc = new MysqlService();
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
			JMSConsumer consumer = context.createConsumer(context.createTopic(topicname));
			while (true) {
				LOG.info("Ready for receive message...");
				Message message = consumer.receive();

				MsgBeanUtils msgbean = new MsgBeanUtils();
				TextMessage textMessage = (TextMessage) message;
				MsgBean bean = msgbean.splitBody(textMessage.getText(), splitkey);
				msgbean.setFullmsgWithType(bean, "Received");
				LOG.info(msgbean.getFullmsg());

				mysqlsvc.insertMsg(bean);
				LOG.info("Call: Random Publish");
				LOG.info(deliversvc.random());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
