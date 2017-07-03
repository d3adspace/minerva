/*
 * Copyright (c) 2017 D3adspace
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.d3adspace.minerva.converter;

import de.d3adspace.minerva.metadata.EntityMeta;
import de.d3adspace.minerva.metadata.EntityMetaContainer;
import de.d3adspace.minerva.metadata.EntityMetaFactory;
import de.d3adspace.minerva.property.EntityProperty;
import org.json.JSONObject;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class SimpleEntityConverter<EntityType> implements EntityConverter<EntityType> {
	
	private final EntityMetaContainer metaContainer;
	
	SimpleEntityConverter(EntityMetaContainer metaContainer) {
		this.metaContainer = metaContainer;
	}
	
	public EntityType toEntity(JSONObject jsonObject, Class<EntityType> entityClazz) {
		EntityMeta entityMeta = this.getEntityMeta(entityClazz);
		
		try {
			EntityType entity = entityClazz.newInstance();
			
			for (EntityProperty property : entityMeta.getEntityProperties()) {
				String fieldName = property.getFieldName();
				
				if (jsonObject.has(fieldName)) {
					property.set(jsonObject.get(fieldName), entity);
				}
			}
			
			return entity;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject fromEntity(EntityType entity) {
		EntityMeta entityMeta = this.getEntityMeta(entity.getClass());
		
		JSONObject jsonObject = new JSONObject();
		
		for (EntityProperty property : entityMeta.getEntityProperties()) {
			String fieldName = property.getFieldName();
			Object value = property.get(entity);
			
			if (value == null || value instanceof String || value instanceof Boolean
				|| value instanceof Integer || value instanceof Long || value instanceof Double) {
				jsonObject.put(fieldName, value);
			} else {
				throw new IllegalStateException(
					"We dont support fields of type: " + value.getClass());
			}
		}
		
		return jsonObject;
	}
	
	private EntityMeta getEntityMeta(Class<?> entityClazz) {
		EntityMeta entityMeta = this.metaContainer.getEntityMeta(entityClazz);
		
		if (entityMeta != null) {
			return entityMeta;
		}
		
		entityMeta = EntityMetaFactory.createEntityMeta(entityClazz);
		this.metaContainer.addEntityMeta(entityClazz, entityMeta);
		return entityMeta;
	}
}
