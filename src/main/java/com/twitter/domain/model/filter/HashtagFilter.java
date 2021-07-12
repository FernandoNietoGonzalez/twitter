package com.twitter.domain.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashtagFilter implements Serializable {

    private static final long serialVersionUID = -7900727454955366170L;

    private Integer pageSize;
    private Integer pageNumber;
    private OrderEnum orderBy;

}
