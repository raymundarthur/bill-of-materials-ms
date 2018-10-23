package com.raymund.bom.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raymund.bom.model.BillOfMaterials;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/components")
@Slf4j
public class ComponentsController {


	@RequestMapping(value= "/{component}/bom", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BillOfMaterials getBillOfMaterial(@PathVariable() String component) {
		log.debug("Got request for component: {}", component);
		return null;
	}

}
