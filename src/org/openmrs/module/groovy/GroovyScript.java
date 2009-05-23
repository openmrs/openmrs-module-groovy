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
package org.openmrs.module.groovy;

import java.util.Date;

import org.openmrs.User;

public class GroovyScript {
    Integer id;
    String name;
    String script;
    Date created;
    Date modified;
    User creator;
    User modifiedBy;

    public GroovyScript() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(final Date modified) {
        this.modified = modified;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(final User creator) {
        this.creator = creator;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        int hash = 8;
        hash = 31 * this.getId() + hash;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof GroovyScript) {
            GroovyScript s = (GroovyScript) obj;
            return (this.getId().equals(s.getId()));
        }
        return false;
    }

    @Override
    public String toString() {
        return "GroovyScript{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", script='" + script + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", creator=" + creator +
                ", modifiedBy=" + modifiedBy +
                '}';
    }
}