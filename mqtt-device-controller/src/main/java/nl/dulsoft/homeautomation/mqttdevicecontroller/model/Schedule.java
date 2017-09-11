package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class Schedule {

    private String cron;
    private String action;

    public String getCron() {
        return cron;
    }

    public Schedule setCron(String cron) {
        this.cron = cron;
        return this;
    }

    public String getAction() {
        return action;
    }

    public Schedule setAction(String action) {
        this.action = action;
        return this;
    }
}
