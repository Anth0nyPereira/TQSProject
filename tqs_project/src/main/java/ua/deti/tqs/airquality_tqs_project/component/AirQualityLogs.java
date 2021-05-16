package ua.deti.tqs.airquality_tqs_project.component;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Component
public class AirQualityLogs {
    private boolean append;
    private FileHandler handler;
    private Logger logger;
    private Date date;

    public AirQualityLogs() throws IOException {
        this.append = true;
        this.date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        this.handler = new FileHandler("airQualityLog-" + strDate + ".log", append);
        logger = Logger.getLogger("ua.deti.airquality_tqs_project");
        logger.addHandler(handler);
    }

    public void addLog(String message) {
        logger.info(message);
    }
}
