# language: pt
# charset: UTF-8

@simulacaoCredito @restricoes @build
Funcionalidade: Api Simulação de Crédito - Restrições
  Eu como cliente gostaria de consultar as retrições de cpf´s

  @consultaCpfRestricao @CT001
  Esquema do Cenario: CT001 - Restrições - Consultar cpf com restrição
    Quando eu consultar um usuario através do cpf "<cpf>" com restrição
    Entao sera apresentado que o cpf "<cpf>" em questão possui restrição

    Exemplos:
      | cpf         |
      | 97093236014 |
      | 60094146012 |
      | 84809766080 |
      | 62648716050 |
      | 26276298085 |
      | 01317496094 |
      | 55856777050 |
      | 19626829001 |
      | 24094592008 |
      | 58063164083 |

  @consultaCpfSemRestricao @CT002
  Cenario: CT002 - Restrições - Consultar cpf sem restrição
    Quando eu consultar um usuario através do cpf "cpf" sem restrição
    Entao sera apresentado que o cpf em questão não possui restrição

  @consultaCpfVazio @CT003
  Cenario: CT003 - Restrições - Consultar cpf vazio
    Quando eu consultar um usuario através de um cpf vazio
    Entao retornará que não foram encontrados registros

