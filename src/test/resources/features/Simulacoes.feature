# language: pt
# charset: UTF-8

@simulacaoCredito @simulacoes @build
Funcionalidade: Api Simulação de Crédito - Simulações
  Eu como cliente gostaria de consultar, inserir e excluir simulações

  @consultarSimulacaoPorCpf @CT004
  Cenario: CT004 - Simulações - consultar simulação através do cpf
    Quando eu consultar uma simulação através do cpf
    Entao sera apresentado a simulação realizada para o cpf solicitado

  @consultarSimulacaoPorCpfInexistente @CT005
  Cenario: CT005 - Simulações - consultar simulação através do cpf inexistente
    Quando eu consultar uma simulação através do cpf que não possui simulação
    Entao sera apresentada a mensagem que o cpf não foi encontrado

  @inserirSimulacao @CT006
  Cenario: CT006 - Simulações - inserção de uma simulação valida
    Quando criar uma nova simulação válida
    Entao será informado que a simulação foi criada e retornará o body na response

  @inserirSimulacaoFalha @CT007
  Esquema do Cenario: CT007 - Simulações - inserção de simulações validando campos obrigatórios "<erros>"
    Quando criar uma nova simulação com os campos nome "<nome>", cpf "<cpf>", email "<email>", valor "<valor>", parcelas "<parcelas>" e seguro "<seguro>" invalidos ou vazios
    Entao retornará bad request informando os erros "<erros>" que está ocorrendo em cada requisição

    Exemplos:
      | nome  | cpf            | email         | valor | parcelas | seguro | erros                                     |
      |       | nao cadastrado | aaa@teste.com | 1200  | 3        | true   | Nome não pode ser vazio                   |
      | teste |                | aaa@teste.com | 1200  | 3        | true   | Cpf não pode ser vazio                    |
      | teste | nao cadastrado |               | 1200  | 3        | true   | E-mail deve ser um e-mail válido          |
      | teste | nao cadastrado | aaa@teste.com |       | 3        | true   | Valor não pode ser vazio                  |
      | teste | nao cadastrado | aaa@teste.com | 0     | 3        | true   | Valor não pode ser menor que 1000         |
      | teste | nao cadastrado | aaa@teste.com | 999   | 3        | true   | Valor não pode ser menor que 1000         |
      | teste | nao cadastrado | aaa@teste.com | 40001 | 3        | true   | Valor deve ser menor ou igual a R$ 40.000 |
      | teste | nao cadastrado | aaa@teste.com | 1200  |          | true   | Parcelas não pode ser vazio               |
      | teste | nao cadastrado | aaa@teste.com | 1200  | 3        |        | Seguro não pode ser vazio                 |
      | teste | 72579529       | aaa@teste.com | 1200  | 3        | true   | Cpf invalido                              |
      | teste | teste          | aaa@teste.com | 1200  | 3        | false  | Cpf invalido                              |
      | teste | nao cadastrado | teste         | 1200  | 2        | true   | E-mail deve ser um e-mail válido          |
      | teste | nao cadastrado | aaa@teste.com | 1200  | 0        | true   | Parcelas deve ser igual ou maior que 2    |
      | teste | nao cadastrado | aaa@teste.com | 1200  | 3        | teste  | Seguros deve ser um valor válido          |
      | teste | ja cadastrado  | aaa@teste.com | 1200  | 3        | true   | CPF já existente                          |
      | teste | 510.204.230-05 | aaa@teste.com | 1200  | 3        | true   | Cpf invalido                              |


  @removerSimulacao @CT008
  Esquema do Cenario: CT008 - Simulações - remover simulação com e sem sucesso "<tipo>"
    Quando remover uma nova simulação do tipo "<tipo>"
    Entao será informado o retorno "<mensagem>" da remoção de acordo com o "<tipo>"

    Exemplos:
      | tipo     | mensagem                 |
      | valida   |                          |
      | invalida | Simulação não encontrada |

  @atualizarSimulacao @CT009
  Cenario: CT009 - Simulações - atualização de uma simulação valida
    Dado que eu atualize uma nova simulação válida
    Quando a simulação foi atualizada retornando o body na response informando que o cpf não possui mais simulações
    Então validarei que os demais campos também foram alterados

  @consultaTodasSimulacoesSemResultado @CT010
  Cenario: CT010 - Simulações - consulta todas as simulações não contendo resultado
    Dado que eu remova todas simulações realizadas
    Quando consultar todas as simulações cadastradas
    Então validarei que não possuem simulações cadastradas retornando no content

  @consultaTodasSimulacoesComResultado @CT011
  Cenario: CT011 - Simulações - consulta todas as simulações contendo resultado
    Dado que eu remova todas simulações realizadas
    E criar uma nova simulação válida
    Quando consultar todas as simulações cadastradas
    Então validarei as simulações cadastradas










