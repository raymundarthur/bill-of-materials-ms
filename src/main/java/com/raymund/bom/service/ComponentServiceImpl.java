package com.raymund.bom.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.raymund.bom.model.BillOfMaterials;
import com.raymund.bom.model.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Concrete implementation of {@link ComponentService}
 * @author Raymund
 *
 */
@Slf4j
@Service
public class ComponentServiceImpl implements ComponentService {

	private Multimap<String, String> inputMap;

	@Lookup
	public BillOfMaterials getBillOfMaterialsBean() {
		return null;
	}

	@PostConstruct
	private void init() {
		/* TODO: Loads the in-memory multi-map with pre-configured value : Replace by proper DB or other storage
		         Here its just a <String, String> multi-map since it is stated on the requirement like this.
		 */

		inputMap = ArrayListMultimap.create();

		inputMap.put("P", "A");
		inputMap.put("P", "B");
		inputMap.put("P", "C");
		inputMap.put("A", "D");
		inputMap.put("A", "E");
		inputMap.put("B", "E");
		inputMap.put("B", "F");
		inputMap.put("B", "G");
		inputMap.put("C", "G");
		inputMap.put("C", "H");
		inputMap.put("D", "I");

		log.info("Pre-loaded Component Dependency multi-map with {} entries", inputMap.size());
	}


	/*
	 * (non-Javadoc)
	 * @see com.raymund.bom.service.ComponentService#getBillOfMaterialsForComponent(java.lang.String)
	 */
	@Override
	public Optional<BillOfMaterials> getBillOfMaterialsForComponent(String name) {

		log.debug("Generating BOM for component: {}", name);
		// 1. Find component P if it exist, otherwise reject
		if(!inputMap.containsKey(name) && !inputMap.containsValue(name)){
			log.info("Not found on input data: {}", name);
			return Optional.empty();
		}


		/* 2. Convert Flat Map into a tree structure
		   This can also be processed only once during startup,
		   but I opted to put it here for better readability only
		 */
		final Map<String, Component> componentMap = new LinkedHashMap<>();

		inputMap.entries().stream().forEach(entry -> {
			log.debug("{} -> {}", entry.getKey(), entry.getValue());
			Component component = componentMap.get(entry.getKey());
			if(component==null) {
				component = new Component(entry.getKey());
				componentMap.put(component.getName(), component);
			}

			Component subComponent = componentMap.get(entry.getValue());
			if(subComponent==null) {
				subComponent = new Component(entry.getValue());
				componentMap.put(subComponent.getName(), subComponent);
			}

			component.getSubComponents().add(subComponent);
		});


		// 3. Tree Traversal, and formatting of output object

		final Component targetComponent = componentMap.get(name);
		final BillOfMaterials billOfMaterials = getBillOfMaterialsBean();

		final LinkedList<Component> componentBufferList = new LinkedList<>();
		componentBufferList.addFirst(targetComponent);
		billOfMaterials.setComponent(targetComponent.getName());

		while(!componentBufferList.isEmpty()) {
			final Component comp = componentBufferList.poll();
			if(!comp.getSubComponents().isEmpty()) {
				comp.getSubComponents().stream().forEach(subComp -> {
					componentBufferList.add(subComp);
					billOfMaterials.getRequirementSet().add(subComp.getName());
				});
			}
		}

		log.debug("Produced a bom with a total of {} dependencies", billOfMaterials.getRequirementSet().size());
		return Optional.ofNullable(billOfMaterials);
	}




}
