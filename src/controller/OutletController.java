package controller;

import java.util.Timer;

import model.Outlet;

public class OutletController {
	private Outlet outlet;
	
	public OutletController() {	}
	
	public void initializeOutlet(int outlet_id, int power_consumption_per_minute) {
		this.outlet = new Outlet(outlet_id, power_consumption_per_minute);
		
		// Create a new timer thread to update the power consumption
		ConsumptionUpdater power_consumption_updater = new ConsumptionUpdater(this.outlet);
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(power_consumption_updater, Outlet.REFRESH_INTERVAL, Outlet.REFRESH_INTERVAL);
	}
	
	public int getOutletId() {
		return outlet.getId();
	}
	
	public double getPowerConsumed() {
		return this.outlet.getPowerConsumed();
	}
	
	public double getConsumptionPerMinute() {
		return this.outlet.getConsumptionPerMinute();
	}
	
	public void setPowerConsumptionPerMinute(double new_value) {
		this.outlet.setPowerConsumptionPerMinute(new_value);
	}
}
