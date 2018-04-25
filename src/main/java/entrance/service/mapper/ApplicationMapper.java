package entrance.service.mapper;

import entrance.web.dto.ApplicationDto;
import entrance.domain.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationDto entityToDto(Application application) {
        ApplicationDto dto = new ApplicationDto();
        dto.setApplicationId(application.getId());
        dto.setContactId(application.getContact().getId());
        dto.setCreatedDate(application.getCreatedDate());
        dto.setProductName(application.getProductName());

        return dto;
    }
}
