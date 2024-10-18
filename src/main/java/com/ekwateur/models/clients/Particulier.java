package com.ekwateur.models.clients;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Getter
@SuperBuilder
public class Particulier extends Client {
    private final String civilite;
    private final String nom;
    private final String prenom;
}
