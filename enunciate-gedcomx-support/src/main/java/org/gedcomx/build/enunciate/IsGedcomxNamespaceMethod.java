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
package org.gedcomx.build.enunciate;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.codehaus.enunciate.config.SchemaInfo;
import org.codehaus.enunciate.contract.jaxb.Accessor;
import org.codehaus.enunciate.contract.jaxb.RootElementDeclaration;
import org.codehaus.enunciate.contract.jaxb.TypeDefinition;
import org.codehaus.enunciate.contract.jaxb.types.XmlType;
import org.gedcomx.build.enunciate.rs.ResourceServiceDefinitionDeclaration;
import org.gedcomx.rt.CommonModels;

import java.util.List;

/**
 * @author Ryan Heaton
 */
public class IsGedcomxNamespaceMethod implements TemplateMethodModelEx {

  @Override
  public Object exec(List list) throws TemplateModelException {
    if (list.size() < 1) {
      throw new TemplateModelException("The IsGedcomxNamespace method must have a schema, rsd, type def, or root element as a parameter.");
    }

    Object object = BeansWrapper.getDefaultInstance().unwrap((TemplateModel) list.get(0));
    String ns;
    if (object instanceof ResourceServiceDefinitionDeclaration) {
      ns = ((ResourceServiceDefinitionDeclaration) object).getNamespace();
    }
    else if (object instanceof SchemaInfo) {
      ns = ((SchemaInfo) object).getNamespace();
    }
    else if (object instanceof TypeDefinition) {
      ns = ((TypeDefinition) object).getNamespace();
    }
    else if (object instanceof RootElementDeclaration) {
      ns = ((RootElementDeclaration) object).getNamespace();
    }
    else if (object instanceof XmlType) {
      ns = ((XmlType) object).getNamespace();
    }
    else if (object instanceof Accessor) {
      ns = ((Accessor) object).getNamespace();
    }
    else {
      ns = String.valueOf(object);
    }

    return isGedcomxNamepace(ns);
  }

  public static boolean isGedcomxNamepace(String ns) {
    return CommonModels.DUBLIN_CORE_NAMESPACE.equals(ns)
      || CommonModels.DUBLIN_CORE_TYPE_NAMESPACE.equals(ns)
      || CommonModels.CONTACT_NAMESPACE.equals(ns)
      || CommonModels.FOAF_NAMESPACE.equals(ns)
      || CommonModels.RDF_NAMESPACE.equals(ns)
      || ns.startsWith(CommonModels.GEDCOMX_DOMAIN);
  }
}