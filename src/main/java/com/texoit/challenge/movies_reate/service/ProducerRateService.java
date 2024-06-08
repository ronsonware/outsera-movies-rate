package com.texoit.challenge.movies_reate.service;

import com.texoit.challenge.movies_reate.data.ProducerIntervalResult;
import com.texoit.challenge.movies_reate.data.WinnerProducerIntervalResponse;
import com.texoit.challenge.movies_reate.factory.ProducerIntervalResultFactory;
import com.texoit.challenge.movies_reate.factory.WinnerProducerIntervalResponseFactory;
import com.texoit.challenge.movies_reate.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerRateService {

    @Autowired
    private MovieRepository repository;

    public WinnerProducerIntervalResponse rateWinners() {
        List<ProducerIntervalResult> winners = repository.findCustomQuery().stream()
                .map(ProducerIntervalResultFactory::build)
                .toList();

        return WinnerProducerIntervalResponseFactory.build(winners);
    }
}
