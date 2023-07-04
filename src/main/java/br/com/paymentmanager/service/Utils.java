package br.com.paymentmanager.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Utils {

    public static String inserirAnoMes(String data) {
        if(data == null) {
            Date dataAtual = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            return dateFormat.format(dataAtual);
        }
        return data;
    }

}
