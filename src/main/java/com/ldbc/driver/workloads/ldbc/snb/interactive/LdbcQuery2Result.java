package com.ldbc.driver.workloads.ldbc.snb.interactive;

public class LdbcQuery2Result {
    private final long personId;
    private final String personFirstName;
    private final String personLastName;
    private final long postOrCommentId;
    private final String postOrCommentContent;
    private final long postOrCommentCreationDate;

    public LdbcQuery2Result(long personId, String personFirstName, String personLastName, long postOrCommentId, String postOrCommentContent, long postOrCommentCreationDate) {
        this.personId = personId;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.postOrCommentId = postOrCommentId;
        this.postOrCommentContent = postOrCommentContent;
        this.postOrCommentCreationDate = postOrCommentCreationDate;
    }

    public long personId() {
        return personId;
    }

    public String personFirstName() {
        return personFirstName;
    }

    public String personLastName() {
        return personLastName;
    }

    public long postOrCommentId() {
        return postOrCommentId;
    }

    public String postOrCommentContent() {
        return postOrCommentContent;
    }

    public long postOrCommentCreationDate() {
        return postOrCommentCreationDate;
    }

    @Override
    public String toString() {
        return "LdbcQuery2Result{" +
                "personId=" + personId +
                ", personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", postOrCommentId=" + postOrCommentId +
                ", postOrCommentContent='" + postOrCommentContent + '\'' +
                ", postOrCommentCreationDate=" + postOrCommentCreationDate +
                '}';
    }
}
