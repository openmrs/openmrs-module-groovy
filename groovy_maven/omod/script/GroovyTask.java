package org.openmrs.module.groovy.api.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.api.db.GroovyDAO;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroovyTask extends AbstractTask {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void execute() {
		GroovyService groovyService = GroovyUtil.getService();
		log.info("Executing groovy task at " + new Date());
		Map<String, String> properties = this.getTaskDefinition().getProperties();
		String script = properties.get("script name");
		
		log.info("Groovy Script to be execute:" + script);
		GroovyScript groovyScript = groovyService.getScript(script);
		
		if (groovyScript != null){
			groovyService.evaluate(groovyScript.getScript());
		} else {
			log.error("Groovy Script not found: " + script);
		}
	}
	
}
