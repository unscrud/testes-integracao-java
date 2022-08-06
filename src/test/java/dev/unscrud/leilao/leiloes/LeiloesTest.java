package dev.unscrud.leilao.leiloes;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.unscrud.leilao.login.LoginPage;

public class LeiloesTest {
  private LeiloesPage paginaDeLeiloes;
  private CadastroLeilaoPage paginaDeCadastro;

  @BeforeEach
  private void inicializar() {
    LoginPage loginPage = new LoginPage();
    loginPage.preencheUsuarioSenha("fulano", "pass");
    paginaDeLeiloes = loginPage.efetuaLogin();
    paginaDeCadastro = paginaDeLeiloes.abrirFormulario();
  }

  @AfterEach
  private void finalizar() {
    paginaDeLeiloes.fechar();
  }

  @Test
  public void deveriaCadastrarLeiloes() {
    String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    String nome = "Leilão do dia " + hoje;
    String valor = "500.00";
    this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
    assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
  }

  @Test
  public void deveriaValidarCadastroDeLeilao() {
    this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
    assertFalse(this.paginaDeCadastro.isPaginaDeCadastro());
    assertTrue(this.paginaDeCadastro.isPaginaDeLeiloes());
    assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
  }
}
