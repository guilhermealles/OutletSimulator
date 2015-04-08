package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.QueueMessage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	private static String QUEUE_NAME = "OUTLET_INFO_MESSAGES";
	private static String HOST = "192.168.0.106";
	
	public static void SendToQueue(QueueMessage message) throws IOException {
		// Serialize the QueueMessage
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(baos);
		os.writeObject(message);
		byte[] messageBytes = baos.toByteArray();
		
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.basicPublish("", QUEUE_NAME, null, messageBytes);
		
		channel.close();
		connection.close();
		
		System.out.println("New message sent to queue: " + message.getOutletId() + ", " + message.getPowerConsumption());
	}
}
