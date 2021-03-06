package uk.gov.hmcts.ccd.definition.designer.domain.service;

import uk.gov.hmcts.ccd.definition.designer.domain.service.response.ServiceResponse;
import uk.gov.hmcts.ccd.definition.designer.repository.model.Definition;

import java.util.List;

public interface DefinitionService {

    ServiceResponse<Definition> createDraftDefinition(Definition definition);

    Definition findByJurisdictionIdAndVersion(String jurisdiction, Integer version);

    List<Definition> findByJurisdictionId(String jurisdiction);

    ServiceResponse<Definition> saveDraftDefinition(Definition definition);

    void deleteDraftDefinition(String jurisdiction, Integer version);
}
