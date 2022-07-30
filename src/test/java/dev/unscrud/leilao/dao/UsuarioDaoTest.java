package dev.unscrud.leilao.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

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
    usuario = new Usuario("fulano", "fulano@unscrud.dev", "pass");
    em.getTransaction().begin();
    em.persist(usuario);
    em.getTransaction().commit();
  }

  @Test
  void testeBuscarUsuarioPorUsername() {
    Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
    assertNotNull(encontrado);
  }
}
