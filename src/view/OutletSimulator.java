package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.OutletCommunicationController;
import controller.OutletController;

public class OutletSimulator {

	private JFrame frame;
	private JTextField new_consumption_minute_text_field;
	private JTextField id_init_text_field;
	private JTextField consumption_minute_text_field;
	private static JLabel id_info_label;
	private static JLabel power_consumed_info_label;
	private static JLabel consumption_per_minute_info_label;
	
	private static OutletController outlet_controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutletSimulator window = new OutletSimulator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OutletSimulator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 360, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel outlet_id_label = new JLabel("Outlet ID: ");
		outlet_id_label.setBounds(20, 20, 80, 16);
		frame.getContentPane().add(outlet_id_label);
		
		JLabel power_consumed_label = new JLabel("Total power consumed: ");
		power_consumed_label.setBounds(20, 48, 163, 16);
		frame.getContentPane().add(power_consumed_label);
		
		JLabel consumption_per_minute_label = new JLabel("Consumpiton per minute: ");
		consumption_per_minute_label.setBounds(20, 76, 178, 16);
		frame.getContentPane().add(consumption_per_minute_label);
		
		id_info_label = new JLabel("0");
		id_info_label.setForeground(Color.LIGHT_GRAY);
		id_info_label.setBounds(250, 20, 61, 16);
		frame.getContentPane().add(id_info_label);
		
		power_consumed_info_label = new JLabel("0");
		power_consumed_info_label.setForeground(Color.LIGHT_GRAY);
		power_consumed_info_label.setBounds(250, 48, 61, 16);
		frame.getContentPane().add(power_consumed_info_label);
		
		consumption_per_minute_info_label = new JLabel("0");
		consumption_per_minute_info_label.setForeground(Color.LIGHT_GRAY);
		consumption_per_minute_info_label.setBounds(250, 76, 61, 16);
		frame.getContentPane().add(consumption_per_minute_info_label);
		
		new_consumption_minute_text_field = new JTextField();
		new_consumption_minute_text_field.setBounds(20, 126, 50, 28);
		frame.getContentPane().add(new_consumption_minute_text_field);
		new_consumption_minute_text_field.setColumns(10);
		
		JButton btnSetNewConsumption = new JButton("Set new Consumption");
		btnSetNewConsumption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outlet_controller.setPowerConsumptionPerMinute(Double.parseDouble(new_consumption_minute_text_field.getText()));
				updateFrameCounters();
			}
		});
		btnSetNewConsumption.setBounds(82, 127, 170, 29);
		frame.getContentPane().add(btnSetNewConsumption);
		
		id_init_text_field = new JTextField();
		id_init_text_field.setText("Outlet ID");
		id_init_text_field.setBounds(20, 211, 120, 28);
		frame.getContentPane().add(id_init_text_field);
		id_init_text_field.setColumns(10);
		
		consumption_minute_text_field = new JTextField();
		consumption_minute_text_field.setText("Consumption/m");
		consumption_minute_text_field.setBounds(20, 251, 120, 28);
		frame.getContentPane().add(consumption_minute_text_field);
		consumption_minute_text_field.setColumns(10);
		
		JButton btnInitializeOutlet = new JButton("Initialize Outlet");
		btnInitializeOutlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outlet_controller = new OutletController();
				outlet_controller.initializeOutlet(Integer.parseInt(id_init_text_field.getText()), Integer.parseInt(consumption_minute_text_field.getText()));
				updateFrameCounters();
				
				id_info_label.setForeground(Color.BLACK);
				power_consumed_info_label.setForeground(Color.BLACK);
				consumption_per_minute_info_label.setForeground(Color.BLACK);
			}
		});
		btnInitializeOutlet.setBounds(152, 212, 150, 29);
		frame.getContentPane().add(btnInitializeOutlet);
		
		JButton btnSendData = new JButton("Send Data");
		btnSendData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutletCommunicationController controller = new OutletCommunicationController(outlet_controller.getOutlet());
				controller.sendMessageToQueue();
			}
		});
		btnSendData.setBounds(237, 283, 117, 29);
		frame.getContentPane().add(btnSendData);
	}
	
	public static void updateFrameCounters() {
		id_info_label.setText(String.valueOf(outlet_controller.getOutletId()));
		power_consumed_info_label.setText(String.valueOf(outlet_controller.getPowerConsumed()));
		consumption_per_minute_info_label.setText(String.valueOf(outlet_controller.getConsumptionPerMinute()));
	}
}
