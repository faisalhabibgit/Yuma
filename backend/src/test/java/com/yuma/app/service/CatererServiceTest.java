//package com.yuma.app.service;
//
//import com.querydsl.core.types.Predicate;
//import com.yuma.app.document.Address;
//import com.yuma.app.document.Caterer;
//import com.yuma.app.document.QCaterer;
//import com.yuma.app.repository.CatererRepository;
//import com.yuma.app.to.CatererTO;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CatererServiceTest {
//	
//	@MockBean
//	private CatererRepository catererRepository;
//
//	@Mock
//	private ConversionService conversionService;
//	
//	@Mock
//	private QCaterer qCaterer;
//	
//	@Autowired
//	private CatererService catererService;
//	
//	private Caterer caterer1;
//	private Caterer caterer2;
//	private List<Caterer> actualCaterers;
//	private List<Caterer> actualFilteredCaterers;
//
//
//	@Before
//	public void setUp() throws Exception { 
//		actualCaterers = new ArrayList<>();
//		actualFilteredCaterers = new ArrayList<>();
//		caterer1 = new Caterer(UUID.randomUUID(), "OrelMoisa", "orelmoisa@gmail.com", new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"), "chinese", new ArrayList<>(), "234");
//		caterer2 = new Caterer(UUID.randomUUID(), "JohnSmith", "johnsmith@gmail.com", new Address(1601, "clever", "CSL", "QC", "Canada", "H4L7I8"), "greek", new ArrayList<>(), "20190807");
//		MockitoAnnotations.initMocks(this);
//		actualCaterers.add(caterer1);
//		actualCaterers.add(caterer2);
//		actualFilteredCaterers.add(caterer2);
//	}
//	
//	@After
//	public void clear(){
//		actualCaterers.clear();
//		actualFilteredCaterers.clear();
//	}
//
//	@Test
//	public void MealServiceListTest() {
//
//		Mockito.when(catererRepository.findAll()).thenReturn(actualCaterers);
//		List<CatererTO> expectedCaterers = catererService.list();
//
//
//		Assert.assertEquals(expectedCaterers.size(), actualCaterers.size());
//
//	}
//	
//	@Test
//	public void MealServiceListByPredicateTest(){
//		qCaterer = new QCaterer("caterer");
//		Predicate predicate = qCaterer.specialty.eq("greek");
//
//		Mockito.when(catererRepository.findAll(predicate)).thenReturn(actualFilteredCaterers);
//		List<CatererTO> expectedCaterers = catererService.listByPredicate(predicate);
//
//		Assert.assertEquals(expectedCaterers.get(0).getSpecialty(),"greek");
//		Assert.assertNotEquals(expectedCaterers.size(), actualCaterers.size());
//	}
//	
//
//}
