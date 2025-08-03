package marketplace.controller;

import marketplace.api.HealthApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthApi {

    @Override
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Ok");
    }

}
