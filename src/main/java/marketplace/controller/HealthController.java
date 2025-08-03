package marketplace.controller;

import marketplace.api.HealthApi;
import marketplace.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthApi {

    @Autowired
    private MongoDBService mongoDBService;

    @Override
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/health/mongodb")
    public ResponseEntity<String> mongodbHealth() {
        if (mongoDBService.isConnected()) {
            return ResponseEntity.ok(mongoDBService.getConnectionStatus());
        } else {
            return ResponseEntity.status(500).body("MongoDB connection failed");
        }
    }
}