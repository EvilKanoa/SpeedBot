package ca.kanoa.nxt.speedbot;

import lejos.nxt.Button;

public class EscapeListener extends Thread {
	
	public EscapeListener() {
		this.setDaemon(true);
	}
	
	@Override
	public void run() {
		while (Button.ESCAPE.isUp()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		System.exit(0);
	}
	
}
