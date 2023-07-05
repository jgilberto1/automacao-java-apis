package support.context.report;


import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static support.context.Context.config;

@Log4j2
public final class Report {

    private final String DELIMITADOR_START = "==================================== Log Start =====================================\n";
    private final String DELIMITADOR_END = "==================================== Log End =====================================\n";

    public void setEnv(){
        new File(System.getProperty("allure.results.directory")).mkdirs();
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Environment", config().getEnv())
                        .put("URL", config().getPropsEnv().urlBase())
                        .put("Proxy RestAssured Enabled", config().getPropsEnv().restProxyEnabled().toString())
                        .put("Allure Server Enabled", config().getPropsEnv().allureEnable().toString())
                        .put("System Operation", System.getProperty("os.name"))
                        .build(), System.getProperty("allure.results.directory")+"/");
    }

    public void setText(String value) {
        log.info(DELIMITADOR_START+value);
        Allure.addAttachment("Log","", value);
        log.info(DELIMITADOR_END);
    }

}
