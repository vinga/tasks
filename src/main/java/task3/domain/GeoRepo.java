package task3.domain;

import org.springframework.data.geo.Circle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GeoRepo extends CrudRepository<Geo, String> {

    List<Geo> findByLocationWithin(Circle c);
}
