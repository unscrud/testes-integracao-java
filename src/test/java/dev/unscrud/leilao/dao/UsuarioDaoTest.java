package dev.unscrud.leilao.dao;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.unscrud.leilao.model.Usuario;
import dev.unscrud.util.JPAUtil;

public class UsuarioDaoTest {

  private UsuarioDao dao;
  private Usuario usuario;
  private EntityManager em;

  @BeforeEach
  private void inicializar() {
    em = JPAUtil.getEntityManager();
    this.dao = new UsuarioDao(em);
    em.getTransaction().begin();
  }

  @AfterEach
  private void aoFinalizarCadaTeste() {
    em.getTransaction().rollback();
  }

  private Usuario criarUsuario() {
    usuario = new Usuario("fulano", "fulano@unscrud.dev", "pass");
    em.persist(usuario);
    return usuario;
  }

  @Test
  void deviriaEncontrarUsuarioCadastrado() {
    Usuario usuario = criarUsuario();
    Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
    assertNotNull(encontrado);
  }

  @Test
  void naoDeviriaEncontrarUsuarioNaoCadastrado() {
    criarUsuario();
    assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
  }
}
