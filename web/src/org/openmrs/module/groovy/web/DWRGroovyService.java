package org.openmrs.module.groovy.web;

import org.openmrs.module.groovy.GroovyUtil;

public class DWRGroovyService {


    public String eval(String script) {
        return GroovyUtil.evaluate(script);
    }
}
