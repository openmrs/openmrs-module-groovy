package org.openmrs.module.groovy;

import java.util.Date;

import org.openmrs.User;

public class GroovyScript {
    Integer id;
    String version;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
    public boolean equals(Object obj) {
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
                ", version=" + version +
                ", name='" + name + '\'' +
                ", script='" + script + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", creator=" + creator +
                ", modifiedBy=" + modifiedBy +
                '}';
    }
}