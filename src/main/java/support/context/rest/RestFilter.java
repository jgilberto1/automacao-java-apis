package support.context.rest;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.Iterator;
import java.util.List;

@Log4j2
public class RestFilter implements Filter {
    private String requestLog;
    private String reponseLog;

    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        this.setRequestLog(filterableRequestSpecification);
        this.setResponseLog(response);
        return response;
    }

    private void setRequestLog(FilterableRequestSpecification requestFilter) {
        this.requestLog = "\nRequest ===> \n"
                + requestFilter.getMethod()
                + " : "
                + requestFilter.getURI()
                + "\nHeaders ==> "
                + this.logHeaders(requestFilter.getHeaders().asList())
                + "\nBody ==> \n"
                + requestFilter.getBody();
    }

    private void setResponseLog(Response response) {
        this.reponseLog = "\nResponse ===> \n"
                + response.getStatusCode()
                + " : "
                + response.getStatusLine()
                + "\nHeaders ==> "
                + this.logHeaders(response.getHeaders().asList())
                + "\nBody ==> \n"
                + response.getBody().asString();
    }

    private String logHeaders(List<Header> headerList) {
        String header = "";
        Header h;
        for (Iterator<Header> headerIt = headerList.listIterator(); headerIt.hasNext(); header = header + h.getName() + " : " + h.getValue()) {
            h = (Header) headerIt.next();
        }
        return header;
    }

    public String getAlllogs(){
        return this.requestLog + this.reponseLog;
    }

    public String getRequestlogs(){
        return this.requestLog;
    }

    public String getResponselogs(){
        return this.reponseLog;
    }

}
