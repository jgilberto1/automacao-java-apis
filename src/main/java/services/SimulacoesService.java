package services;

import com.github.javafaker.Faker;
import constants.EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import support.GeraCpfCnpj;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static support.context.Context.getScenario;
import static support.context.Context.rest;


public class SimulacoesService implements EndPoints {

    GeraCpfCnpj gerador = new GeraCpfCnpj();
    Faker faker = new Faker();

    String nome = null;
    String cpf = null;
    String email = null;
    Integer valor = 0;
    Integer parcelas = 0;

    Boolean seguro = null;

    Integer id = 0;

    Integer status = 0;

    String erros = null;

    String cpf_cadastrado = null;

    Integer id_simulacao = 0;

    String nomeAlterado = null;
    String cpfAlterado = null;

    Integer parcelaAlterada = 0;

    String emailAlterado = null;

    Boolean seguroAlterado = null;

    Integer valorAlterado = 0;

    public void getSimulacaoCpf() {
        postSimulacaoSucesso();
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(SIMULACOES + cpf));
    }

    public void getSimulacaoCpfInexistente() {
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(SIMULACOES + cpf));
    }

    public void postSimulacaoSucesso() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nome", this.nome = faker.name().fullName());
        requestBody.put("cpf", this.cpf = gerador.cpf(false));
        requestBody.put("email", this.email = faker.internet().emailAddress());
        requestBody.put("valor", this.valor = faker.number().numberBetween(1000, 4000));
        requestBody.put("parcelas", this.parcelas = faker.number().numberBetween(2, 48));
        requestBody.put("seguro", this.seguro = true);

        rest().newRequest();
        rest().setResponse(rest().body(requestBody).contentType("application/json").post(SIMULACOES));

        Response response = rest().getResponse();
        JsonPath jsonPath = response.jsonPath();
        this.id = jsonPath.get("id");
        this.cpf_cadastrado = jsonPath.get("cpf");
    }

    public void verifySimulacaoCpf() {
        String cenario = getScenario().getName();
        switch (cenario) {
            case "CT004 - Simulações - consultar simulação através do cpf":
                this.status = HttpStatus.SC_OK;
                break;
            case "CT006 - Simulações - inserção de uma simulação valida":
                this.status = HttpStatus.SC_CREATED;
                break;
            default:
                this.status = HttpStatus.SC_OK;
        }

        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(status)
                .body("id", Matchers.equalTo(id))
                .body("nome", containsString(nome))
                .body("cpf", containsString(cpf))
                .body("email", containsString(email))
                .body("valor", Matchers.equalTo(valor.floatValue()))
                .body("parcelas", Matchers.equalTo(parcelas))
                .body("seguro", Matchers.equalTo(true));
    }

    public void verifySimulacaoCpfInexistente() {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", containsString("Simulação não encontrada"));
    }

    public void postSimulacaoInvalida(String nome, String cpf, String email, String valor, String parcelas, String seguro) {

        switch (cpf) {
            case "ja cadastrado":
                postSimulacaoSucesso();
                cpf = cpf_cadastrado;
                break;
            case "nao cadastrado":
                cpf = gerador.cpf(false);
                break;
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nome", nome);
        requestBody.put("cpf", cpf);
        requestBody.put("email", email);
        requestBody.put("valor", valor);
        requestBody.put("parcelas", parcelas);
        requestBody.put("seguro", seguro);

        rest().newRequest();
        rest().setResponse(rest().body(requestBody).contentType("application/json").post(SIMULACOES));
    }


    public void delSimulacao(String tipo) {
        postSimulacaoSucesso();
        if (tipo.equals("valida")) {
            id_simulacao = this.id;
        } else {
            id_simulacao = 10000;
        }
        rest().newRequest();
        rest().setResponse(rest().getRequest().delete(SIMULACOES + id_simulacao));
    }

    public void verifySimulacaoInexistente(String erro) {

        if (erro.equals("CPF duplicado")) {
            this.status = HttpStatus.SC_CONFLICT;
        } else this.status = HttpStatus.SC_BAD_REQUEST;

        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(status);

        ResponseBody body = rest().getResponse().body();
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains(erro), true);
    }

    public void verifyDelSimulacao(String mensagem, String tipo) {

        if (tipo.equals("valida")) {
            this.status = HttpStatus.SC_NO_CONTENT;
        } else this.status = HttpStatus.SC_NOT_FOUND;

        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(status);

        ResponseBody body = rest().getResponse().body();
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains(mensagem), true);
    }


    public void putSimulacao() {
        postSimulacaoSucesso();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nome", this.nomeAlterado = faker.name().fullName());
        requestBody.put("cpf", this.cpfAlterado = gerador.cpf(false));
        requestBody.put("email", this.emailAlterado = faker.internet().emailAddress());
        requestBody.put("valor", this.valorAlterado = faker.number().numberBetween(1000, 4000));
        requestBody.put("parcelas", this.parcelaAlterada = faker.number().numberBetween(2, 48));
        requestBody.put("seguro", this.seguroAlterado = false);

        rest().newRequest();
        rest().setResponse(rest().body(requestBody).contentType("application/json").put(SIMULACOES + cpf_cadastrado));

        Response response = rest().getResponse();
        JsonPath jsonPath = response.jsonPath();
        this.cpfAlterado = jsonPath.get("cpf");
    }

    public void verifyPutSimulacaoCpfInexistente() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nome", this.nome = faker.name().fullName());
        requestBody.put("cpf", this.cpf = gerador.cpf(false));
        requestBody.put("email", this.email = faker.internet().emailAddress());
        requestBody.put("valor", this.valor = faker.number().numberBetween(1000, 4000));
        requestBody.put("parcelas", this.parcelas = faker.number().numberBetween(2, 48));
        requestBody.put("seguro", false);
        rest().newRequest();
        rest().setResponse(rest().body(requestBody).contentType("application/json").put(SIMULACOES + cpf_cadastrado));

        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", containsString("CPF não encontrado"));
    }

    public void verifyPutSimulacaoValoresAlterados() {
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(SIMULACOES + cpfAlterado));

        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.equalTo(id))
                .body("nome", containsString(nomeAlterado))
                .body("cpf", containsString(cpfAlterado))
                .body("email", containsString(emailAlterado))
                .body("valor", Matchers.equalTo(valorAlterado))
                .body("parcelas", not(Matchers.equalTo(parcelaAlterada)))
                .body("seguro", Matchers.equalTo(false));
    }


    public void getTodasSimulacoes() {
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(SIMULACOES));

        Response response = rest().getResponse();
        JsonPath jsonPath = response.jsonPath();
        this.id = jsonPath.get("id[0]");
    }


    public void delTodasSimulacoes() {
        getTodasSimulacoes();
        while (id != null) {
            rest().newRequest();
            rest().setResponse(rest().getRequest().delete(SIMULACOES + id));
            getTodasSimulacoes();
        }
    }

    public void verifyTodasSimulacoesNoContent() {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public void verifyTodasSimulacoesSucesso() {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", hasItem(id))
                .body("nome", hasItem(containsString(nome)))
                .body("cpf", hasItem(containsString(cpf)))
                .body("email", hasItem(containsString(email)))
                .body("valor", hasItem(valor))
                .body("parcelas", hasItem(parcelas))
                .body("seguro", hasItem(true));
    }

}
