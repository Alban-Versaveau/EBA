package com.ekwateur.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Builder
public record ConsommationClient(@NotNull @JsonProperty("consommationElectrique") BigDecimal consommationElectrique,
                                 @NotNull @JsonProperty("consommationGaz") BigDecimal consommationGaz) {
}
