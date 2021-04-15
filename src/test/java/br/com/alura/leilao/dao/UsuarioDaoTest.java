package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDaoTest {
    private UsuarioDao dao;

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());

        assertNotNull(encontrado);
    }
    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
    }
}
