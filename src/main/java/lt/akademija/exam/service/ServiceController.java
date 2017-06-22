package lt.akademija.exam.service;

import lt.akademija.exam.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Class to control calculation services
 * Created by Juozas on 2017-06-22.
 */
@RestController
public class ServiceController {

    @Autowired
    private ReportService reportService;

}
