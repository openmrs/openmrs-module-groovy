/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
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