package controller;

import java.util.LinkedList;

import model.Outlet;
import model.QueueMessage;

public class OutletCommunicationController {
	private Outlet outlet;
	private LinkedList<QueueMessage> buffer;
	
	public OutletCommunicationController(Outlet o) {
		this.outlet = o;
		this.buffer = new LinkedList<QueueMessage>();
	}
	
	private QueueMessage generateQueueMessage() {
		return new QueueMessage(this.outlet.getId(), this.outlet.getAndResetPowerConsumed());
	}
	
	public void sendMessageToQueue() {
		QueueMessage to_send = generateQueueMessage();
		buffer.addLast(to_send);
		while (buffer.isEmpty() == false) {
			QueueMessage message = buffer.getFirst();
			buffer.removeFirst();
			try {
				Producer.SendToQueue(message);
			}
			catch (Exception e) {
				buffer.addFirst(message);
				break;	// If the RMQ server is not responding, it makes no sense to continue trying to connect to it
			}
		}
	}
}
