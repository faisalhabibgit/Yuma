package com.yuma.app.resources;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.yuma.app.document.QCaterer;
import com.yuma.app.service.CatererService;
import com.yuma.app.to.CatererTO;

@Slf4j
@RestController
@RequestMapping("api/caterer")
public class CatererResource {

	final Logger logger = LoggerFactory.getLogger("caterer Logger");
	private CatererService catererService;

	@Autowired
	public CatererResource(CatererService catererService) {
		this.catererService = catererService;
	}

	@GetMapping("/get")
	public List<CatererTO> getAll() {
		logger.info("retrieving caterer list from DB");
		return this.catererService.list();
	}

	@GetMapping("/{specialty}")
	public List<CatererTO> getByDescription(@PathVariable String specialty) {
		QCaterer qCaterer = new QCaterer("caterer");
		Predicate predicate = qCaterer.specialty.eq(specialty);
		logger.info("retrieving caterer list from DB by specialty {}", specialty);
		return this.catererService.listByPredicate(predicate);
	}

	@PutMapping()
	public CatererTO update(@RequestBody CatererTO catererTo) {
		logger.info("updating meal into the database");
		return this.catererService.update(catererTo);
	}

	@DeleteMapping("/delete/{catererId}")
	public void deleteMeal(@PathVariable UUID catererId){
		logger.info("deleting meal with mealId {}", catererId);
		this.catererService.deleteCaterer(catererId);
	}	
}
