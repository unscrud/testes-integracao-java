package dev.unscrud.leilao.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioDaoTest {

  private UsuarioDao dao;
  private EntityManager em;

  @BeforeEach
  private void inicializar() {
    this.dao = new UsuarioDao(em);
  }

  @Test
  void testeBuscarUsuarioPorUsername() {
    assertNotNull(this.dao.buscarPorUsername("fulano"));
  }
}
