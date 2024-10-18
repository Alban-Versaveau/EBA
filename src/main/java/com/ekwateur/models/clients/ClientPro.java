package com.ekwateur.models.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@Getter
@SuperBuilder
public class ClientPro extends Client {
    private final String siret;
    private final String raisonSociale;
    @JsonIgnore
    private final BigDecimal ca;
}
