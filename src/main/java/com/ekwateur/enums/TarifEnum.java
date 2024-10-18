package com.ekwateur.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum TarifEnum {
    TARIF_PARTICULIER(new BigDecimal("0.121"), new BigDecimal("0.115")),
    TARIF_CLIENT_PRO_PETIT_CA(new BigDecimal("0.114"), new BigDecimal("0.111")),
    TARIF_CLIENT_PRO_GRAND_CA(new BigDecimal("0.118"), new BigDecimal("0.113"));

    private final BigDecimal tarifElectricite;
    private final BigDecimal tarifGaz;

    public static TarifEnum obtenirTarif() {
        return TARIF_PARTICULIER;
    }

    public static TarifEnum obtenirTarif(BigDecimal ca) {
        return (ca.compareTo(new BigDecimal("1000000")) < 0) ? TARIF_CLIENT_PRO_GRAND_CA : TARIF_CLIENT_PRO_PETIT_CA;
    }
}
