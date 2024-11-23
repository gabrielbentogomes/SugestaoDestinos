package com.onthego.sugestaocidades.SugestaoDestinos.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Attraction {
    private String name_attraction;
    private String description_attraction;
}
