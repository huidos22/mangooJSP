package com.huidos.mangooo.configuration;

/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.webresources.StandardRoot;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.huidos.mangooo.validator.ClienteFormValidator;
import com.huidos.mangooo.validator.EmailValidator;
import com.huidos.mangooo.validator.GastoCorteFormValidator;
import com.huidos.mangooo.validator.ProductoFormValidator;
import com.huidos.mangooo.validator.UsuarioFormValidator;
import com.huidos.mangooo.validator.VentaFormValidator;
import com.huidos.mangooo.viewresolvers.ExcelViewResolver;
import com.huidos.mangooo.viewresolvers.PdfViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.huidos.mangooo.configuration,com.huidos.mangooo.validator")
@Import(value = { MangoooSecurityConfig.class })
public class MangoooConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/js/**").addResourceLocations("js/");
		registry.addResourceHandler("/css/**").addResourceLocations("css/");
		registry.addResourceHandler("/images/**").addResourceLocations("images/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("fonts/");
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	/*
	 * Configure ContentNegotiationManager
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML).useJaf(false).mediaType("xls",
				new MediaType("application", "vnd.ms-excel"));
	}

	/*
	 * Configure ContentNegotiatingViewResolver
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		resolvers.add(jsonViewResolver());
		resolvers.add(excelViewResolver());
		resolvers.add(jspViewResolver());
		resolvers.add(pdfViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/*
	 * Configure View resolver to provide JSON output using JACKSON library to
	 * convert object in JSON format.
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return new com.huidos.mangooo.viewresolvers.JsonViewResolver();
	}

	/*
	 * Configure View resolver to provide PDF output using lowagie pdf library
	 * to generate PDF output for an object content
	 */
	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}

	/*
	 * Configure View resolver to provide XLS output using Apache POI library to
	 * generate XLS output for an object content
	 */
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	/*
	 * Configure View resolver to provide HTML output This is the default format
	 * in absence of any type suffix.
	 */
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	public UsuarioFormValidator usuarioFormValidator() {
		return new UsuarioFormValidator();
	}

	@Bean
	public ClienteFormValidator clienteFormValidator() {
		return new ClienteFormValidator();
	}

	@Bean
	public ProductoFormValidator productoFormValidator() {
		return new ProductoFormValidator();
	}

	@Bean
	public EmailValidator emailFormValidator() {
		return new EmailValidator();
	}

	@Bean
	public VentaFormValidator ventaFormValidator() {
		return new VentaFormValidator();
	}

	@Bean
	public GastoCorteFormValidator GastoCorteFormValidator() {
		return new GastoCorteFormValidator();
	}

	/**
	 * We’ll then define the ModelMapper bean in our Spring configuration:
	 * 
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("validation");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatFactory.addContextCustomizers((context) -> {
			StandardRoot standardRoot = new StandardRoot(context);
			standardRoot.setCacheMaxSize(40 * 1024);
		});
		return tomcatFactory;
	}
}
