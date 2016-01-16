package controler;

import model.Hotel;

/**
 * Created by TienDQ on 1/13/16.
 */
public abstract class ParsingControl {
    private String mLink;

    public ParsingControl(String mLink) {
        this.mLink = mLink;
    }

    public abstract Hotel parseHotel();
}
