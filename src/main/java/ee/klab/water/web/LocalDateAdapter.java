package ee.klab.water.web;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.toString();
    }

    @Override
    public LocalDate unmarshal(String string) throws Exception {
        return LocalDate.parse(string);
    }

}
