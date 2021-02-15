package com.sistemas.basico.controlador;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sistemas.basico.dominio.Tarea;
import com.sistemas.basico.servicio.EmpleadoService;
import com.sistemas.basico.servicio.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/tarea")
public class TareaController {
	@Autowired
	private TareaService tareaService;
	@Autowired private EmpleadoService empleadoService;
	
	@GetMapping({"{trabajoid}","{trabajoid}/index" } )
	public String getIndex( 
			@PathVariable("trabajoid") Long trabajoid,
			Model model) { 
		List<Tarea> listaTareas = new ArrayList<Tarea>();
		
		for(Tarea tarea :tareaService.listarTodos()) {
			if (tarea.getTrabajo().getId()== trabajoid){
				listaTareas.add(tarea);
			}	
		}
		
	model. addAttribute( " listaTareas", listaTareas);
	
	return "/tarea/tareaIndex" ;
	}
	
	@GetMapping("/editar/{id}") 
	public String getTareaFormEdit(
			@PathVariable("id")	Long id, 
			Model model) { 
	Tarea buscado = tareaService.buscar(id);
	
	if (buscado != null) { 
		model.addAttribute("tarea", buscado);
	}
	else { 
		model. addAttribute("tarea", new Tarea()); 
	}
	model.addAttribute("listaEmpleado", empleadoService.listarTodos());
	return "/tarea/tareaForm";
	}
	
	@PostMapping("/editar")
	public String postTareaFormEdit( 
			@Valid @ModelAttribute("tarea") Tarea tarea,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			//si hubo errores se muestra el formulario para correccion 
			model.addAttribute("listaEmpleados", empleadoService.listarTodos());

			return "/tarea/tareaForm";

		}
		tareaService.agregar(tarea);
		return "redirect:/tarea/" + tarea.getTrabajo().getId() + "/index";
	}

	@GetMapping("/iniciar/{id}") 
	public String getTareaIniciar( 
			@PathVariable("id") Long id, 
			Model model) { 
		Tarea tarea = tareaService.buscar(id); 
		Long trabajoId = 0L;
		
		if (tarea != null) { 
			tarea.setFechaInicio(new Date());
			trabajoId = tarea.getTrabajo().getId();
			tareaService.agregar(tarea) ; 
		}
		
	return "redirect:/tarea/"+trabajoId+"/index";
	
	}
	
	@GetMapping("/terminar/{id}") 
	public String getTareaTerminar( 
			@PathVariable("id") Long id,
			Model model) { 
		Tarea tarea = tareaService.buscar(id); 
		Long trabajoId = 0L;
		
		if (tarea != null) {
			tarea.setFechaFin(new Date()); 
			trabajoId = tarea.getTrabajo().getId();
			tareaService.agregar(tarea) ; 
		}
		return "redirect:/tarea/"+trabajoId+"/index";
	}
}
