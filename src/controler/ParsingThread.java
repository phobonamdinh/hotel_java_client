package controler;

import model.Hotel;
import util.Config;

/**
 * Created by TienDQ on 1/13/16.
 */
public class ParsingThread implements Runnable {
    private String mLinkToParsing;

    public ParsingThread(String linkToParsing) {
        this.mLinkToParsing = linkToParsing;
    }

    @Override
    public void run() {
        ParsingControl parsingControl = null;
        if (mLinkToParsing.contains(Config.BASE_DOMAIN_DIACHISO)){
            parsingControl = new ParsingDiaChiSoControl(mLinkToParsing);
        } else if (mLinkToParsing.contains(Config.BASE_DOMAIN_BOOKING)){
            parsingControl = new ParsingBookingControl(mLinkToParsing);
        }

        if (parsingControl != null){
            Hotel hotel = parsingControl.parseHotel();

        }
    }
}
