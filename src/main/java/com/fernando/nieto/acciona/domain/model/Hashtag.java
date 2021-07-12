package com.fernando.nieto.acciona.domain.model;

import lombok.Data;

@Data
public class Hashtag {

    private static final long serialVersionUID = 2890982093237935527L;

    private Long id;
    private String text;
    private Integer counter;

    public void merge(final Hashtag saved, final Hashtag toSave) {
        toSave.setId(saved.getId());
        toSave.setCounter(saved.getCounter() + 1);
    }
}
