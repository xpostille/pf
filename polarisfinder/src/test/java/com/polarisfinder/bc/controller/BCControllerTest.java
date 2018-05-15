package com.polarisfinder.bc.controller;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.TransactionRequest.Type;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.junit.Test;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.polarisfinder.bc.controller.EmployeeRestController;
import com.polarisfinder.bc.entity.SampleOrg;
import com.polarisfinder.bc.entity.SampleStore;

import static java.lang.String.format;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
// @Import(EmployeeRestController.class)
public class BCControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeRestController employeeRestController;

	@Test
	public void testEmployee() {
		System.out.println("h");
		assertThat(employeeRestController).isNotNull();
	}

	public class End2endNodeIT extends End2endIT {
		{

			testName = "End2endNodeIT"; // Just print out what test is really running.

			// This is what changes are needed to deploy and run Node code.

			// this is relative to src/test/fixture and is where the Node chaincode source
			// is.
			CHAIN_CODE_FILEPATH = "sdkintegration/nodecc/sample1"; // override path to Node code
			CHAIN_CODE_PATH = null; // This is used only for GO.
			CHAIN_CODE_NAME = "example_cc_node"; // chaincode name.
			CHAIN_CODE_LANG = Type.NODE; // language is Node.
		}

		@Override
		void blockWalker(HFClient client, Channel channel)
				throws InvalidArgumentException, ProposalException, IOException {
			// block walker depends on the state of the chain after go's end2end. Nothing
			// here is language specific so
			// there is no loss in coverage for not doing this.
		}

		@Override
		@Test
		public void setup() throws Exception {
			sampleStore = new SampleStore(sampleStoreFile);
			enrollUsersSetup(sampleStore);
			runFabricTest(sampleStore); // just run fabric tests.
		}

		@Override
		Channel constructChannel(String name, HFClient client, SampleOrg sampleOrg) throws Exception {
			// override this method since we don't want to construct the channel that's been
			// done.
			// Just get it out of the samplestore!

			client.setUserContext(sampleOrg.getPeerAdmin());

			return sampleStore.getChannel(client, name).initialize();

		}
	}

	/*
	 * @Test public void getemployees() throws Exception {
	 * 
	 * //given(employeeRestController.getemployees()).willReturn();
	 * this.mvc.perform(get("/employee/getemployees")).andDo(print()).andExpect(
	 * status().isOk())
	 * .andExpect(content().string(containsString("Hello World")));; }
	 * 
	 * 
	 * @Test public void getArrivals() throws Exception { Arrival arrival = new
	 * Arrival(); arrival.setCity("Yerevan");
	 * 
	 * List<Arrival> allArrivals = singletonList(arrival);
	 * 
	 * given(arrivalController.getAllArrivals()).willReturn(allArrivals);
	 * 
	 * mvc.perform( get(VERSION + ARRIVAL +
	 * "all").with(user("blaze").password("Q1w2e3r4")).contentType(APPLICATION_JSON)
	 * ) .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
	 * .andExpect(jsonPath("$[0].city", is(arrival.getCity()))); }
	 * 
	 * @Test public void getArrivalsById() throws Exception { Arrival arrival = new
	 * Arrival(); arrival.setCity("Yerevan");
	 * 
	 * given(arrivalController.getArrivalById(arrival.getId())).willReturn(arrival);
	 * 
	 * mvc.perform(get(VERSION + ARRIVAL +
	 * arrival.getId()).with(user("blaze").password("Q1w2e3r4"))
	 * .contentType(APPLICATION_JSON)).andExpect(status().isOk())
	 * .andExpect(jsonPath("city", is(arrival.getCity()))); }
	 */
}
