package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.EmailDTO;

public interface EmailService {

	void sendEmail(EmailDTO dto);
}
