/*******************************************************************************
 * Copyright (c) 2011, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.datastore.internal.model.query;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.kapua.service.datastore.model.query.ExistsPredicate;

/**
 * Implementation of query predicate for checking if a field exists
 * 
 * @since 1.0
 *
 */
public class ExistsPredicateImpl implements ExistsPredicate {

    private final static String PREDICATE_KEY = "exists";
    private final static String FIELD_KEY = "field";

    private String name;

    /**
     * Creates an exists predicate for the given field name
     * 
     * @param name
     */
    public ExistsPredicateImpl(String name) {
        this.name = name;
    }

    /**
     * Creates an exists predicate concatenating the given fields name with a dot (useful for composite fileds)
     * 
     * @param paths
     */
    public ExistsPredicateImpl(String... paths) {
        StringBuilder builder = new StringBuilder();
        for (String str : paths) {
            builder.append(str);
            builder.append('.');
        }
        name = builder.substring(0, builder.length() - 1);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    /**
     * <pre>
     * GET /_search
     *  {
     *      "query": {
     *          "exists" : { "field" : "user" }
     *      }
     *   }
     * </pre>
     */
    public Map<String, Object> toSerializedMap() {
        Map<String, Object> outputMap = new HashMap<>();
        Map<String, Object> termMap = new HashMap<>();
        termMap.put(FIELD_KEY, name);
        outputMap.put(PREDICATE_KEY, termMap);
        return outputMap;
    }

}
