package org.tommap.eazystorebe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tommap.eazystorebe.model.entity.Contact;
import org.tommap.eazystorebe.model.request.ContactRequest;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "createdBy", source = "name")
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    Contact toContact(ContactRequest request);
}
