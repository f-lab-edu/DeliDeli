package flab.delideli.paging;

import lombok.Getter;

@Getter
public class PagingCriteria {

    private Integer cursor;
    private static int perPage = 10;

    public PagingCriteria(Integer cursor) {
        this.cursor = cursor;
    }
}
