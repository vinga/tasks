package task3.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Geo {
    @Id
    private String clientId;
    double[] location; //longitude, latitude
    private long timestamp;
    private Double accuracy;


    public Geo() {

    }
    public Geo(String clientId, double[] loc) {
        this.clientId=clientId;
        this.location=loc;
        this.timestamp=new Date().getTime();
    }

    public double getLongitude() {
        return location[0];
    }
    public double getLatitude() {
        return location[1];
    }
}
