package com.besthacks.tsp.controller;

import com.besthacks.tsp.domain.index.Index;
import com.besthacks.tsp.service.AirlyClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/indexes")
public class IndexController {

    private AirlyClient airlyClient;

    @GetMapping
    public List<Index> getIndexes() {
        return airlyClient.getIndexes();
    }

}
