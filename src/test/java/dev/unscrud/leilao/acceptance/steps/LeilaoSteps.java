package dev.unscrud.leilao.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dev.unscrud.leilao.leiloes.CadastroLeilaoPage;
import dev.unscrud.leilao.leiloes.LeiloesPage;
import dev.unscrud.leilao.login.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LeilaoSteps {
  private LoginPage loginPage;
  private LeiloesPage leiloesPage;
  private CadastroLeilaoPage cadastroDeLeilao;
  private String hoje;
  private String nome;
  private String valor;

  @Dado("o usuario logado")
  public void dado_o_usuario_logado() {
    loginPage = new LoginPage();
    loginPage.preencheUsuarioSenha("fulano", "pass");
  }

  @Quando("acessa a pagina de novo leilao")
  public void acessa_a_pagina_de_novo_leilao() {
    leiloesPage = loginPage.efetuaLogin();
    cadastroDeLeilao = leiloesPage.abrirFormulario();
  }

  @E("preenche o formulario com dados validos")
  public void preenche_o_formulario_com_dados_validos() {
    hoje = LocalDate.now()
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    nome = "Leilão do dia " + hoje;
    valor = "500.00";

    leiloesPage = cadastroDeLeilao
        .cadastrarLeilao(nome, valor, hoje);
  }

  @Entao("volta a pagina de leiloes")
  public void volta_a_pagina_de_leiloes() {
    assertTrue(leiloesPage.isPaginaDeLeiloes());
  }

  @E("o novo leilao aparece na tabela")
  public void o_novo_leilao_aparece_na_tabela() {
    assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    leiloesPage.fechar();
  }
}
