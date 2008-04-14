/*
 * Copyright (C) 2005-2008 Alfresco Software Limited.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * 
 * As a special exception to the terms and conditions of version 2.0 of the GPL,
 * you may redistribute this Program in connection with Free/Libre and Open
 * Source Software ("FLOSS") applications as described in Alfresco's FLOSS
 * exception. You should have recieved a copy of the text describing the FLOSS
 * exception, and it is also available here:
 * http://www.alfresco.com/legal/licensing"
 */
package org.alfresco.web.site.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.web.site.Framework;
import org.alfresco.web.site.ModelUtil;
import org.alfresco.web.site.RequestContext;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author muzquiano
 */
public class Page extends ModelObject
{
    public static String TYPE_NAME = "page";
    public static String PROP_TEMPLATE_ID = "template-id";
    public static String ATTR_FORMAT_ID = "format-id";
    public static String PROP_ROOT_PAGE = "root-page";
    
    public Page(Document document)
    {
        super(document);
    }

    @Override
    public String toString()
    {
        return "Page Instance: " + getId() + ", " + toXML();
    }

    public String getTemplateId()
    {
        return getTemplateId(null);
    }

    public String getTemplateId(String formatId)
    {
        Element templateElement = getTemplateElement(formatId);
        if (templateElement != null)
            return templateElement.getStringValue();
        return null;
    }

    protected Element getTemplateElement(String formatId)
    {
        if(formatId != null && formatId.equals(Framework.getConfig().getDefaultFormatId()))
        {
            formatId = null;
        }
        
        List templateElements = getDocument().getRootElement().elements(
                PROP_TEMPLATE_ID);
        for (int i = 0; i < templateElements.size(); i++)
        {
            Element templateElement = (Element) templateElements.get(i);
            String _formatId = templateElement.attributeValue(ATTR_FORMAT_ID);
            if ("".equals(_formatId))
                _formatId = null;
            if (formatId == null)
            {
                if (_formatId == null || "".equals(_formatId))
                {
                    return templateElement;
                }
            }
            else
            {
                if (formatId.equals(_formatId))
                    return templateElement;
            }
        }
        return null;
    }

    public void setTemplateId(String templateId)
    {
        setTemplateId(templateId, null);
    }

    public void setTemplateId(String templateId, String formatId)
    {
        if(formatId != null && formatId.equals(Framework.getConfig().getDefaultFormatId()))
        {
            formatId = null;
        }
        
        Element templateElement = getTemplateElement(formatId);
        if (templateElement == null)
        {
            templateElement = getDocument().getRootElement().addElement(
                    PROP_TEMPLATE_ID);
            if (formatId != null)
                templateElement.addAttribute(ATTR_FORMAT_ID, formatId);
        }
        templateElement.setText(templateId);
    }

    public void removeTemplateId(String formatId)
    {
        if(formatId != null && formatId.equals(Framework.getConfig().getDefaultFormatId()))
        {
            formatId = null;
        }
        
        Element templateElement = this.getTemplateElement(formatId);
        if (templateElement != null)
            templateElement.getParent().remove(templateElement);
    }

    public Map<String, Template> getTemplates(RequestContext context)
    {
        Map map = new HashMap();

        List templateElements = getDocument().getRootElement().elements(
                PROP_TEMPLATE_ID);
        for (int i = 0; i < templateElements.size(); i++)
        {
            Element templateElement = (Element) templateElements.get(i);
            String formatId = templateElement.attributeValue(ATTR_FORMAT_ID);
            if (formatId == null || "".equals(formatId))
                formatId = context.getConfig().getDefaultFormatId();

            String templateId = templateElement.getStringValue();
            if (templateId != null)
            {
                Template template = (Template) context.getModel().loadTemplate(
                        context, templateId);
                map.put(formatId, template);
            }
        }

        return map;
    }

    public boolean getRootPage()
    {
        return getBooleanProperty(PROP_ROOT_PAGE);
    }

    public void setRootPage(boolean b)
    {
        this.setProperty(PROP_ROOT_PAGE, (b ? "true" : "false"));
    }

    public Template getTemplate(RequestContext context)
    {
        return getTemplate(context, null);
    }

    public Template getTemplate(RequestContext context, String formatId)
    {
        String templateId = getTemplateId(formatId);
        if (templateId != null)
            return context.getModel().loadTemplate(context, templateId);
        return null;
    }

    public Page[] getChildPages(RequestContext context)
    {
        PageAssociation[] associations = ModelUtil.findPageAssociations(
                context, this.getId(), null, PageAssociation.CHILD_ASSOCIATION_TYPE_ID);
        Page[] pages = new Page[associations.length];
        for (int i = 0; i < associations.length; i++)
        {
            Page childPage = (Page) associations[i].getDestPage(context);
            pages[i] = childPage;
        }
        return pages;
    }
    
    public String getTypeName() 
    {
        return TYPE_NAME;
    }
    
}
