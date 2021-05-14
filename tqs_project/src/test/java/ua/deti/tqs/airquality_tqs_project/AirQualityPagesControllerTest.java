package ua.deti.tqs.airquality_tqs_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import ua.deti.tqs.airquality_tqs_project.controller.PagesController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AirQualityPagesControllerTest {

    @Autowired
    private PagesController pagesController;

    @Test
    public void whenGoodTypeParam_ThenAModelAndViewObjectIsCreated() {
        assertThat(pagesController.Data("Aveiro")).isInstanceOf(ModelAndView.class); // remember that when you could obtain an AirQuality object that isn't null, it means that you
        // actually reached some kind of results and a Model&View object was created, these results were added to this Model&View object and a new page was returned, with the information stored
        // in this Model&View object
    }
    // some invalid/empty searches
    @Test
    public void whenNumericTypeParam_ThenReturn404ErrorPage() {
        assertThat(pagesController.Data("12345")).isEqualTo("error404");
    }

    @Test
    public void whenEmptySearch_ThenReturn404ErrorPageEmptySearch() {
        assertThat(pagesController.Data("")).isEqualTo("emptySearch");
    }

    @Test
    public void whenBadTypeParam_ThenReturn404ErrorPage() {
        assertThat(pagesController.Data("48q3fg849g7f0q")).isEqualTo("error404");
    }
}
