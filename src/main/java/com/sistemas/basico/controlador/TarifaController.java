package com.sistemas.basico.controlador;

import javax.validation.Valid;

import com.sistemas.basico.dominio.Tarifa;
import com.sistemas.basico.servicio.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/tarifa")
public class TarifaController {
	@Autowired
	private TarifaService tarifaService;
	
	@GetMapping({"/","/index"})
	public String getIndex(Model model) { 
	model.addAttribute("listaTarifas", tarifaService.listarTodos());
	
	return "/tarifa/tarifaIndex" ;
	}
	@GetMapping("/nuevo")
	public String getTarifaFormNew(Model model) { 
		model.addAttribute("tarifa", new Tarifa());
		
		return "/tarifa/tarifaForm"; 
	}
	
	@PostMapping("/nuevo")
	public String postTarifaFormNew( 
			@Valid @ModelAttribute("tarifa") Tarifa tarifa,
			BindingResult bindingResult,
			Model model) { 
	
		if (bindingResult.hasErrors()) {
			//si hubo erroes se muestra el formulario para correccion 
			return "/tarifa/tarifaForm" ; 
		}
		tarifaService.agregar(tarifa);
		
		return "redirect:/tarifa/index";
	}
	
	@GetMapping("/editar/{id}")
	public String getTarifaFormEdit( 
			@PathVariable("id") Long id,
			Model model) { 
		Tarifa buscado = tarifaService.buscar(id);
		
		if (buscado != null) { 
			model.addAttribute("tarifa", buscado); 
		}
		else { 
			model.addAttribute("tarifa", new Tarifa());
		}
	 
		return "/tarifa/tarifaForm"; 
	}

		@PostMapping("/editar") 
		public String postTarifaFormEdit( 
				@Valid @ModelAttribute("tarifa") Tarifa tarifa, 
				BindingResult bindingResult, 
				Model model) { 

			if (bindingResult.hasErrors()) {
				//Si hubo errores se muestra el formulario para correccion 
				return "/tarifa/tarifaForm"; 
			}
			tarifaService.agregar(tarifa ) ;
			return "redirect:/tarifa/index"; 
		}
	@GetMapping("/eliminar/{id}")
	public String getTarifaEliminar(
			@PathVariable("id") Long id, 
			Model model) { 

	tarifaService.eliminar( id ); 
	
	return "redirect:/tarifa/index"; 
	}
}
