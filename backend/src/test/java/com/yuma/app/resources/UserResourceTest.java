package com.yuma.app.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

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
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Preferences;
import com.yuma.app.repository.ConsumersRepository;
import com.yuma.app.to.ConsumerTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceTest {

	@MockBean
	ConsumersRepository consumerRepository;
	
	@Mock
	private Logger logger;
	
	@Mock
	ConversionService conversionService;

	@Autowired
	private ConsumerResource consumerResource;
	
	private Consumer consumer1;
	private Consumer consumer2;
	

	@Before
	public void setUp() throws Exception {

    consumer1 = new Consumer(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, new HashSet<>()), "20190807");
    consumer2 = new Consumer(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, new HashSet<>()), "20190807");
    
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllTest(){
		List<Consumer> actualConsumers = new ArrayList<>();
		actualConsumers.add(consumer1);
		actualConsumers.add(consumer2);

		Mockito.when(consumerRepository.findAll()).thenReturn(actualConsumers);
		Mockito.when(conversionService.convert(consumer1, ConsumerTO.class)).thenReturn(new ConsumerTO());
		Mockito.when(conversionService.convert(consumer2, ConsumerTO.class)).thenReturn(new ConsumerTO());

		List<ConsumerTO> expectedConsumers = consumerResource.getAll();

		Assert.assertEquals(expectedConsumers.size(),actualConsumers.size());
		
		
	}
	
}
