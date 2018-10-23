package com.raymund.bom.model;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillOfMaterials extends ResourceSupport  {

	private String component;

	private List<String> requirementList;

}
