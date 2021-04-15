package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.LeilaoBuilder;
import br.com.alura.leilao.util.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LeilaoDaoTest {
    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaCadastrarUmLeilao() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@gmail.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
            .comNome("Mochila")
            .comValorInicial("500.00")
            .comData(LocalDate.now())
            .comUsuario(usuario).criar();


        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        assertNotNull(salvo);
    }

    @Test
    void deveriaAtualizarUmLeilao() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@gmail.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500.00")
                .comData(LocalDate.now())
                .comUsuario(usuario).criar();

        leilao = dao.salvar(leilao);

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400.00"));

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        assertEquals("Celular", salvo.getNome());
        assertEquals(new BigDecimal("400.00"), salvo.getValorInicial());
    }

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
        em.persist(usuario);
        return usuario;
    }
}
