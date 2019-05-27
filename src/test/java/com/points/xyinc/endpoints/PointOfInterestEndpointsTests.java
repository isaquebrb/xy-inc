package com.points.xyinc.endpoints;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.points.xyinc.domain.PointOfInterest;
import com.points.xyinc.services.PointOfInterestService;
import com.points.xyinc.services.exceptions.InvalidParametersException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles({"test"})
public class PointOfInterestEndpointsTests {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private PointOfInterestService service;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		Assert.assertNotNull(this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findAllTest() throws Exception {
		mockMvc.perform(get("/points")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(7))).andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	public void findByCoordenatesTest() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("x", "20");
		params.add("y", "10");
		params.add("distance", "10");
		mockMvc.perform(get("/points/findByCoordenates").params(params)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[1].id", is(3)))
				.andExpect(jsonPath("$[2].id", is(5))).andExpect(jsonPath("$[3].id", is(6)));
	}

	@Test
	public void insertTest() throws Exception {
		String poiJson = json(new PointOfInterest(null, "Test Insert Poi", 30, 30));
		this.mockMvc.perform(post("/points").contentType(contentType).content(poiJson)).andExpect(status().isCreated());
	}

	@Test(expected = InvalidParametersException.class)
	public void insertWithNullNameTest() throws Exception {
		service.insert(new PointOfInterest(null, null, 30, 30));
	}

	@Test(expected = InvalidParametersException.class)
	public void insertWithNullCoordinateXTest() throws Exception {
		service.insert(new PointOfInterest(null, "Test Insert Poi With Null Coordinate X", null, 10));
	}

	@Test(expected = InvalidParametersException.class)
	public void insertWithNullCoordinateYTest() throws Exception {
		service.insert(new PointOfInterest(null, "Test Insert Poi With Null Coordinate Y", 30, null));
	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
