package task3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import task3.domain.Geo;
import task3.domain.GeoRepo;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Component
    public class Initializer implements CommandLineRunner {

        @Autowired
        private MongoTemplate mongoTemplate;

        @Override
        public void run(String... args) throws Exception {
            mongoTemplate.getDb().dropDatabase();
            mongoTemplate.indexOps(Geo.class).ensureIndex(new GeospatialIndex("location"));

            mongoTemplate.insert(new Geo("client1", new double[]{0, -0}));
            mongoTemplate.insert(new Geo("client2", new double[]{15, -40}));
        }
    }

    @RestController
    class GeoFetch {

        @Autowired
        private MongoTemplate mongoTemplate;

        @Autowired
        private GeoRepo geoRepo;

        @RequestMapping(method = RequestMethod.POST, path = "/locations/{clientId}")
        public void post(@PathVariable("clientId") String clientId, Geo geo) {
            geo.setClientId(clientId);
            mongoTemplate.insert(geo);
        }

        // for testing
        @RequestMapping(path = "/locations")
        public Iterable<Geo> getAll() {
            return geoRepo.findAll();
        }
    }
}


