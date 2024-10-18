package com.ekwateur.models;

import com.ekwateur.models.clients.Client;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Facture(Client client,
                      BigDecimal montantDu) {
}
