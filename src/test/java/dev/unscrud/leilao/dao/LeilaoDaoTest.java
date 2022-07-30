package dev.unscrud.leilao.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.unscrud.leilao.model.Usuario;
import dev.unscrud.util.JPAUtil;
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
    Usuario usuario = criarUsuario();
    Leilao leilao = new Leilao("Mochila", new BigDecimal("70"), LocalDate.now(), usuario);

    leilao = dao.salvar(leilao);

    Leilao salvo = dao.buscarPorId(leilao.getId());
    assertNotNull(salvo);
  }

  @Test
  public void deveriaAtualizarUmLeilao() {
    Usuario usuario = criarUsuario();
    Leilao leilao = new Leilao("Mochila", new BigDecimal("70"), LocalDate.now(), usuario);

    leilao = dao.salvar(leilao);

    leilao.setNome("Celular");
    leilao.setValorInicial(new BigDecimal("400"));

    leilao = dao.salvar(leilao);

    Leilao salvo = dao.buscarPorId(leilao.getId());
    assertEquals("Celular", salvo.getNome());
    assertEquals(new BigDecimal("400"), salvo.getValorInicial());
  }

  private Usuario criarUsuario() {
    Usuario usuario = new Usuario("fulano", "fulano@unscrud.dev", "pass");
    em.persist(usuario);
    return usuario;
  }
}
