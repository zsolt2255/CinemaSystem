package com.topin.db;

import com.jayway.jsonpath.Filter;

import java.util.List;

public class QueryFilter extends Filter {

    List<Where> whereList;

    /**
     * @param whereList
     */
    public QueryFilter(List<Where> whereList) {
        this.whereList = whereList;
    }

    /**
     * @param predicateContext
     * @return boolean
     */
    @Override
    public boolean apply(PredicateContext predicateContext) {
        return false;
    }
}
