package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.RegistroUploadFileDto;
import br.com.paymentmanager.model.RegistrosUploadFile;
import br.com.paymentmanager.repository.RegistrosUploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrosUploadFileService {

    @Autowired
    private RegistrosUploadFileRepository registrosUploadFileRepository;

    public RegistroUploadFileDto inserir(RegistrosUploadFile ruf) {
        registrosUploadFileRepository.save(ruf);

        return new RegistroUploadFileDto(ruf);
    }
}
