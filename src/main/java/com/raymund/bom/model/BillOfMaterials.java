package com.raymund.bom.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Model class for our response body, includes hyper-media link support
 * <br />
 * Not necessary to make the {@link Scope} as prototype, I just want to test the Lookup annotation
 *
 * @author Raymund
 *
 */
@Getter
@Setter
@ToString
@Component
@Scope("prototype" )
public class BillOfMaterials extends ResourceSupport  {

	private String component;

	private final Set<String> requirementSet;

	public BillOfMaterials() {
		this.requirementSet = new HashSet<>();
	}

}
