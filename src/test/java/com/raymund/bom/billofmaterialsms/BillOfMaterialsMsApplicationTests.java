package com.raymund.bom.billofmaterialsms;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.raymund.bom.controller.ComponentsController;

/**
 * TDD for Rest Endpoint
 * @author v0id
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ComponentsController.class)
public class BillOfMaterialsMsApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	@Ignore //Remove if ready
	public void testGetBOM_Http200() throws Exception {
		mvc.perform(get("/api/v1/components/bom/P")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.component", hasSize(1)))
		.andExpect(jsonPath("$.component", hasValue("P")))
		.andExpect(jsonPath("$.requirementList", hasSize(8)));
	}

	@Test
	public void testGetBOM_Http404() throws Exception {
		mvc.perform(get("/api/v1/components/bom/X")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isNotFound());
	}



}
