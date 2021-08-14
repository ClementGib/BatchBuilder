package com.clement.gibert.batch.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@ApplicationScoped
public class Scheduler extends TimerTask {

	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@PostConstruct
	private void init() {
		logger.trace("CONSTRUCTION");
		Timer time = new Timer();
		time.schedule(this, 100, 10000);
	}
	
	@Override
	public void run() {
		logger.trace("RUN");
		
	}
	
}
