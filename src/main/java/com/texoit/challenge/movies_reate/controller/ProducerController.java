package com.texoit.challenge.movies_reate.controller;

import com.texoit.challenge.movies_reate.data.WinnerProducerIntervalResponse;
import com.texoit.challenge.movies_reate.service.ProducerRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    @Autowired
    private ProducerRateService service;

    @GetMapping("rate-winners")
    public ResponseEntity<WinnerProducerIntervalResponse> rateWinners() {
        return ResponseEntity.ok(service.rateWinners());
    }

}
