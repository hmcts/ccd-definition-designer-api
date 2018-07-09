package uk.gov.hmcts.ccd.definition.store.elastic.mapping;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import uk.gov.hmcts.ccd.definition.store.elastic.mapping.type.TypeMappingGenerator;
import uk.gov.hmcts.ccd.definition.store.repository.entity.FieldEntity;

public class StubTypeMappingGenerator extends TypeMappingGenerator {

    private String dataMapping;
    private String dataClassificationMapping;
    private String type;

    public StubTypeMappingGenerator(String type, String dataMapping, String dataClassificationMapping) {
        this.type = type;
        this.dataMapping = dataMapping;
        this.dataClassificationMapping = dataClassificationMapping;
    }

    public String dataMapping(FieldEntity field) {
        return dataMapping;
    }

    public String dataClassificationMapping(FieldEntity field) {
        return dataClassificationMapping;
    }

    public List<String> getCcdTypes() {
        return newArrayList(type);
    }
}
