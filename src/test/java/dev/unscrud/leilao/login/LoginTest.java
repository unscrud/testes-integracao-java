package dev.unscrud.leilao.login;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
  private LoginPage paginaDeLogin;

  @BeforeEach
  private void inicializar() {
    this.paginaDeLogin = new LoginPage();
  }

  @Test
  public void deveriaEfetuarLoginComDadosValidos() {
    paginaDeLogin.preencheUsuarioSenha("fulano", "pass");
    paginaDeLogin.efetuaLogin();
    assertFalse(paginaDeLogin.isPaginaDeLogin());
    assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
  }

  @Test
  public void naoDeveriaLogarComDadosInvalidos() {
    this.paginaDeLogin.preencheUsuarioSenha("usuarioinvalido", "senhainvalida");
    this.paginaDeLogin.efetuaLogin();
    assertTrue(paginaDeLogin.isPaginaDeLoginComErro());
    assertTrue(paginaDeLogin.possuiString("Usuário e senha inválidos."));
    assertNull(paginaDeLogin.getNomeUsuarioLogado());
  }

  @Test
  public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
    paginaDeLogin.navegaParaPaginaDeLances();
    assertTrue(paginaDeLogin.isPaginaDeLogin());
    assertFalse(paginaDeLogin.possuiString("Dados do Leilão"));
  }

  @AfterEach
  private void finalizar() {
    this.paginaDeLogin.fechar();
  }

}
