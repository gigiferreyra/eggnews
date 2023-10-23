
import com.noticiasegg.news.NewsApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ServletInitializer extends SpringBootServletInitializer {

        @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NewsApplication.class);
	}

}