package com.yuma.app.service;

import com.querydsl.core.types.Predicate;
import com.yuma.app.document.Caterer;
import com.yuma.app.repository.CatererRepository;
import com.yuma.app.to.CatererTO;
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

	public List<CatererTO> listByPredicate(Predicate predicate) {

		List<CatererTO> catererTos = new ArrayList<>();
		List<Caterer> catererList = Helper.toCatererList(catererRepository.findAll(predicate));

		for (Caterer caterer: catererList) {
			catererTos.add(conversionService.convert(caterer, CatererTO.class));
		}
		return catererTos;
	}

	public List<CatererTO> list() {

		List<CatererTO> catererTos = new ArrayList<>();
		List<Caterer> catererList = catererRepository.findAll();

		for (Caterer meal : catererList) {
			catererTos.add(conversionService.convert(meal, CatererTO.class));
		}
		return catererTos;
	}

	public CatererTO update(CatererTO catererTo) {

		Caterer caterer = catererRepository.findOne(catererTo.getUserId());

		if (caterer == null) {
			throw new IllegalArgumentException("Entity doesn't exist in the database");
		}

		Caterer catererToUpdate = conversionService.convert(catererTo, Caterer.class);
		caterer.updateFrom(catererToUpdate);
		caterer = catererRepository.save(caterer);
		return conversionService.convert(caterer, CatererTO.class);
	}

	public void deleteCaterer(UUID catererId){
		catererRepository.delete(catererRepository.findOne(catererId));
	}
}


