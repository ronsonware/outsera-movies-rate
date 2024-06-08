package com.texoit.challenge.movies_reate.factory;

import com.texoit.challenge.movies_reate.data.ProducerIntervalResult;

import java.util.Objects;

public class ProducerIntervalResultFactory {

    private static Integer PRODUCER_POS = 0;
    private static Integer INTERVAL_POS = 1;
    private static Integer FOLLOWING_WIN_POS = 2;
    private static Integer PREVIOUS_WIN_POS = 3;
    private static Integer FLAG_POS = 4;

    public static ProducerIntervalResult build(Object[] objects) {
        return ProducerIntervalResult.builder()
                .producer(getStringValueSafe(objects, PRODUCER_POS))
                .interval(getIntegerValueSafe(objects, INTERVAL_POS))
                .followingWin(getIntegerValueSafe(objects, FOLLOWING_WIN_POS))
                .previousWin(getIntegerValueSafe(objects, PREVIOUS_WIN_POS))
                .flag(getStringValueSafe(objects, FLAG_POS))
                .build();
    }

    private static String getStringValueSafe(Object[] objects, Integer position) {
        return Objects.nonNull(objects) && objects.length > position ? objects[position].toString() : null;
    }

    private static Integer getIntegerValueSafe(Object[] objects, Integer position) {
        String value = getStringValueSafe(objects, position);

        return Objects.nonNull(value) ? Integer.valueOf(value) : null;
    }
}
