package entrance.service.api;

import entrance.web.dto.ApplicationDto;

import java.util.List;

public interface ApplicationService {

    ApplicationDto getRecentByContactId(Long contactId);

    List<ApplicationDto> findByProductName(String productName);
}
