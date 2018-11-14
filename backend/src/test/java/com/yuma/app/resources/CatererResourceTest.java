package com.yuma.app.resources;

import com.yuma.app.document.Address;
import com.yuma.app.repository.CatererRepository;
import com.yuma.app.service.CatererService;
import com.yuma.app.to.CatererTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatererResourceTest {

	@MockBean
	private CatererService catererService;

	@Mock
	private Logger logger;

	@Mock
	CatererRepository catererRepository;

	@Autowired
	private CatererResource catererResource;

	private CatererTO catererTO1;
	private CatererTO catererTO1updated ;
	private CatererTO catererTO2;


	@Before
	public void setUp() throws Exception {

		catererTO1 = new CatererTO(UUID.randomUUID(), "OrelMoisa", "orelmoisa@gmail.com", new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"), "chinese", new ArrayList<>(), "234");
		catererTO1updated = new CatererTO(catererTO1.getUserId(), "Hamideh", "hamideh@gmail.com", new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"), "chinese", new ArrayList<>(), "234");
		catererTO2 =  new CatererTO(UUID.randomUUID(), "JohnSmith", "johnsmith@gmail.com", new Address(1601, "clever", "CSL", "QC", "Canada", "H4L7I8"), "greek", new ArrayList<>(), "20190807");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void catererResourceGetAllTest() {
		List<CatererTO> actualCaterers = new ArrayList<>();
		actualCaterers.add(catererTO1);
		actualCaterers.add(catererTO2);
		Mockito.when(catererService.list()).thenReturn(actualCaterers);
		List<CatererTO> expectedCaterers = catererResource.getAll();

		Assert.assertEquals(expectedCaterers.size(), actualCaterers.size());
	}

	@Test
	public void catererResourceUpdateTest() {

		Mockito.when(catererService.update(catererTO1)).thenReturn(catererTO1updated);

		CatererTO catererT0actual = catererResource.update(catererTO1);

		Assert.assertEquals(catererTO1updated.getUserId(), catererT0actual.getUserId());
		Assert.assertEquals(catererTO1updated.getName(), catererT0actual.getName());
		Assert.assertEquals(catererTO1updated.getEmail(),catererT0actual.getEmail());

	}


}
