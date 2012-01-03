/*
 * Copyright (C) 2005-2011 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.module.org_alfresco_module_dod5015.patch;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.module.org_alfresco_module_dod5015.notification.RecordsManagementNotificationHelper;
import org.alfresco.repo.module.AbstractModuleComponent;
import org.alfresco.repo.version.VersionModel;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionService;
import org.alfresco.service.cmr.version.VersionType;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author Roy Wetherall
 */
public class NotificationTemplatePatch extends AbstractModuleComponent 
                                       implements BeanNameAware
{
    /** Last patch update property */
    private static final QName PROP_LAST_PATCH_UPDATE = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "lastPatchUpdate");
    
    private static final String PATH_DUE_FOR_REVIEW = "alfresco/module/org_alfresco_module_dod5015/bootstrap/content/notify-records-due-for-review-email.ftl";
    private static final String PATH_SUPERSEDED = "alfresco/module/org_alfresco_module_dod5015/bootstrap/content/record-superseded-email.ftl";
    
    /** Logger */
    private static Log logger = LogFactory.getLog(NotificationTemplatePatch.class);
    
    /** Records management notification helper */
    private RecordsManagementNotificationHelper notificationHelper;
    
    /** Node service */
    private NodeService nodeService;
    
    /** Content service */
    private ContentService contentService;
    
    /** Version service */
    private VersionService versionService;
    
    /** Bean name */
    private String name;
    
    /**
     * @param notificationHelper    notification helper
     */
    public void setNotificationHelper(RecordsManagementNotificationHelper notificationHelper)
    {
        this.notificationHelper = notificationHelper;
    }
    
    /**
     * @param nodeService   node service
     */
    public void setNodeService(NodeService nodeService)
    {
        this.nodeService = nodeService;
    }
    
    /**
     * @param contentService content service
     */
    public void setContentService(ContentService contentService)
    {
        this.contentService = contentService;
    }
    
    /**
     * @param versionService    version service
     */
    public void setVersionService(VersionService versionService)
    {
        this.versionService = versionService;
    }
    
    /**
     * @see org.alfresco.repo.module.AbstractModuleComponent#setBeanName(java.lang.String)
     */
    @Override
    public void setBeanName(String name)
    {
        this.name = name;
    }
    
    /**
     * @see org.alfresco.repo.module.AbstractModuleComponent#executeInternal()
     */
    @Override
    protected void executeInternal() throws Throwable
    {
        if (logger.isDebugEnabled() == true)
        {
            logger.debug("RM Module NotificationTemplatePatch ...");
        }
        
        NodeRef supersededTemplate = notificationHelper.getSupersededTemplate();
        updateTemplate(supersededTemplate, PATH_SUPERSEDED);
        
        NodeRef dueForReviewTemplate = notificationHelper.getDueForReviewTemplate();
        updateTemplate(dueForReviewTemplate, PATH_DUE_FOR_REVIEW);
    }
    
    /**
     * Attempt to update the template with the updated version
     * 
     * @param template
     * @param updatedTemplate
     */
    private void updateTemplate(NodeRef template, String templateUpdate)
    {
        if (template == null || nodeService.exists(template) == false)
        {
            if (logger.isDebugEnabled() == true)
            {
                logger.debug("Skipping template update, because template has not been bootstraped.");
            }
        }
        else
        {
            // Check to see if this template has already been updated
            String lastPatchUpdate = (String)nodeService.getProperty(template, PROP_LAST_PATCH_UPDATE);
            if (lastPatchUpdate == null || name.equals(lastPatchUpdate) == false)
            {
                if (logger.isDebugEnabled() == true)
                {
                    logger.debug("Applying update to template. (template=" + template.toString() + ", templateUpdate=" + templateUpdate + ")");
                }
                     
                // Make sure the template is versionable
                if (nodeService.hasAspect(template, ContentModel.ASPECT_VERSIONABLE) == false)
                {
                    nodeService.addAspect(template, ContentModel.ASPECT_VERSIONABLE, null);
                    
                    // Create version (before template is updated)
                    Map<String, Serializable> versionProperties = new HashMap<String, Serializable>(2);
                    versionProperties.put(Version.PROP_DESCRIPTION, "Initial version");
                    versionProperties.put(VersionModel.PROP_VERSION_TYPE, VersionType.MINOR);
                    versionService.createVersion(template, versionProperties);
                }               
                
                // Update the content of the template
                InputStream is = getClass().getClassLoader().getResourceAsStream(templateUpdate);
                ContentWriter writer = contentService.getWriter(template, ContentModel.PROP_CONTENT, true);
                writer.putContent(is);
                
                // Set the last patch update property
                nodeService.setProperty(template, PROP_LAST_PATCH_UPDATE, name);
                
                // Create version (once template has been updated)
                Map<String, Serializable> versionProperties = new HashMap<String, Serializable>(2);
                versionProperties.put(Version.PROP_DESCRIPTION, "System template update");
                versionProperties.put(VersionModel.PROP_VERSION_TYPE, VersionType.MINOR);
                versionService.createVersion(template, versionProperties);
            }
            else
            {
                if (logger.isDebugEnabled() == true)
                {
                    logger.debug("Skipping template update, because template has already been patched. (template=" + template.toString() + ")");
                }
            }
        }
    }    
}
