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

package de.d3adspace.minerva;

import de.d3adspace.minerva.converter.EntityConverter;
import org.json.JSONObject;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class SimpleMinerva implements Minerva {
	
	private final EntityConverter converter;
	
	SimpleMinerva(EntityConverter converter) {
		this.converter = converter;
	}
	
	@Override
	public <EntityType> EntityType toEntity(JSONObject jsonObject, Class<EntityType> entityClazz) {
		return (EntityType) this.converter.toEntity(jsonObject, entityClazz);
	}
	
	@Override
	public <EntityType> JSONObject fromEntity(EntityType entity) {
		return this.converter.fromEntity(entity);
	}
}
