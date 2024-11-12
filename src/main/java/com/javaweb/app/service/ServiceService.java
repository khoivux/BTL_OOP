package com.javaweb.app.service;


import com.javaweb.app.dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    List<ServiceDTO> findAll();
}
