package dev.unscrud.leilao.acceptance.steps;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import dev.unscrud.leilao.login.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LoginSteps {
  private LoginPage loginPage;

  @Dado("o usuario valido")
  public void o_usuario_valido() {
    loginPage = new LoginPage();
    loginPage.preencheUsuarioSenha("fulano", "pass");
  }

  @Quando("realiza login")
  public void realiza_login() {
    loginPage.efetuaLogin();
  }

  @Entao("eh redirecionado para a pagina de leiloes")
  public void eh_redirecionado_para_a_pagina_de_leiloes() {
    assertFalse(loginPage.isPaginaDeLogin());
    assertEquals("fulano", loginPage.getNomeUsuarioLogado());
    loginPage.fechar();
  }

  @Dado("o usuario invalido")
  public void o_usuario_invalido() {
    loginPage = new LoginPage();
    loginPage.preencheUsuarioSenha("usuario", "senha");
  }

  @Quando("tenta se logar")
  public void tenta_se_logar() {
    loginPage.efetuaLogin();
  }

  @Entao("continua na pagina de login")
  public void continua_na_pagina_de_login() {
    assertTrue(loginPage.isPaginaDeLoginComErro());
    assertTrue(loginPage.possuiString("Usuário e senha inválidos."));
    assertNull(loginPage.getNomeUsuarioLogado());
    loginPage.fechar();
  }

}
