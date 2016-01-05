package com.nexmo.hm.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Sunny Ghosh
 *
 */

public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	 
	    @Override
	    protected Class<?>[] getRootConfigClasses() {
	    	return new Class[]{};
	    }
	  
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	    	 return new Class[] { WebMvcConfig.class };
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }
}
