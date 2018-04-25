package entrance.service.impl;

import entrance.web.dto.ApplicationDto;
import entrance.domain.entity.Application;
import entrance.domain.entity.Contact;
import entrance.service.exception.ApplicationNotFoundException;
import entrance.service.exception.ContactNotFoundException;
import entrance.service.mapper.ApplicationMapper;
import entrance.domain.repository.api.ApplicationRepository;
import entrance.domain.repository.api.ContactRepository;
import entrance.service.api.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public ApplicationDto getRecentByContactId(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (!contact.isPresent()) {
            throw new ContactNotFoundException(contactId);
        }
        Application application = applicationRepository.findRecentByContactId(contact.get().getId());
        if (application == null) {
            throw new ApplicationNotFoundException(contactId);
        }
        return applicationMapper.entityToDto(application);
    }

    @Override
    public List<ApplicationDto> findByProductName(String productName) {
        return applicationRepository.findByProductName(productName).stream()
                .map(applicationMapper::entityToDto).collect(Collectors.toList());
    }
}
