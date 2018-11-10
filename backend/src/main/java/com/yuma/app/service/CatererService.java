package com.yuma.app.service;

import com.querydsl.core.types.Predicate;
import com.yuma.app.document.Caterer;
import com.yuma.app.repository.CatererRepository;
import com.yuma.app.to.CatererTo;
import com.yuma.app.util.Helper;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CatererService {
	private CatererRepository catererRepository;
	private ConversionService conversionService;

	public CatererService(CatererRepository catererRepository, ConversionService conversionService) {
		this.catererRepository = catererRepository;
		this.conversionService = conversionService;
	}

	public List<CatererTo> listByPredicate(Predicate predicate) {

		List<CatererTo> catererTos = new ArrayList<>();
		List<Caterer> catererList = Helper.toCatererList(catererRepository.findAll(predicate));

		for (Caterer caterer: catererList) {
			catererTos.add(conversionService.convert(caterer, CatererTo.class));
		}
		return catererTos;
	}

	public List<CatererTo> list() {

		List<CatererTo> catererTos = new ArrayList<>();
		List<Caterer> catererList = catererRepository.findAll();

		for (Caterer meal : catererList) {
			catererTos.add(conversionService.convert(meal, CatererTo.class));
		}
		return catererTos;
	}

	public CatererTo update(CatererTo catererTo) {

		Caterer caterer = catererRepository.findOne(catererTo.getUserId());

		if (caterer == null) {
			throw new IllegalArgumentException("Entity doesn't exist in the database");
		}

		Caterer catererToUpdate = conversionService.convert(catererTo, Caterer.class);
		caterer.updateFrom(catererToUpdate);
		caterer = catererRepository.save(caterer);
		return conversionService.convert(caterer, CatererTo.class);
	}

	public void deleteCaterer(UUID catererId){
		catererRepository.delete(catererRepository.findOne(catererId));
	}
}


