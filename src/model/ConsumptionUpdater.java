package model;

import java.util.TimerTask;

public class ConsumptionUpdater extends TimerTask {

	private Outlet outlet;

	public ConsumptionUpdater(Outlet o) {
		this.outlet = o;
	}
	
	@Override
	public void run() {
		this.outlet.updatePowerConsumed();
	}

}
