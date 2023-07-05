package support.context.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:conf/${env}.properties",
        "classpath:allure.properties",
        "classpath:rest.properties"})
public interface Configuration extends Config {

    @Key("url.base")
    String urlBase();

    @Key("faker.locale")
    String faker();

    @Key("allure.server.host")
    String allureHost();

    @Key("allure.server.port")
    String allurePort();

    @Key("allure.server.project")
    String allureProject();

    @Key("allure.server.force_project_creation")
    String allureForceProjectCreation();

    @Key("allure.server.enable")
    Boolean allureEnable();

    @Key("rest.proxy.enable")
    Boolean restProxyEnabled();

    @Key("rest.proxy.host")
    String restProxyHost();

    @Key("rest.proxy.port")
    int restProxyPort();

}
