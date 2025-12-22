package org.tommap.eazystorebe.service;

import org.tommap.eazystorebe.model.request.ContactRequest;

public interface IContactService {
    boolean save(ContactRequest request);
}
