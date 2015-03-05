/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
import org.openmrs.api.context.Context
import org.openmrs.api.EncounterService;
import org.openmrs.module.groovy.GroovyUtil
import org.openmrs.module.groovy.api.GroovyModuleService
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
//import org.openmrs.EncounterRole;
//import org.openmrs.Provider;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Obs;
import org.openmrs.Concept;

import org.hibernate.SessionFactory;

def sf = Context.serviceContext.applicationContext.getBean(SessionFactory.class)
def sql = "select p.patient_id from patient p " +
		"inner join obs o1 on p.patient_id = o1.person_id and o1.concept_id = 6110 and o1.value_coded = 6220 " +
		"left join obs o2 on p.patient_id = o2.person_id and o2.concept_id = 6330 and o2.value_datetime is not null " +
		"where PERIOD_DIFF(EXTRACT(YEAR_MONTH FROM NOW()), EXTRACT(YEAR_MONTH FROM o2.obs_datetime)) > 12 "

def query = sf.currentSession.createSQLQuery(sql)
EncounterService es = Context.getEncounterService();
def c = new Concept(1)
def provider = new Person(1)
def eType = new EncounterType(1)
query.list().each {pid ->
	def patient = new Patient(pid)
	def today = Calendar.instance.getTime()

	def e = new Encounter()
	e.patient = patient
	e.encounterDatetime = today
	e.dateCreated = today
	e.encounterType = eType
	//e.setProvider(new EncounterRole(1), new Provider(1));
	e.setProvider(provider)

	def obs = new Obs()
	obs.concept = c
	obs.encounter = e
	obs.valueDatetime = today
	obs.dateCreated = today

	e.addObs obs

	es.saveEncounter(e);
}
​