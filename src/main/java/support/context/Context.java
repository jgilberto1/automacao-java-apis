package support.context;

import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import support.context.config.Config;
import support.context.report.Report;
import support.context.rest.RestActions;

@Log4j2
public class Context {

    private static RestActions rest;
    private static Config config;
    private static Scenario scenario;
    private static Report report;

    public Context() {
        setup();
    }

    public void setup() {
        log.info("Initialize context");
        rest = new RestActions();
        report = new Report();
        config = new Config();
        scenario = getScenario();
        report.setEnv();
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void setScenario(Scenario scenario) {
        Context.scenario = scenario;
    }

    public static Config config() {
        return config;
    }

    public static Report report() {
        return report;
    }

    public static RestActions rest() {
        return rest;
    }
}
