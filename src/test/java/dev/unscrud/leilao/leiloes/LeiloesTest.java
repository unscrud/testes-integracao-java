package dev.unscrud.leilao.leiloes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import dev.unscrud.leilao.login.LoginPage;

public class LeiloesTest {
  private LeiloesPage paginaDeLeiloes;

  @AfterEach
  private void finalizar() {
    paginaDeLeiloes.fechar();
  }

  @Test
  public void deveriaCadastrarLeiloes() {
    LoginPage loginPage = new LoginPage();
    loginPage.preencheUsuarioSenha("fulano", "pass");
    paginaDeLeiloes = loginPage.efetuaLogin();
    String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    String nome = "Leilão do dia " + hoje;
    String valor = "500.00";
    CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.abrirFormulario();
    this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
    assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
  }

}
