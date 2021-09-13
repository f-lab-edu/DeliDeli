package flab.delideli.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingCriteria {

    private int pageNumber;
    private int startPage;
    private static int perPage = 10;

    public PagingCriteria(int pageNumber) {
        this.pageNumber = pageNumber;
        this.startPage = (pageNumber - 1) * perPage;
    }
}
