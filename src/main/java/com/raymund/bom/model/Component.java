package com.raymund.bom.model;

import java.util.Set;
import java.util.TreeSet;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represent a node in our dependency-tree of components
 * @author Raymund
 */
@Data
public class Component implements Comparable<Component> {

	private String name;

	@EqualsAndHashCode.Exclude
	private Set<Component> subComponents;

	public Component(String name) {
		this.name = name;
		this.subComponents = new TreeSet<>();
	}


	@Override
	public int compareTo(Component other) {
		return this.name.compareTo(other.getName());
	}


}
