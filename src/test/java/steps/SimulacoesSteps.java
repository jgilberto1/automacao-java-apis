package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import services.SimulacoesService;

public class SimulacoesSteps {
    private SimulacoesService simulacoesService;

    public SimulacoesSteps() {
        simulacoesService = new SimulacoesService();
    }

    @Quando("eu consultar uma simulação através do cpf")
    public void euConsultarUmaSimulaçãoAtravésDoCpf() {
        simulacoesService.getSimulacaoCpf();
    }

    @Entao("sera apresentado a simulação realizada para o cpf solicitado")
    public void seraApresentadoASimulaçãoRealizadaParaOCpfSolicitado() {
        simulacoesService.verifySimulacaoCpf();
    }

    @Quando("eu consultar uma simulação através do cpf que não possui simulação")
    public void euConsultarUmaSimulaçãoAtravésDoCpfQueNãoPossuiSimulação() {
        simulacoesService.getSimulacaoCpfInexistente();
    }

    @Entao("sera apresentada a mensagem que o cpf não foi encontrado")
    public void seraApresentadaAMensagemQueOCpfNãoFoiEncontrado() {
        simulacoesService.verifySimulacaoCpfInexistente();
    }

    @Quando("criar uma nova simulação válida")
    public void criarUmaNovaSimulaçãoVálida() {
        simulacoesService.postSimulacaoSucesso();
    }

    @Entao("será informado que a simulação foi criada e retornará o body na response")
    public void seráInformadoQueASimulaçãoFoiCriadaERetornaráOBodyNaResponse() {
        simulacoesService.verifySimulacaoCpf();
    }

    @Quando("criar uma nova simulação com os campos nome {string}, cpf {string}, email {string}, valor {string}, parcelas {string} e seguro {string} invalidos ou vazios")
    public void criarUmaNovaSimulaçãoComOsCamposNomeCpfEmailValorParcelasESeguroInvalidosOuVazios(String nome, String cpf, String email, String valor, String parcelas, String seguro) {
        simulacoesService.postSimulacaoInvalida(nome, cpf, email, valor, parcelas, seguro);
    }

    @Entao("retornará bad request informando os erros {string} que está ocorrendo em cada requisição")
    public void retornaráBadRequestInformandoOsErrosQueEstáOcorrendoEmCadaRequisição(String erro) {
        simulacoesService.verifySimulacaoInexistente(erro);
    }

    @Quando("remover uma nova simulação do tipo {string}")
    public void removerUmaNovaSimulaçãoDoTipo(String tipo) {
        simulacoesService.delSimulacao(tipo);
    }

    @Entao("será informado o retorno {string} da remoção de acordo com o {string}")
    public void seráInformadoORetornoDaRemoçãoDeAcordoComO(String mensagem, String tipo) {
        simulacoesService.verifyDelSimulacao(mensagem, tipo);
    }

    @Dado("que eu atualize uma nova simulação válida")
    public void queEuAtualizeUmaNovaSimulaçãoVálida() {
        simulacoesService.putSimulacao();
    }

    @Quando("a simulação foi atualizada retornando o body na response informando que o cpf não possui mais simulações")
    public void aSimulaçãoFoiAtualizadaRetornandoOBodyNaResponseInformandoQueOCpfNãoPossuiMaisSimulações() {
        simulacoesService.verifyPutSimulacaoCpfInexistente();
    }

    @Então("validarei que os demais campos também foram alterados")
    public void validareiQueOsDemaisCamposTambémForamAlterados() {
        simulacoesService.verifyPutSimulacaoValoresAlterados();
    }

    @Dado("que eu remova todas simulações realizadas")
    public void queEuRemovaTodasSimulaçõesRealizadas() {
        simulacoesService.delTodasSimulacoes();
    }

    @Quando("consultar todas as simulações cadastradas")
    public void consultarTodasAsSimulaçõesCadastradas() {
        simulacoesService.getTodasSimulacoes();
    }

    @Então("validarei que não possuem simulações cadastradas retornando no content")
    public void validareiQueNãoPossuemSimulaçõesCadastradasRetornandoNoContent() {
        simulacoesService.verifyTodasSimulacoesNoContent();
    }

    @Então("validarei as simulações cadastradas")
    public void validareiAsSimulaçõesCadastradas() {
        simulacoesService.verifyTodasSimulacoesSucesso();
    }
}
