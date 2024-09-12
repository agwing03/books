package project.books.sys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";
	
	/**
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//addPathPatterns 인터셉터에서 경로 추가 허용할때 
		//excludePathPatterns 인터셉터에서 경로 제외할때
        registry.addInterceptor(new LoggerInterceptor())
        .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**","/images/**");
	}
	*/

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // '/'포함된 모든 리소스
				.addResourceLocations("classpath:/templates/", "classpath:/static/"); // view 위치 추가
	}
    
//	
//    @Override
//    public void addCorsMappings(final CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
//                .exposedHeaders(HttpHeaders.LOCATION);
//    }
//    
}
