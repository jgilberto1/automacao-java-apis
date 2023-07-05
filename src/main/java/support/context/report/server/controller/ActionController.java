package support.context.report.server.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static support.context.Context.*;


@Log4j2
public class ActionController {

    private RequestSpecification request;

    public ActionController() {
        request = RestAssured.given()
                .contentType(ContentType.MULTIPART)
                .accept("*/*");
    }

    private File getTargetFolder(){
         return new File("./target/allure-results");
    }

    public void sendResults(String projectId, String forceProjectCreation) {
        Response response = null;
        try{
            if (config().getPropsEnv().allureEnable()){
                final String URL = String.format("%s:%s/allure-docker-service/send-results?project_id=%s&force_project_creation=%s",
                        config().getPropsEnv().allureHost(),config().getPropsEnv().allurePort(),
                        URLEncoder.encode(config().getPropsEnv().allureProject()+"-"+config().getEnv(),StandardCharsets.UTF_8.name()),
                        config().getPropsEnv().allureForceProjectCreation()).toLowerCase();
                log.info(URL);
                response = getFileMultPart(getTargetFolder()).post(URL);
                if (response.getStatusCode() != 200) {
                    log.error("Erro ao gerar report!");
                    log.error("Status Code: " +response.getStatusCode());
                    log.error("Error: "+response.getBody().prettyPrint());
                }else {
                    log.info(String.format("Results successfully sent for project_id '%s'",config().getPropsEnv().allureProject()));
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void sendResults() {
        Response response = null;
        try{
            if (config().getPropsEnv().allureEnable()){
                final String URL = String.format("%s:%s/allure-docker-service/send-results?project_id=%s&force_project_creation=%s",
                        config().getPropsEnv().allureHost(),config().getPropsEnv().allurePort(),
                        URLEncoder.encode(config().getPropsEnv().allureProject()+"-"+config().getEnv(),StandardCharsets.UTF_8.name()),
                        config().getPropsEnv().allureForceProjectCreation()).toLowerCase();;
                log.info(URL);
                response = getFileMultPart(getTargetFolder()).post(URL);
                if (response.getStatusCode() != 200) {
                    log.error("Erro ao gerar report!");
                    log.error("Status Code: " +response.getStatusCode());
                    log.error("Error: "+response.getBody().prettyPrint());
                }else {
                    log.info(String.format("Results successfully sent for project_id '%s'",config().getPropsEnv().allureProject()));
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public RequestSpecification getFileMultPart(final File folder) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFileMultPart(fileEntry);
            } else {
                request.multiPart("files[]",new File(fileEntry.getAbsolutePath()));
            }
        }
        return request;
    }
}
