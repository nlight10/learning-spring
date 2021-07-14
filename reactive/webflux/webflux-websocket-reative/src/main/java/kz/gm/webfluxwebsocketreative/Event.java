package kz.gm.webfluxwebsocketreative;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Daddy: GM
 * BirthDate: 14.07.2021
 */
@Data
@AllArgsConstructor
public class Event {
    private String eventId;
    private String eventDt;
}
