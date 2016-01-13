package controler;

import model.Hotel;

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
        Hotel hotel = ParsingControl.parseHotel(mLinkToParsing);
    }
}
