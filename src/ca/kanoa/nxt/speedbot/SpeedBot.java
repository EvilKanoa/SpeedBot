package ca.kanoa.nxt.speedbot;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import ca.kanoa.nxt.speedbot.Logger.Level;

public class SpeedBot {

	private static SpeedBot instance;
	
	EscapeListener escapeListener;
	Logger logger;
	
	//Sensors
	TouchSensor sTouch1, sTouch2;
	ColorSensor sColor;
	UltrasonicSensor sSonar;
	
	public SpeedBot() {
		escapeListener = new EscapeListener();
		logger = new Logger(Level.DEBUG, false);
	}
	
	private void setup() {
		escapeListener.start();
		
		sTouch1 = new TouchSensor(SensorPort.S1);
		sTouch2 = new TouchSensor(SensorPort.S2);
		sColor = new ColorSensor(SensorPort.S3);
		sSonar = new UltrasonicSensor(SensorPort.S4);
	}
	
	/**
	 * Runs until the escape button is pressed or returns false.
	 * @return If the program should continue. False will end the program.
	 */
	private boolean loop() {
		//Testing the build stuffs
		if (sTouch1.isPressed()) {
			Motor.A.forward();
		} else {
			Motor.A.stop();
		}
		return true;
	}
	
	public static void main(String[] args) {
		instance = new SpeedBot();
		instance.setup();
		while (instance.loop());
	}
	
	public static Logger getLogger() {
		return instance.logger;
	}
	
}
