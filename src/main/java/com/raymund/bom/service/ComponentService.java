package com.raymund.bom.service;

import java.util.Optional;

import com.raymund.bom.model.BillOfMaterials;

/**
 * Service that provides main functionality of BOM generation per component
 * @author Raymund
 *
 */
public interface ComponentService {

	/**
	 * Generates a bill of materials (list of required sub-components) for a component
	 * @param name the unique component identifier
	 * @return
	 */
	public  Optional<BillOfMaterials> getBillOfMaterialsForComponent(String name);
}
