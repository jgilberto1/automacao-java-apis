package services;

import constants.EndPoints;
import org.apache.http.HttpStatus;
import support.GeraCpfCnpj;

import static org.hamcrest.CoreMatchers.containsString;
import static support.context.Context.rest;

public class RestricoesService implements EndPoints {
    GeraCpfCnpj gerador = new GeraCpfCnpj();

    public void getComRestricao(String cpf) {
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(RESTRICOES + cpf));
    }

    public void getSemRestricao(String cpf) {
        cpf = gerador.cpf(false);
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(RESTRICOES + cpf));
    }

    public void getCpfVazio() {
        String cpf = "";
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(RESTRICOES + cpf));
    }

    public void verifyRestricao(String cpf) {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("mensagem", containsString("O CPF " + cpf + " possui restrição"));
    }

    public void verifySemRestricao() {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public void verifyRegistroNaoEncontrado() {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
