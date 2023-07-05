package support.context.rest;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import support.context.rest.functions.IManagerRest;
import support.context.rest.exceptions.CoreException;

import java.util.List;
import java.util.Map;

import static support.context.Context.*;

@Log4j2
public class RestActions implements IManagerRest {

    private RequestSpecification request;
    private Response response;
    private RestFilter restFilter = new RestFilter();

    public RestActions() {}

    public void setBaseURI(String baseUrl){
        log.info("Set base URI on RestAssured as default");
        RestAssured.baseURI = baseUrl;
    }

    public void setBaseURI(){
        log.info("Set base URI on RestAssured as default from environment variables");
        RestAssured.baseURI = config().getPropsEnv().urlBase();;
    }

    public String getAllLogs(){
        log.info("Get All logs - Request and Response");
        return this.restFilter.getAlllogs();
    }

    public String getRequestLogs(){
        log.info("Get Request logs ");
        return this.restFilter.getRequestlogs();
    }

    public String getResponseLogs(){
        log.info("Get Response logs ");
        return this.restFilter.getResponselogs();
    }

    public RequestSpecification getRequest(){
        log.info("Get Request Object");
        return this.request;
    }

    public void newRequest(){
        log.info("Create main RestAssured Object");
        this.request = RestAssured.given().filter(this.restFilter);
        if(config().getPropsEnv().restProxyEnabled()){
            log.info("Set Proxy on RestAssured");
            this.request.proxy(config().getPropsEnv().restProxyHost(), config().getPropsEnv().restProxyPort());
        }
    }

    public void setResponse(Response response){
        log.info("Set Response Object");
        this.response = response;
    }

    public Response getResponse(){
        log.info("Get Response Object");
        return this.response;
    }

    public RequestSpecification header(Header header){
        return this.request.header(header);
    }

    public RequestSpecification header(String headerName, Object obj, Object... addObj){
        return this.request.header(headerName,obj,addObj);
    }

    public RequestSpecification body(Map<String, Object> body){
        return this.request.body(body);
    }

    public RequestSpecification body(String property, String value){
        return this.request.body(this.getJson(property,value).toString());
    }

    public RequestSpecification body(String property, String value, String... add){
        return this.request.body(this.getJson(property,value,add).toString());
    }

    public JsonObject getJson(String property, String value){
        return this.getJson(property,value);
    }


    public JsonObject getJson(String property, String value, String... add){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(property,value);

        try{
            for (int i = 0; i < add.length; i += 2) {
                jsonObject.addProperty(add[i], add[i + 1]);
            }

            return jsonObject;
        }catch (ArrayIndexOutOfBoundsException e){
              throw new CoreException("invalid number of arguments from 'properties' and 'values'" + e);
        }
    }

    public String getResponseValue(String parameter){
        return JsonPath.from(this.response.asString()).get(parameter);
    }

    public List<Map<String,String>> getResponseValues(String parameter){
        return JsonPath.from(this.response.asString()).get(parameter);
    }

    public String getResponseValue(Response response, String parameter){
        return JsonPath.from(response.asString()).get(parameter);
    }

    public List<Map<String,String>> getResponseValues(Response response, String parameter){
        return JsonPath.from(response.asString()).get(parameter);
    }

    public XmlPath getResponseXmlPath(){
        return XmlPath.from(this.response.asString());
    }

    public XmlPath getResponseXmlPath(Response response){
        return XmlPath.from(response.asString());
    }

    public XmlPath getResponseHtmlPath(){
        return this.response.htmlPath();
    }

    public XmlPath getResponseHtmlPath(Response response){
        return response.htmlPath();
    }
}
