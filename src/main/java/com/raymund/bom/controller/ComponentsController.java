package com.raymund.bom.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raymund.bom.exception.ComponentNotFoundException;
import com.raymund.bom.model.BillOfMaterials;
import com.raymund.bom.service.ComponentService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST End point to expose our Component BOM generation services
 * @author Raymund
 *
 */
@RestController
@RequestMapping("/api/v1/components")
@Slf4j
public class ComponentsController {

	@Autowired
	private ComponentService componentService;


	@RequestMapping(value= "/{component}/bom", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BillOfMaterials getBillOfMaterial(@PathVariable() String component) {
		log.debug("Got request for component: {}", component);

		component = component==null ? "" : component.toUpperCase();
		final Optional<BillOfMaterials> bomOptional = componentService.getBillOfMaterialsForComponent(component);

		if(bomOptional.isPresent()) {
			final BillOfMaterials bom = bomOptional.get();
			final Link selfLink = linkTo(methodOn(ComponentsController.class)
					.getBillOfMaterial(component)).withSelfRel();
			bom.add(selfLink);
			return bom;
		}else {
			throw new ComponentNotFoundException("Component not found - " + component);
		}
	}

}
