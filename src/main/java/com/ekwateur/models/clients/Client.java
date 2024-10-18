package com.ekwateur.models.clients;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@Getter
@SuperBuilder
public abstract class Client {
    final String referenceClient;
    final BigDecimal consommationElectrique;
    final BigDecimal consommationGaz;
}
