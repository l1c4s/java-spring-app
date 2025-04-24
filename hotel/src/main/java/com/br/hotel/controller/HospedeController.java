package com.br.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.hotel.Repositorios.HospedeRepositorio;
import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.models.Hospede;
import com.br.hotel.models.UserCliente;
import com.br.hotel.servicos.HospedeServico;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HospedeController {

    @Autowired
    private HospedeRepositorio hospedeRepositorio;

    @Autowired
    private UserClienteRepositorio userclienterepositorio;

    @Autowired
    private HospedeServico hospedeServico;

    @GetMapping("/mainpage")
    public ModelAndView home(UserCliente userCliente){
        ModelAndView mv = new ModelAndView("mainpage");
        String cpf = userCliente.getCpf();
        Optional<UserCliente> user = userclienterepositorio.findByCpf(cpf);

        if(user.isPresent()){
            mv.addObject("userlogado", user.get());
        }
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrarHospede(Hospede hospede) {
        ModelAndView mv = new ModelAndView("cadastro");
        List<Hospede> hospedes = hospedeServico.ListarHospedes();
        mv.addObject("hospede", hospede);  // Objeto usado no formulário
        mv.addObject("hospedes", hospedes); // Lista de hóspedes cadastrados
        
        return mv;
    }

    @PostMapping("/cadastro")
public ModelAndView cadastrarHospede(@Valid Hospede hospede, BindingResult result, HttpSession session) {
    if (result.hasErrors()) {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("hospede", hospede);
        mv.addObject("hospedes", hospedeServico.ListarHospedes());
        return mv;
    }

    try {
        // Recupera o CPF do usuário logado da sessão
        String cpfUsuario = (String) session.getAttribute("cpfUsuario");

        if (cpfUsuario == null) {
            return new ModelAndView("cadastro").addObject("error", "Erro: Usuário não autenticado.");
        }

        Optional<UserCliente> usuarioOpt = userclienterepositorio.findByCpf(cpfUsuario);

        if (!usuarioOpt.isPresent()) {
            return new ModelAndView("cadastro").addObject("error", "Erro: Usuário não encontrado.");
        }

        UserCliente usuario = usuarioOpt.get();
        hospede.setUsercliente(usuario);
        hospedeServico.persist(hospede, usuario.getCpf());

    } catch (Exception e) {
        return new ModelAndView("cadastro").addObject("error", "Erro ao cadastrar hóspede: " + e.getMessage());
    }

    return new ModelAndView("redirect:/cadastro");
}

@PostMapping("/hospede/remover")
public ModelAndView removerHospede(@RequestParam Long id, HttpSession session) {
    // Recupera o CPF do usuário logado da sessão
    String cpfUsuarioLogado = (String) session.getAttribute("cpfUsuario");
    
    if (cpfUsuarioLogado == null) {
        // Se o usuário não estiver logado, exibe um erro
        return new ModelAndView("error").addObject("error", "Usuário não autenticado.");
    }
    
    // Encontra o hóspede pelo ID
    Optional<Hospede> hospedeOpt = hospedeRepositorio.findById(id);
    
    if (hospedeOpt.isPresent()) {
        Hospede hospede = hospedeOpt.get();
        
        // Verifica se o CPF do usuário logado é o mesmo do usuário que cadastrou o hóspede
        if (!hospede.getUsercliente().getCpf().equals(cpfUsuarioLogado)) {
            // Se os CPFs não coincidirem, o usuário não pode excluir o hóspede
            return new ModelAndView("error").addObject("error", "Você não tem permissão para remover esse hóspede.");
        }
        
        // Se a verificação for bem-sucedida, remove o hóspede
        hospedeRepositorio.delete(hospede);
    }
    
    // Redireciona para a página de cadastro de hóspedes com a lista atualizada
    return new ModelAndView("redirect:/cadastro");
}
}
