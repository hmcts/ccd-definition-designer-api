package uk.gov.hmcts.ccd.definition.designer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.DefinitionEntity;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.DefinitionStatus;

import java.util.List;
import java.util.Optional;

@Component
public class DraftDefinitionRepositoryDecorator {

    private final DraftDefinitionRepository repository;

    @Autowired
    public DraftDefinitionRepositoryDecorator(DraftDefinitionRepository repository) {
        this.repository = repository;
    }

    public DefinitionEntity save(DefinitionEntity definitionEntity) {
        final Optional<Integer> version = repository.findLastVersion(definitionEntity.getJurisdiction().getReference());
        definitionEntity.setVersion(1 + version.orElse(0));
        if (definitionEntity.getStatus() == null) {
            definitionEntity.setStatus(DefinitionStatus.DRAFT);
        }
        return repository.save(definitionEntity);
    }

    public DefinitionEntity findByJurisdictionIdAndVersion(final String jurisdiction, final Integer version) {
        if (null == version) {
            return repository.findLatestByJurisdictionId(jurisdiction);
        }
        return repository.findByJurisdictionIdAndVersion(jurisdiction, version);
    }

    public List<DefinitionEntity> findByJurisdictionId(final String jurisdiction) {
        return repository.findByJurisdictionId(jurisdiction);
    }

    public DefinitionEntity simpleSave(final DefinitionEntity definitionEntity) {
        return repository.save(definitionEntity);
    }
}
