package com.devsuperior.dscatalog.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class fileDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private MultipartFile file;
	
	public fileDTO() {
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
