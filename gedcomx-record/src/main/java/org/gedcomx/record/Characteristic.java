/**
 * Copyright 2011 Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.record;


import org.codehaus.enunciate.XmlQNameEnumUtil;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonTypeIdResolver;
import org.gedcomx.rt.XmlTypeIdResolver;
import org.gedcomx.types.CharacteristicType;
import org.gedcomx.types.Typed;
import org.gedcomx.types.TypesNamespaces;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * A field specifying a characteristic about a record, persona, or relationship.
 */
@JsonTypeInfo ( use =JsonTypeInfo.Id.CUSTOM, property = "@type")
@JsonTypeIdResolver (XmlTypeIdResolver.class)
@XmlType ( name = "Characteristic" )
public class Characteristic extends Field implements Typed, Temporal, Spatial {

  private URI type;
  private Date date;
  private Place place;

  /**
   * The type of the characteristic.
   *
   * @return The type of the characteristic.
   */
  @XmlAttribute (namespace = TypesNamespaces.GEDCOMX_TYPES_NAMESPACE)
  @XmlQNameEnumRef (CharacteristicType.class)
  @XmlSchemaType (name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI)
  public URI getType() {
    return type;
  }

  /**
   * The type of the characteristic.
   *
   * @param type The type of the characteristic.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known type of the characteristic, or {@link org.gedcomx.types.CharacteristicType#other} if not known.
   *
   * @return The enum referencing the known type of the characteristic, or {@link org.gedcomx.types.CharacteristicType#other} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public org.gedcomx.types.CharacteristicType getKnownType() {
    return XmlQNameEnumUtil.fromURI(getType(), org.gedcomx.types.CharacteristicType.class);
  }

  /**
   * Set the type of this characteristic from a known enumeration of types.
   *
   * @param knownType The known type.
   */
  @JsonIgnore
  public void setKnownType(org.gedcomx.types.CharacteristicType knownType) {
    setType(XmlQNameEnumUtil.toURI(knownType));
  }

  /**
   * The date of applicability of this characteristic.
   *
   * @return The date of applicability of this characteristic.
   */
  public Date getDate() {
    return date;
  }

  /**
   * The date of applicability of this characteristic.
   *
   * @param date The date of applicability of this characteristic.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * The place of applicability of this characteristic.
   *
   * @return The place of applicability of this characteristic.
   */
  public Place getPlace() {
    return place;
  }

  /**
   * The place of applicability of this characteristic.
   *
   * @param place The place of applicability of this characteristic.
   */
  public void setPlace(Place place) {
    this.place = place;
  }

}