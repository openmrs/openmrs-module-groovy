package org.openmrs.module.groovy.db.hibernate;


import org.openmrs.module.groovy.GroovyScript;

import java.util.List;

public interface GroovyModuleDAO {

    List<GroovyScript> getAllScripts();

    GroovyScript getScriptById(Integer id);

    GroovyScript saveGroovyScript(GroovyScript script);

    void deleteGroovyScript(GroovyScript script);
}
