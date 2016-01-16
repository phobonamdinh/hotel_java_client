package controler;

import model.Hotel;
import org.jsoup.nodes.Document;

/**
 * Created by TienDQ on 1/15/16.
 */
public class ParsingBookingControl extends ParsingControl {

    public ParsingBookingControl(String link) {
        super(link);
    }

    @Override
    public Hotel parseHotel(Document document) {
        return null;
    }
}
