package com.sistemas.basico.controlador;

import com.sistemas.basico.dominio.Etapa;
import com.sistemas.basico.dominio.Tarea;
import com.sistemas.basico.dominio.Trabajo;
import com.sistemas.basico.servicio.ClienteService;
import com.sistemas.basico.servicio.EtapaService;
import com.sistemas.basico.servicio.TarifaService;
import com.sistemas.basico.servicio.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrabajoController {
    @Autowired
    private TrabajoService trabajoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TarifaService tarifaService;
    @Autowired
    private EtapaService etapaService;

    @GetMapping({"/", "index"})
    public String getIndex(Model model) {
        model.addAttribute("listaTrabajos", trabajoService.listarTodos());

        return "/trabajo/trabajoIndex";
    }

    @GetMapping("/nuevo")
    public String getTrabajoFormNew(Model model) {
        Trabajo nuevo = new Trabajo();

        nuevo.setFechaTrabajo(new Date());
        nuevo.setFechaEntrega(new Date());

        model.addAttribute("trabajo", nuevo);
        model.addAttribute("listaClientes", clienteService.listarTodos());
        model.addAttribute("listaTarifas", tarifaService.listarTodos());
        //model.addAttribute ("listaEmpleados", empleadoService.listarTodos());

        return "/trabajo/trabajoForm";
    }

    @PostMapping("/nuevo")
    public String postTrabajoFormNew(
            @Valid @ModelAttribute("trabajo") Trabajo trabajo,
            BindingResult bindingResult,
            Model model) {
		List<Tarea> tareas = new ArrayList<Tarea>();

		if (bindingResult.hasErrors()) {
			//si hubo errores se muestra el formulario para correccion
			model.addAttribute("listaClientes", clienteService.listarTodos());
			model.addAttribute("listaTarifas", tarifaService.listarTodos());

			return "/trabajo/trabajoForm";
		}
		//agregar tareas:
		for (Etapa etapa : etapaService.listarTodos()) {
			Tarea tarea = new Tarea();

			tarea.setTrabajo(trabajo);
			tarea.setEtapa(etapa);
			tareas.add(tarea);
		}

		trabajo.setTarea(tareas);
		trabajoService.agregar(trabajo);

		return "redirect:/trabajo/index";
	}
   @GetMapping("/editar/{id}")
   public String getTrabajoFormEdit (
             @PathVariable("id") Long id,
                Model model){
            Trabajo buscado = trabajoService.buscar(id);

            if (buscado != null) {
                model.addAttribute("trabajo", buscado);
            } else {
                model.addAttribute("trabajo", new Trabajo());
            }
            model.addAttribute("listaClientes", clienteService.listarTodos());
            model.addAttribute("listaTarifas", tarifaService.listarTodos());

            return "/trabajo/trabajoForm";
        }
   @PostMapping("/editar")
    public String postTrabajoFormEdit(
                @Valid @ModelAttribute("trabajo") Trabajo trabajo,
                BindingResult bindingResult,
                Model model) {

            if (bindingResult.hasErrors()) {
                //
                model.addAttribute("listaClientes", clienteService.listarTodos());
                model.addAttribute("listaTarifas", tarifaService.listarTodos());

                return "/trabajo/trabajoForm";
            }
            trabajoService.agregar(trabajo);

            return "redirect:/trabajo/index";
    }

    @GetMapping("/eliminar/{id}")
       public String getTrabajoEliminar(
                @PathVariable("id") Long id,
                Model model){

                trabajoService.eliminar(id);

                return"redirect:/trabajo/index";
                }
	}