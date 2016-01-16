package controler;

import model.Hotel;
import util.Config;

/**
 * Created by TienDQ on 1/13/16.
 */
public class ParsingThread implements Runnable {
    private String mLinkToParsing;
    private IOnParsingControlState mOnParsingControlState;

    public ParsingThread(String linkToParsing, IOnParsingControlState onParsingControlState) {
        this.mLinkToParsing = linkToParsing;
        this.mOnParsingControlState = onParsingControlState;
    }

    @Override
    public void run() {
        if (mOnParsingControlState != null) {
            mOnParsingControlState.onParsingStart(mLinkToParsing);

            ParsingControl parsingControl = null;
            if (mLinkToParsing.contains(Config.BASE_DOMAIN_DIACHISO)) {
                parsingControl = new ParsingDiaChiSoControl(mLinkToParsing);
            } else if (mLinkToParsing.contains(Config.BASE_DOMAIN_BOOKING)) {
                parsingControl = new ParsingBookingControl(mLinkToParsing);
            }

            if (parsingControl != null) {
                mOnParsingControlState.onParsingDoneWithAnObject(mLinkToParsing, parsingControl.parseHotel());
            }
        }
    }
}
