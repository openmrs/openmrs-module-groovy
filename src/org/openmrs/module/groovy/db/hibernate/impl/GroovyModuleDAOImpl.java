package org.openmrs.module.groovy.db.hibernate.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.db.hibernate.GroovyModuleDAO;

public class GroovyModuleDAOImpl implements GroovyModuleDAO {
     private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<GroovyScript> getAllScripts() {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(GroovyScript.class);
        crit.addOrder(Order.asc("name"));        
        return (List<GroovyScript>)crit.list();
    }

    public GroovyScript getScriptById(Integer id) {
        return (GroovyScript) sessionFactory.getCurrentSession().get(GroovyScript.class, id);
    }

    public GroovyScript saveGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().saveOrUpdate(script);
        System.out.println(script.getModifiedBy());
        System.out.println(script.getModified());
        return script;
    }

    public void deleteGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().delete(script);
    }
}
