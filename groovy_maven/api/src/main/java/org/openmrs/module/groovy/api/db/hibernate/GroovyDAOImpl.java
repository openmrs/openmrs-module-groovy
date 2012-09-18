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
package org.openmrs.module.groovy.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.api.db.GroovyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * It is a default implementation of  {@link GroovyDAO}.
 */
@Service
public class GroovyDAOImpl implements GroovyDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    @SuppressWarnings("unchecked")
	public List<GroovyScript> getAllScripts() {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(GroovyScript.class);
        crit.addOrder(Order.asc("name"));        
        return (List<GroovyScript>)crit.list();
    }

    
    public GroovyScript getScript(Integer id) {
        return (GroovyScript) sessionFactory.getCurrentSession().get(GroovyScript.class, id);
    }

    
    public GroovyScript saveGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().saveOrUpdate(script);
        return script;
    }

    
    public void deleteGroovyScript(GroovyScript script) {
        sessionFactory.getCurrentSession().delete(script);
    }


	@Override
	public GroovyScript getScript(String scriptName) {
		// TODO Auto-generated method stub
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(GroovyScript.class);
		crit.add(Restrictions.eq("name", scriptName));
		List<GroovyScript> list = crit.list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null; 
		
	}
}