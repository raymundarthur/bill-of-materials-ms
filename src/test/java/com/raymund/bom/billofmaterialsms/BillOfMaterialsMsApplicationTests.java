package com.raymund.bom.billofmaterialsms;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.raymund.bom.controller.ComponentsController;
import com.raymund.bom.model.BillOfMaterials;
import com.raymund.bom.service.ComponentService;

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

	@MockBean
	private ComponentService componentService;


	@Test
	public void testGetBOM_Http200() throws Exception {

		//Fixtures
		final BillOfMaterials bomExpected = new BillOfMaterials();
		bomExpected.setComponent("P");
		bomExpected.getRequirementSet().add("A");

		given(componentService.getBillOfMaterialsForComponent("P")).willReturn(Optional.of(bomExpected));

		mvc.perform(get("/api/v1/components/P/bom")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.component", is("P")))
		.andExpect(jsonPath("$.requirementSet", hasSize(1)));
	}

	@Test
	public void testGetBOM_Http404() throws Exception {
		mvc.perform(get("/api/v1/components/bom/X")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isNotFound());
	}




}
