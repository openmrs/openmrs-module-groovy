package org.openmrs.module.groovy.db.hibernate.impl;

import org.openmrs.module.groovy.db.hibernate.GroovyModuleDAO;
import org.openmrs.module.groovy.GroovyScript;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import java.util.List;

public class GroovyModuleDAOImpl implements GroovyModuleDAO {
     private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<GroovyScript> getAllScripts() {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(GroovyScript.class);
        crit.addOrder(Order.asc("name"));        
        return (List<GroovyScript>)crit.list();
    }

    @Override
    public GroovyScript getScriptById(Integer id) {
        return (GroovyScript) sessionFactory.getCurrentSession().get(GroovyScript.class, id);
    }

    @Override
    public GroovyScript saveGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().saveOrUpdate(script);
        System.out.println(script.getModifiedBy());
        System.out.println(script.getModified());
        return script;
    }

    @Override
    public void deleteGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().delete(script);
    }
}
