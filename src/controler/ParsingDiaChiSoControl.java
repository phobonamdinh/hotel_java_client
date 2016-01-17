package controler;

import model.Hotel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.ObjectUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienDQ on 1/15/16.
 */
public class ParsingDiaChiSoControl extends ParsingControl {

    public ParsingDiaChiSoControl(String link) {
        super(link);
    }

    @Override
    public Hotel parseHotel(Document document) {
        Hotel hotel = new Hotel();

        // set name
        Element titleElement = document.select("h1.detail_title").first();
        hotel.setName(titleElement.ownText());

        // set location's latitude
        Element latElement = document.select("meta[property=place:location:latitude]").first();
        if (latElement != null) {
            hotel.setLatitude(Double.parseDouble(latElement.attr("content")));
        }

        // set location's longitude
        Element longElement = document.select("meta[property=place:location:longitude]").first();
        if (longElement != null) {
            hotel.setLongitude(Double.parseDouble(longElement.attr("content")));
        }

        // set images
        Element imageElement = document.select("img.imgW100").first();
        if (imageElement != null){
            List<String> images = new ArrayList<>();
            images.add(imageElement.attr("src"));
            hotel.setImages(images);
        }

        // set contact information
        Element contactElements = document.select("ul.contact_list_info").first();
        Elements contacts = contactElements.select("li");

        if (contacts != null && contacts.size() > 0) {
            for (Element contact : contacts) {
                // set address
                Element address = contact.select("i.fa-map-marker").first();
                if (address != null) {
                    hotel.setAddress(contact.ownText());
                }

                // set phone
                Element phone = contact.select("i.fa-phone").first();
                if (phone != null) {
                    hotel.setPhone(contact.ownText());
                }

                // set website
                Element website = contact.select("i.fa-link").first();
                if (website != null) {
                    hotel.setWebsite(contact.ownText());
                }

                // set email
                Element email = contact.select("i.fa-envelope-o").first();
                if (email != null) {
                    hotel.setEmail(contact.ownText());
                }
            }
        }

        // set tags
        Element tagsElement = document.select("div.tags-cloud").first();
        if (tagsElement != null){
            Elements listTags = tagsElement.select("div.tag");
            if (listTags != null && listTags.size() > 0){
                List<String> tagString = new ArrayList<>();
                for (Element tag: listTags){
                    Element iElement = tag.select("i.fa-tags").first();
                    if (iElement == null){
                        Element aElement = tag.select("a").first();
                        if (aElement != null){
                            tagString.add(aElement.ownText());
                        }
                    }
                }
                hotel.setTags(tagString);
            }
        }

        System.out.println(ObjectUtil.toJson(hotel));
        return hotel;
    }
}
