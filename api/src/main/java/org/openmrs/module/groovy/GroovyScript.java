/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy;

import org.openmrs.User;

import java.util.Date;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroovyScript that = (GroovyScript) o;

        if (!created.equals(that.created)) return false;
        if (!creator.equals(that.creator)) return false;
        if (!id.equals(that.id)) return false;
        if (!modified.equals(that.modified)) return false;
        if (!modifiedBy.equals(that.modifiedBy)) return false;
        if (!name.equals(that.name)) return false;
        if (!script.equals(that.script)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 7;
        if (name == null) {
        	result = 31 * result;
        } else {
        	result = 31 * result + name.hashCode();
        }
        if (script == null) {
        	result = 31 * result;
        } else {
        	result = 31 * result + script.hashCode();
        }
        if (created == null) {
        	result = 31 * result;
        } else {
        	result = 31 * result + created.hashCode();
        }
        if (modified == null) {
        	result = 31 * result;
        } else {
        	result = 31 * result + modified.hashCode();
        }
        if (creator == null) {
        	result = 31 * result;
        } else {
        	result = 31 * result + creator.hashCode();
        }
        if (modifiedBy == null) {
        	result = 31 * result;
        } else {
        	result = 31 * result + modifiedBy.hashCode();
        }
        return result;
    }
}