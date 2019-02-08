package uk.gov.hmcts.ccd.definition.designer.repository.entity;

public interface Versionable extends Referencable {

    Integer getVersion();

    void setVersion(Integer version);
}
