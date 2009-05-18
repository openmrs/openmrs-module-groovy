/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.groovy.db.hibernate.impl;

import org.openmrs.module.groovy.db.hibernate.GroovyModuleDAO;
import org.openmrs.module.groovy.GroovyScript;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
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
        return script;
    }

    @Override
    public void deleteGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().delete(script);
    }
}
