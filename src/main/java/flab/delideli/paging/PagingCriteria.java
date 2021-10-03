package flab.delideli.paging;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class PagingCriteria implements Serializable {

    private final Integer cursor;
    private static final int perPage = 10;

    public PagingCriteria(Integer cursor) {
        this.cursor = cursor;
    }
}
