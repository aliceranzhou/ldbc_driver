package com.ldbc.driver.workloads.ldbc.snb.interactive;

import com.ldbc.driver.Operation;

import java.util.List;

public class LdbcQuery10 extends Operation<List<LdbcQuery10Result>> {
    public static final int DEFAULT_LIMIT = 20;
    private final long personId;
    private final String personUri;
    private final int month1;
    private final int month2;
    private final int limit;

    public LdbcQuery10(long personId, String personUri, int month1, int month2, int limit) {
        this.personId = personId;
        this.personUri = personUri;
        this.month1 = month1;
        this.month2 = month2;
        this.limit = limit;
    }

    public long personId() {
        return personId;
    }

    public String personUri() {
        return personUri;
    }

    public int month1() {
        return month1;
    }

    public int month2() {
        return month2;
    }

    public int limit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LdbcQuery10 that = (LdbcQuery10) o;

        if (limit != that.limit) return false;
        if (month1 != that.month1) return false;
        if (month2 != that.month2) return false;
        if (personId != that.personId) return false;
        if (personUri != null ? !personUri.equals(that.personUri) : that.personUri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personId ^ (personId >>> 32));
        result = 31 * result + (personUri != null ? personUri.hashCode() : 0);
        result = 31 * result + month1;
        result = 31 * result + month2;
        result = 31 * result + limit;
        return result;
    }

    @Override
    public String toString() {
        return "LdbcQuery10{" +
                "personId=" + personId +
                ", personUri='" + personUri + '\'' +
                ", month1=" + month1 +
                ", month2=" + month2 +
                ", limit=" + limit +
                '}';
    }
}