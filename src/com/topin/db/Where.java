package com.topin.db;

import com.jayway.jsonpath.Criteria;

public class Where {
    private String key;
    private String value;

    private String operator;

    /**
     * @param key
     * @param operator
     * @param value
     */
    public Where(String key, String operator, String value) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    /**
     * @return Criteria
     */
    public Criteria getCriteria() {
        return Criteria.where(key).is(value);
    }
}
