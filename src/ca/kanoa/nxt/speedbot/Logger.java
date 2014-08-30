package ca.kanoa.nxt.speedbot;

import java.util.ArrayList;
import java.util.List;

import lejos.util.Stopwatch;

public class Logger {

	public static enum Level {

		DEBUG(0),
		INFO(1),
		WARNING(2),
		ERROR(3),
		SEVERE(4);
		
		private int level;
		
		Level(int level) {
			this.level = level;
		}
		
		@Override
		public String toString() {
			return "[" + super.toString().toUpperCase().toCharArray()[0] + "]";
		}

		public int getLevel() {
			return level;
		}
		
	}
	
	private Stopwatch watch;
	private Level displayLevel;
	private boolean logMilis;
	private List<Float> log;

	/**
	 * Creates a new logger
	 * @param displayLevel The level that a message requires to be displayed. All messages will be logged.
	 * @param logMilis If milliseconds should be included in the timestamp.
	 */
	public Logger(Level displayLevel, boolean logMilis) {
		watch = new Stopwatch();
		watch.reset();
		this.displayLevel = displayLevel;
		this.logMilis = logMilis;
		log = new ArrayList<Float>();
	}

	private void log(String message, Level level) {
		String toLog = level.toString() + formatTime(watch.elapsed()) + message;
		Float[] fa = serialize(message, level);
		for (Float f : fa) {
			log.add(f);
		}
		if (level.getLevel() >= displayLevel.getLevel()) {
			System.out.println(toLog);
		}
	}

	private String formatTime(int time) {
		int minutes = (int) Math.floor(time / 60000);
		int seconds = (int) Math.floor((time - (minutes * 60000)) / 1000);
		int milis = (time - (minutes * 60000) - (seconds * 1000));
		return "[" + minutes + ":" + seconds + (logMilis ? ":" + milis : "") + "]";
	}
	
	private Float[] serialize(String msg, Level level) {
		List<Float> floats = new ArrayList<Float>();
		msg = level.toString() + msg;
		for (char c : msg.toCharArray()) {
			floats.add(new Float((int) c));
		}
		floats.add(new Float(-1));
		return floats.toArray(new Float[0]);
	}
	
	@SuppressWarnings("unused")
	private String deserialize(Float[] floats) {
		StringBuilder builder = new StringBuilder();
		for (Float f : floats) {
			if (((float) f) < 0) {
				builder.append('\n');
			} else {
				builder.append((char) ((float) f));
			}
		}
		return builder.toString();
	}

	public void debug(String msg) {
		log(msg, Level.DEBUG);
	}

	public void info(String msg) {
		log(msg, Level.INFO);
	}

	public void warning(String msg) {
		log(msg, Level.WARNING);
	}

	public void error(String msg) {
		log(msg, Level.ERROR);
	}

	public void severe(String msg) {
		log(msg, Level.SEVERE);
	}

	public Level getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(Level displayLevel) {
		this.displayLevel = displayLevel;
	}

}
