package uk.gov.hmcts.ccd.definition.designer.repository;

import uk.gov.hmcts.ccd.definition.designer.repository.entity.Versionable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class VersionedDefinitionRepositoryDecorator<T extends Versionable, I extends Serializable>
    extends AbstractDefinitionRepositoryDecorator<T, I, VersionedDefinitionRepository<T, I>> {

    public VersionedDefinitionRepositoryDecorator(VersionedDefinitionRepository repository) {
        super(repository);
    }

    @Override
    public <S extends T> S save(S s) {
        final Optional<Integer> version = repository.findLastVersion(s.getReference());
        s.setVersion(1 + version.orElse(0));
        return repository.save(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        for (S s : iterable) {
            final Optional<Integer> version = repository.findLastVersion(s.getReference());
            s.setVersion(1 + version.orElse(0));
        }
        return repository.save(iterable);
    }
}
