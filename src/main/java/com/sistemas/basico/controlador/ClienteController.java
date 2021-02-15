package com.sistemas.basico.controlador;

import com.sistemas.basico.dominio.Cliente;
import com.sistemas.basico.dominio.Tarea;
import com.sistemas.basico.dominio.Trabajo;
import com.sistemas.basico.servicio.ClienteService;
import com.sistemas.basico.servicio.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TrabajoService trabajoService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaClientes", clienteService.listarTodos());
        return "/cliente/clienteIndex";
    }

    @GetMapping("/nuevo")
    public String getClienteFormNew(Model model) {
        model.addAttribute("cliente", new Cliente());

        return "/cliente/clienteForm";
    }

    @PostMapping("/nuevo")
    public String postClienteFormNew(
            @Valid @ModelAttribute("cliente") Cliente cliente,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            //si hubo errores se muestra el formulario para correcion
            return "/cliente/clienteForm";
        }
        clienteService.agregar(cliente);

        return "redirect:/cliente/index";
    }

    @GetMapping("/editar/{id}")
    public String getClienteFormEdit(
            @PathVariable("id") Long id,
            Model model) {
        Cliente buscado = clienteService.buscar(id);
        if (buscado != null) {
            model.addAttribute("cliente", buscado);
        } else {
            model.addAttribute("cliente", new Cliente());
        }
        return "/cliente/clienteForm";
    }

    @PostMapping("/editar")
    public String postClienteFormEdit(
            @Valid @ModelAttribute("cliente") Cliente cliente,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            //si hubo errores se muestra el formulario para correcion
            return "/cliente/clienteForm";
        }
        clienteService.agregar(cliente);

        return "redirect:/cliente/index";
    }

    @GetMapping("/eliminar/{id}")
    public String getClienteEliminar(
            @PathVariable("id") Long id,
            Model model) {

        clienteService.eliminar(id);

        return "redirect:/cliente/index";
    }

    @GetMapping("/monitorear")
    public String getClienteMonitorear(Model model) {
        //muestra cuadro de seleccion de clientes
        model.addAttribute("listaClientes", clienteService.listarTodos());
        model.addAttribute("cliente", new Cliente());

        return "/cliente/clienteMonitorForm";
    }

    @PostMapping("/monitorear/lista")
    public String getMonitorearLista(
            @ModelAttribute("cliente") Cliente cliente,
            Model model) {
        //muestra lista de trabajos por cliente
        Cliente buscado = clienteService.buscar(cliente.getId());

        if (buscado != null) {
            model.addAttribute("cliente", buscado);
            model.addAttribute("listaTrabajos", buscado.getTrabajos());
        }

        return "/cliente/clienteMonitorIndex";
    }

    @GetMapping("/monitorear/trabajo/{id}")
    public String getMonitorearDetalle(
            @PathVariable("id") Long id,
            Model model) {
        Trabajo trabajo = trabajoService.buscar(id);
        List<Tarea> tareas = trabajo.getTarea();
        Collections.sort(tareas, (x, y) -> x.getId().compareTo(y.getId()));

        model.addAttribute("trabajo", trabajo);
        model.addAttribute("listaTareas", tareas);

        return "/cliente/clienteMonitorDetalle";
    }
}

