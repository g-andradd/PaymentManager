package br.com.paymentmanager.service;

import br.com.paymentmanager.dto.RegistroUploadFileDto;
import br.com.paymentmanager.model.RegistrosUploadFile;
import br.com.paymentmanager.repository.RegistrosUploadFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrosUploadFileService {

    private final RegistrosUploadFileRepository registrosUploadFileRepository;

    public RegistrosUploadFileService(RegistrosUploadFileRepository registrosUploadFileRepository) {
        this.registrosUploadFileRepository = registrosUploadFileRepository;
    }

    @Transactional
    public RegistroUploadFileDto inserir(RegistrosUploadFile ruf) {
        registrosUploadFileRepository.save(ruf);

        return new RegistroUploadFileDto(ruf);
    }
}
