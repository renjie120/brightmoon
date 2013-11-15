package brightmoon.jms;

import javax.jms.ConnectionFactory;

public class Sender {
	public static void main(String[] args) {
		String jmsProviderAddress = "tcp://localhost:61616"; 
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				jmsProviderAddress);
	}
}
