package support.context.config;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import support.context.config.functions.IDatePicker;

import java.util.Locale;

public class Config implements IDatePicker {
    private String env;
    private Configuration propsEnv;
    private Faker faker;

    public Config() {
        this.setEnv(System.getProperty("env"));
        this.setPropsEnv(ConfigCache.getOrCreate(Configuration.class));
        ConfigFactory.setProperty("env", this.getEnv());
        this.faker = new Faker(new Locale(getPropsEnv().faker()));
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Configuration getPropsEnv() {
        return propsEnv;
    }

    public void setPropsEnv(Configuration propsEnv) {
        this.propsEnv = propsEnv;
    }

    public Faker getFaker() {
        return faker;
    }
}
