package com.yuma.app.resources;

import com.querydsl.core.types.Predicate;
import com.yuma.app.document.Caterer;
import com.yuma.app.document.QCaterer;
import com.yuma.app.document.QMeal;
import com.yuma.app.repository.CatererRepository;
import com.yuma.app.service.CatererService;
import com.yuma.app.to.CatererTo;
import com.yuma.app.to.MealTo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/caterer")
public class CatererResource {

	final Logger logger = LoggerFactory.getLogger("caterer Logger");
	private CatererService catererService;

	@Autowired
	public CatererResource(CatererService catererService) {
		this.catererService = catererService;
	}

	private CatererRepository catererRepository;

	public CatererResource(CatererRepository catererRepository) {
		this.catererRepository = catererRepository;
	}

	@GetMapping("/get")
	public List<CatererTo> getAll() {
		logger.info("retrieving caterer list from DB");
		return this.catererService.list();
	}

	@GetMapping("/{specialty}")
	public List<CatererTo> getByDescription(@PathVariable String specialty) {
		QCaterer qCaterer = new QCaterer("caterer");
		Predicate predicate = qCaterer.specialty.eq(specialty);
		logger.info("retrieving caterer list from DB by specialty {}", specialty);
		return this.catererService.listByPredicate(predicate);
	}

	@PutMapping()
	public CatererTo update(@RequestBody CatererTo catererTo) {
		logger.info("updating meal into the database");
		return this.catererService.update(catererTo);
	}

	@DeleteMapping("/delete/{catererId}")
	public void deleteMeal(@PathVariable UUID catererId){
		logger.info("deleting meal with mealId {}", catererId);
		this.catererService.deleteCaterer(catererId);
	}	
}
