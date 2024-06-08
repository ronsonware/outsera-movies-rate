package com.texoit.challenge.movies_reate.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerIntervalResult {
    private String producer;
    private Integer interval;
    private Integer followingWin;
    private Integer previousWin;

    @JsonIgnore
    private String flag;
}
