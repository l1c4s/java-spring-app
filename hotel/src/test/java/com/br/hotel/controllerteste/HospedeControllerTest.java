package com.br.hotel.controllerteste;

import com.br.hotel.Repositorios.HospedeRepositorio;
import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.controller.HospedeController;
import com.br.hotel.models.Hospede;
import com.br.hotel.models.UserCliente;
import com.br.hotel.servicos.HospedeServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

@SpringBootTest
public class HospedeControllerTest {

    @InjectMocks
    private HospedeController hospedeController;

    @Mock
    private HospedeRepositorio hospedeRepositorio;

    @Mock
    private HospedeServico hospedeServico;

    @Mock
    private Model model;

    @Mock
    private UserClienteRepositorio userClienteRepositorio;

    private Hospede hospede;
    private UserCliente userCliente;

    @BeforeEach
    void setup() {
        // Mock do hóspede
        hospede = new Hospede();
        hospede.setId(1L);
        hospede.setNome("João Silva");
        hospede.setCpf("98765432100");

        // Mock do usuário
        userCliente = new UserCliente();
        userCliente.setCpf("12345678900");
        userCliente.setSenha("123456");

        // Mock do repositório
        when(userClienteRepositorio.findByCpf("12345678900"))
                .thenReturn(java.util.Optional.of(userCliente));
    }

    @Test
    void testRenderizaPaginaCadastro() {
        // Chama o método diretamente
        ModelAndView mv = hospedeController.cadastrarHospede(new Hospede());

        // Verifica se a view está correta
        assert mv.getViewName().equals("cadastro");
    }
}
    

