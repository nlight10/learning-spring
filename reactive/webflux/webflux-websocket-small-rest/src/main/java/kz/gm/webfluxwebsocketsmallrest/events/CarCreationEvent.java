package kz.gm.webfluxwebsocketsmallrest.events;

import lombok.Data;

/**
 * Daddy: GM
 * BirthDate: 14.07.2021
 */
@Data
public class CarCreationEvent {

    private Long carId;
    private String creationTime;

    public CarCreationEvent(Long carId, String creationTime) {
        super();
        this.carId = carId;
        this.creationTime = creationTime;
    }

}
