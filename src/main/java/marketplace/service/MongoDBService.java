package marketplace.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MongoDBService {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBService.class);

    @Autowired
    private MongoClient mongoClient;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    /**
     * Get MongoDB database connection
     * @return MongoDatabase instance
     */
    public MongoDatabase getDatabase() {
        return mongoClient.getDatabase(databaseName);
    }

    /**
     * Get MongoDB client connection
     * @return MongoClient instance
     */
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    /**
     * Check MongoDB connectivity
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        try {
            // Try to get database stats to test connectivity
            mongoClient.getDatabase(databaseName).runCommand(new org.bson.Document("ping", 1));
            logger.info("MongoDB connection successful");
            return true;
        } catch (Exception e) {
            logger.error("MongoDB connection failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Get connection status with details
     * @return Connection status message
     */
    public String getConnectionStatus() {
        try {
            MongoDatabase database = getDatabase();
            database.runCommand(new org.bson.Document("ping", 1));
            return String.format("Connected to MongoDB database: %s", databaseName);
        } catch (Exception e) {
            return String.format("Failed to connect to MongoDB: %s", e.getMessage());
        }
    }
}