package com.yuma.app.resources;
import java.util.List;

import com.yuma.app.document.Caterer;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yuma.app.repository.CatererRepository;

@Slf4j
@RestController
@RequestMapping("/caterer")
public class CatererResource {

    final Logger logger = LoggerFactory.getLogger("caterer Logger");

    private CatererRepository catererRepository;
    public CatererResource(CatererRepository catererRepository) {
        this.catererRepository= catererRepository;
    }

    @GetMapping("/get")
    public List<Caterer> getAll() {
        logger.info("retrieving caterer list from DB");
        return this.catererRepository.findAll();
    }
}
