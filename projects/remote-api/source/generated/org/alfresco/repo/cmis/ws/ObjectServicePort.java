package org.alfresco.repo.cmis.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.1.2
 * Wed Jan 13 20:13:28 GMT 2010
 * Generated source version: 2.1.2
 * 
 */
 
@WebService(targetNamespace = "http://docs.oasis-open.org/ns/cmis/ws/200908/", name = "ObjectServicePort")
@XmlSeeAlso({ObjectFactory.class})
public interface ObjectServicePort {

    @WebResult(name = "object", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "getObject", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetObject")
    @ResponseWrapper(localName = "getObjectResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetObjectResponse")
    @WebMethod
    public org.alfresco.repo.cmis.ws.CmisObjectType getObject(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String objectId,
        @WebParam(name = "filter", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String filter,
        @WebParam(name = "includeAllowableActions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean includeAllowableActions,
        @WebParam(name = "includeRelationships", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.EnumIncludeRelationships includeRelationships,
        @WebParam(name = "renditionFilter", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String renditionFilter,
        @WebParam(name = "includePolicyIds", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean includePolicyIds,
        @WebParam(name = "includeACL", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean includeACL,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @WebResult(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "getProperties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetProperties")
    @ResponseWrapper(localName = "getPropertiesResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetPropertiesResponse")
    @WebMethod
    public org.alfresco.repo.cmis.ws.CmisPropertiesType getProperties(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String objectId,
        @WebParam(name = "filter", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String filter,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @WebResult(name = "contentStream", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "getContentStream", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetContentStream")
    @ResponseWrapper(localName = "getContentStreamResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetContentStreamResponse")
    @WebMethod
    public org.alfresco.repo.cmis.ws.CmisContentStreamType getContentStream(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String objectId,
        @WebParam(name = "streamId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String streamId,
        @WebParam(name = "offset", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.math.BigInteger offset,
        @WebParam(name = "length", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.math.BigInteger length,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @WebResult(name = "allowableActions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "getAllowableActions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetAllowableActions")
    @ResponseWrapper(localName = "getAllowableActionsResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetAllowableActionsResponse")
    @WebMethod
    public org.alfresco.repo.cmis.ws.CmisAllowableActionsType getAllowableActions(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String objectId,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @RequestWrapper(localName = "deleteContentStream", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.DeleteContentStream")
    @ResponseWrapper(localName = "deleteContentStreamResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.DeleteContentStreamResponse")
    @WebMethod
    public void deleteContentStream(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "changeToken", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> changeToken,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension
    ) throws CmisException;

    @RequestWrapper(localName = "deleteObject", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.DeleteObject")
    @ResponseWrapper(localName = "deleteObjectResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.DeleteObjectResponse")
    @WebMethod
    public void deleteObject(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String objectId,
        @WebParam(name = "allVersions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean allVersions,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension
    ) throws CmisException;

    @RequestWrapper(localName = "createRelationship", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateRelationship")
    @ResponseWrapper(localName = "createRelationshipResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateRelationshipResponse")
    @WebMethod
    public void createRelationship(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisPropertiesType properties,
        @WebParam(name = "policies", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.util.List<java.lang.String> policies,
        @WebParam(name = "addACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType addACEs,
        @WebParam(name = "removeACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType removeACEs,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension,
        @WebParam(mode = WebParam.Mode.OUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId
    ) throws CmisException;

    @RequestWrapper(localName = "createDocumentFromSource", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateDocumentFromSource")
    @ResponseWrapper(localName = "createDocumentFromSourceResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateDocumentFromSourceResponse")
    @WebMethod
    public void createDocumentFromSource(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "sourceId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String sourceId,
        @WebParam(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisPropertiesType properties,
        @WebParam(name = "folderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String folderId,
        @WebParam(name = "versioningState", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.EnumVersioningState versioningState,
        @WebParam(name = "policies", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.util.List<java.lang.String> policies,
        @WebParam(name = "addACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType addACEs,
        @WebParam(name = "removeACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType removeACEs,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension,
        @WebParam(mode = WebParam.Mode.OUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId
    ) throws CmisException;

    @RequestWrapper(localName = "updateProperties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.UpdateProperties")
    @ResponseWrapper(localName = "updatePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.UpdatePropertiesResponse")
    @WebMethod
    public void updateProperties(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "changeToken", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> changeToken,
        @WebParam(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisPropertiesType properties,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension
    ) throws CmisException;

    @RequestWrapper(localName = "moveObject", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.MoveObject")
    @ResponseWrapper(localName = "moveObjectResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.MoveObjectResponse")
    @WebMethod
    public void moveObject(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId,
        @WebParam(name = "targetFolderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String targetFolderId,
        @WebParam(name = "sourceFolderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String sourceFolderId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension
    ) throws CmisException;

    @RequestWrapper(localName = "createDocument", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateDocument")
    @ResponseWrapper(localName = "createDocumentResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateDocumentResponse")
    @WebMethod
    public void createDocument(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisPropertiesType properties,
        @WebParam(name = "folderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String folderId,
        @WebParam(name = "contentStream", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisContentStreamType contentStream,
        @WebParam(name = "versioningState", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.EnumVersioningState versioningState,
        @WebParam(name = "policies", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.util.List<java.lang.String> policies,
        @WebParam(name = "addACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType addACEs,
        @WebParam(name = "removeACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType removeACEs,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension,
        @WebParam(mode = WebParam.Mode.OUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId
    ) throws CmisException;

    @WebResult(name = "failedToDelete", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "deleteTree", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.DeleteTree")
    @ResponseWrapper(localName = "deleteTreeResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.DeleteTreeResponse")
    @WebMethod
    public org.alfresco.repo.cmis.ws.DeleteTreeResponse.FailedToDelete deleteTree(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "folderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String folderId,
        @WebParam(name = "allVersions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean allVersions,
        @WebParam(name = "unfileObjects", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.EnumUnfileObject unfileObjects,
        @WebParam(name = "continueOnFailure", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean continueOnFailure,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @WebResult(name = "renditions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "getRenditions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetRenditions")
    @ResponseWrapper(localName = "getRenditionsResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetRenditionsResponse")
    @WebMethod
    public java.util.List<org.alfresco.repo.cmis.ws.CmisRenditionType> getRenditions(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String objectId,
        @WebParam(name = "renditionFilter", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String renditionFilter,
        @WebParam(name = "maxItems", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.math.BigInteger maxItems,
        @WebParam(name = "skipCount", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.math.BigInteger skipCount,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @WebResult(name = "object", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
    @RequestWrapper(localName = "getObjectByPath", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetObjectByPath")
    @ResponseWrapper(localName = "getObjectByPathResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.GetObjectByPathResponse")
    @WebMethod
    public org.alfresco.repo.cmis.ws.CmisObjectType getObjectByPath(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "path", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String path,
        @WebParam(name = "filter", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String filter,
        @WebParam(name = "includeAllowableActions", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean includeAllowableActions,
        @WebParam(name = "includeRelationships", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.EnumIncludeRelationships includeRelationships,
        @WebParam(name = "renditionFilter", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String renditionFilter,
        @WebParam(name = "includePolicyIds", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean includePolicyIds,
        @WebParam(name = "includeACL", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean includeACL,
        @WebParam(name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisExtensionType extension
    ) throws CmisException;

    @RequestWrapper(localName = "createFolder", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateFolder")
    @ResponseWrapper(localName = "createFolderResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreateFolderResponse")
    @WebMethod
    public void createFolder(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisPropertiesType properties,
        @WebParam(name = "folderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String folderId,
        @WebParam(name = "policies", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.util.List<java.lang.String> policies,
        @WebParam(name = "addACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType addACEs,
        @WebParam(name = "removeACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType removeACEs,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension,
        @WebParam(mode = WebParam.Mode.OUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId
    ) throws CmisException;

    @RequestWrapper(localName = "createPolicy", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreatePolicy")
    @ResponseWrapper(localName = "createPolicyResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.CreatePolicyResponse")
    @WebMethod
    public void createPolicy(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(name = "properties", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisPropertiesType properties,
        @WebParam(name = "folderId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String folderId,
        @WebParam(name = "policies", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.util.List<java.lang.String> policies,
        @WebParam(name = "addACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType addACEs,
        @WebParam(name = "removeACEs", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisAccessControlListType removeACEs,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension,
        @WebParam(mode = WebParam.Mode.OUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId
    ) throws CmisException;

    @RequestWrapper(localName = "setContentStream", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.SetContentStream")
    @ResponseWrapper(localName = "setContentStreamResponse", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/", className = "org.alfresco.repo.cmis.ws.SetContentStreamResponse")
    @WebMethod
    public void setContentStream(
        @WebParam(name = "repositoryId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.String repositoryId,
        @WebParam(mode = WebParam.Mode.INOUT, name = "objectId", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> objectId,
        @WebParam(name = "overwriteFlag", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        java.lang.Boolean overwriteFlag,
        @WebParam(mode = WebParam.Mode.INOUT, name = "changeToken", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<java.lang.String> changeToken,
        @WebParam(name = "contentStream", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        org.alfresco.repo.cmis.ws.CmisContentStreamType contentStream,
        @WebParam(mode = WebParam.Mode.INOUT, name = "extension", targetNamespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
        javax.xml.ws.Holder<CmisExtensionType> extension
    ) throws CmisException;
}
