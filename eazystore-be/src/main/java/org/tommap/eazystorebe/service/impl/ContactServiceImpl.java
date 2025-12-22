package org.tommap.eazystorebe.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tommap.eazystorebe.mapper.ContactMapper;
import org.tommap.eazystorebe.model.request.ContactRequest;
import org.tommap.eazystorebe.repository.ContactRepository;
import org.tommap.eazystorebe.service.IContactService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public boolean save(ContactRequest request) {
        try {
            contactRepository.save(contactMapper.toContact(request));

            return true;
        } catch (Exception ex) {
            log.error("Failed to save contact: {}", ex.getMessage(), ex);

            return false;
        }
    }
}
