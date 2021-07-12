package com.fernando.nieto.acciona.domain.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetFilter implements Serializable {

    private static final long serialVersionUID = -1438762683907541046L;

    private Integer pageSize;
    private Integer pageNumber;
    private String userName;
    private Boolean validated;
}