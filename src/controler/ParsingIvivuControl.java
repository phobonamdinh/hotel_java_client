package controler;

import model.Hotel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienDQ on 1/17/16.
 */
public class ParsingIvivuControl extends ParsingControl {
    public ParsingIvivuControl(String mLink) {
        super(mLink);
    }

    @Override
    public Hotel parseHotel(Document document) {
        Hotel hotel = new Hotel();

        // set name
        Element nameElement = document.select("h1#hotel-name-detail").first();
        if (nameElement != null){
            hotel.setName(nameElement.ownText());
        }

        // set address
        Element addressElement = document.select("span.htldtl-address").first();
        if (addressElement != null){
            hotel.setAddress(addressElement.ownText());
        }

        // set location
        Element locationElement = document.select("div#gmap3-right").first();
        if (locationElement != null){
            hotel.setLatitude(Double.parseDouble(locationElement.attr("data-lat")));
            hotel.setLongitude(Double.parseDouble(locationElement.attr("data-lng")));
        }

        // set images
        Element imagesElement = document.select("div.jslides").first();
        if (imagesElement != null){
            Elements imgs = imagesElement.select("img[u=image]");
            if (imgs != null && imgs.size() > 0){
                List<String> imagesLink = new ArrayList<>();
                for (Element img: imgs){
                    imagesLink.add("https:" + img.attr("src"));
                }
                hotel.setImages(imagesLink);
            }
        }

        return hotel;
    }
}
