/**
 * This package contains JAXB classes.
 */
@XmlJavaTypeAdapter(value = LocalDateAdapter.class, type = LocalDate.class)
package ee.klab.water.web.model;

import ee.klab.water.web.LocalDateAdapter;
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
