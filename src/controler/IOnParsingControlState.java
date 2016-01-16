package controler;

import model.Hotel;

/**
 * Created by TienDQ on 1/17/16.
 */
public interface IOnParsingControlState {
    void onParsingStart(String linkParsing);
    void onParsingDoneWithAnObject(String linkParsing, Hotel hotel);
    void onParsingFinish();
}
