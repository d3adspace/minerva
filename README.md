# Minerva

Minerva is a simple and lightweight serialization framework for entities. For now it only
support primitive datatypes. We decided to use JSON as a storage format.

# Installation / Usage

- Install [Maven](http://maven.apache.org/download.cgi)
- Clone this repo
- Instal: ```mvn clean install```

**Maven dependencies**

_Client:_
```xml
<dependency>
    <groupId>de.d3adspace</groupId>
    <artifactId>minerva</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

# Example
_Model:_
```java

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

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MinervaUser {
	
	private String name;
	private int age;
	
	public MinervaUser(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public MinervaUser() {
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "MinervaUser{" +
			"name='" + name + '\'' +
			", age=" + age +
			'}';
	}
}
```

_Serialize:_
```java
Minerva minerva = MinervaFactory.createMinerva();
		
MinervaUser minervaUser = new MinervaUser("Minerva", 1);
		
JSONObject jsonObject = minerva.fromEntity(minervaUser);
```

_Result:_
```
{
    "name": "Minerva"
    "age": 1
}
```

_Deserialize:_
```java
MinervaUser minervaUser = minerva.toEntity(jsonObject, MinervaUser.class);
```

_Preload entity meta to reduce load at serilization:_
```java
minerva.map(MinervaUser.class);
```