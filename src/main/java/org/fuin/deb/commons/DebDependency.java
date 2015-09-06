/**
 * Copyright (C) 2015 Michael Schnell. All rights reserved. 
 * http://www.fuin.org/
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see http://www.gnu.org/licenses/.
 */
package org.fuin.deb.commons;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.fuin.objects4j.common.Contract;

/**
 * Dependency to a package.
 */
@XmlRootElement(name = "dependency")
public final class DebDependency {

    @XmlElement(name = "name")
    private String name;

    private transient DebPackage resolvedDependency;

    /**
     * Default constructor for JAXB.
     */
    protected DebDependency() {
        super();
    }

    /**
     * Constructor with mandatory data.
     * 
     * @param name
     *            Unique name of the dependency.
     */
    public DebDependency(@NotNull final String name) {
        super();
        Contract.requireArgNotNull("name", name);
        this.name = name;
    }

    /**
     * Returns the name of the referenced package.
     * 
     * @return Referenced package name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the package that corresponds to the name if it already was
     * resolved.
     * 
     * @return Referenced package.
     */
    public final DebPackage getResolvedDependency() {
        return resolvedDependency;
    }

    /**
     * Tries to resolve the package the is referenced by the name. After this
     * method was called the {@link #resolvedDependency} is set if a module with
     * that name was found.
     * 
     * @param resolver
     *            Contains all known packages.
     * 
     * @return TRUE if the package could be resolved.
     */
    public final boolean resolve(final DebPackageResolver resolver) {
        resolvedDependency = resolver.resolve(name);
        return resolvedDependency != null;
    }

    @Override
    public final String toString() {
        return "DebDependency [name=" + name + "]";
    }

}
