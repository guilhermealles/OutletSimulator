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
		
		QueueMessage to_send = generateQueueMessage();
		try {
			Producer.SendToQueue(to_send);
		}
		catch (Exception e) {
			// If it is not possible to send the message right now, add it to the buffer to send it later
			buffer.addLast(to_send);
		}
	}
}
