package com.example.cms.utils.common.infrastructure;

import com.example.cms.utils.common.service.port.UuidHolder;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class SystemUuidHolder implements UuidHolder {

    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }


}
