package steps;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import services.RestricoesService;

public class RestricoesSteps {
    private RestricoesService restricoesService;

    public RestricoesSteps() {
        restricoesService = new RestricoesService();
    }

    @Quando("eu consultar um usuario através do cpf {string} com restrição")
    public void euConsultarUmUsuarioAtravésDoCpfComRestrição(String cpf) {
        restricoesService.getComRestricao(cpf);
    }

    @Entao("sera apresentado que o cpf {string} em questão possui restrição")
    public void seraApresentadoQueOCpfEmQuestãoPossuiRestrição(String cpf) {
        restricoesService.verifyRestricao(cpf);
    }

    @Quando("eu consultar um usuario através do cpf {string} sem restrição")
    public void euConsultarUmUsuarioAtravésDoCpfSemRestrição(String cpf) {
        restricoesService.getSemRestricao(cpf);
    }

    @Entao("sera apresentado que o cpf em questão não possui restrição")
    public void seraApresentadoQueOCpfEmQuestãoNãoPossuiRestrição() {
        restricoesService.verifySemRestricao();
    }

    @Quando("eu consultar um usuario através de um cpf vazio")
    public void euConsultarUmUsuarioAtravésDeUmCpfVazio() {
        restricoesService.getCpfVazio();
    }

    @Entao("retornará que não foram encontrados registros")
    public void retornaráQueNãoForamEncontradosRegistros() {
        restricoesService.verifyRegistroNaoEncontrado();
    }

}
