package timer;

import java.io.File;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import util.Config;

@Singleton
@Startup
public class CleanerTimer implements TimedObject {

	// This class is a timer that executes every five seconds, it removes files older than timeLimit

	@Resource
	TimerService timerService;

	@PostConstruct
	public void initTimer() {
		if (timerService.getTimers() != null) {
			for (Timer timer : timerService.getTimers()) {
				timer.cancel();
			}
		}
		timerService.createCalendarTimer(new ScheduleExpression().hour("*").minute("*").second("*/5"),
				new TimerConfig("cleanerTimer", true));
	}

	@Override
	public void ejbTimeout(Timer timer) {
		Long timeLimit = 10L * 60L * 1000L; // 10 minutes
		Long now = System.currentTimeMillis();
		File folder = new File(Config.DATASETS_PATH.getValue());
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.lastModified() + timeLimit < now) {
				file.delete();
			}
		}
	}

}