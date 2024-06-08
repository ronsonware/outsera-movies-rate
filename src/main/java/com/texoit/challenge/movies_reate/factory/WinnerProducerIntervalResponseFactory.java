package com.texoit.challenge.movies_reate.factory;

import com.texoit.challenge.movies_reate.data.ProducerIntervalResult;
import com.texoit.challenge.movies_reate.data.WinnerProducerIntervalResponse;

import java.util.List;

public class WinnerProducerIntervalResponseFactory {

    private static String MIN_FLAG = "min";
    private static String MAX_FLAG = "max";

    public static WinnerProducerIntervalResponse build(List<ProducerIntervalResult> results) {
        return WinnerProducerIntervalResponse.builder()
                .min(results.stream().filter(winner -> winner.getFlag().equals(MIN_FLAG)).toList())
                .max(results.stream().filter(winner -> winner.getFlag().equals(MAX_FLAG)).toList())
                .build();
    }
}
