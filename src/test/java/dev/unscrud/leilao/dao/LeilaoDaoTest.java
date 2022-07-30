package dev.unscrud.leilao.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.unscrud.leilao.model.Usuario;
import dev.unscrud.util.JPAUtil;
import dev.unscrud.util.builder.LeilaoBuilder;
import dev.unscrud.util.builder.UsuarioBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import dev.unscrud.leilao.model.Leilao;

public class LeilaoDaoTest {
  private LeilaoDao dao;
  private EntityManager em;

  @BeforeEach
  private void inicializar() {
    em = JPAUtil.getEntityManager();
    this.dao = new LeilaoDao(em);
    em.getTransaction().begin();
  }

  @AfterEach
  private void aoFinalizarCadaTeste() {
    em.getTransaction().rollback();
  }

  @Test
  public void deveriaCadastrarUmLeilao() {
    Usuario usuario = new UsuarioBuilder().comNome("Fulano")
        .comEmail("fulano@email.com").comSenha("pass").criar();
    em.persist(usuario);

    Leilao leilao = new LeilaoBuilder().comNome("Mochila").comValorInicial("500")
        .comData(LocalDate.now()).comUsuario(usuario).criar();

    leilao = dao.salvar(leilao);

    Leilao salvo = dao.buscarPorId(leilao.getId());
    assertNotNull(salvo);
  }

  @Test
  public void deveriaAtualizarUmLeilao() {
    Usuario usuario = new UsuarioBuilder().comNome("Fulano")
        .comEmail("fulano@email.com").comSenha("pass").criar();
    em.persist(usuario);

    Leilao leilao = new LeilaoBuilder().comNome("Mochila").comValorInicial("500")
        .comData(LocalDate.now()).comUsuario(usuario).criar();

    leilao = dao.salvar(leilao);

    leilao.setNome("Celular");
    leilao.setValorInicial(new BigDecimal("400"));

    leilao = dao.salvar(leilao);

    Leilao salvo = dao.buscarPorId(leilao.getId());
    assertEquals("Celular", salvo.getNome());
    assertEquals(new BigDecimal("400"), salvo.getValorInicial());
  }

}
