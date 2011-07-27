/**
 * Copyright (C) 2005-2010 Alfresco Software Limited.
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
 
/**
 * Folder actions component.
 * 
 * @namespace Alfresco
 * @class Alfresco.FolderActions
 */
(function()
{
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom;

   /**
    * Alfresco Slingshot aliases
    */
   var $html = Alfresco.util.encodeHTML,
      $combine = Alfresco.util.combinePaths,
      $siteURL = Alfresco.util.siteURL;
   
   /**
    * FolderActions constructor.
    * 
    * @param {String} htmlId The HTML id of the parent element
    * @return {Alfresco.FolderActions} The new FolderActions instance
    * @constructor
    */
   Alfresco.FolderActions = function(htmlId)
   {
      Alfresco.FolderActions.superclass.constructor.call(this, "Alfresco.FolderActions", htmlId, ["button"]);
      
      /* Decoupled event listeners */
      YAHOO.Bubbling.on("filesPermissionsUpdated", this.doRefresh, this);
      
      return this;
   };
   
   /**
    * Extend Alfresco.component.Base
    */
   YAHOO.extend(Alfresco.FolderActions, Alfresco.component.Base);
   
   /**
    * Augment prototype with Actions module
    */
   YAHOO.lang.augmentProto(Alfresco.FolderActions, Alfresco.doclib.Actions);

   /**
    * Augment prototype with main class implementation, ensuring overwrite is enabled
    */
   YAHOO.lang.augmentObject(Alfresco.FolderActions.prototype,
   {
      /**
       * Object container for initialization options
       *
       * @property options
       * @type object
       */
      options:
      {
         /**
          * Reference to the current folder
          *
          * @property nodeRef
          * @type string
          */
         nodeRef: null,

         /**
          * Current siteId.
          * 
          * @property siteId
          * @type string
          */
         siteId: null,
         
         /**
          * ContainerId representing root container
          *
          * @property containerId
          * @type string
          * @default "documentLibrary"
          */
         containerId: "documentLibrary",

         /**
          * Replication URL Mapping details
          *
          * @property replicationUrlMapping
          * @type object
          */
         replicationUrlMapping: {},

         /**
          * JSON representation of folder details
          *
          * @property folderDetails
          * @type object
          */
         folderDetails: null,

         /**
          * Whether the Repo Browser is in use or not
          *
          * @property repositoryBrowsing
          * @type boolean
          */
         repositoryBrowsing: true
      },
      
      /**
       * The data for the folder
       * 
       * @property recordData
       * @type object
       */
      recordData: null,

      /**
       * Metadata returned by doclist data webscript
       *
       * @property doclistMetadata
       * @type object
       * @default null
       */
      doclistMetadata: null,

      /**
       * Path of asset being viewed - used to scope some actions (e.g. copy to, move to)
       * 
       * @property currentPath
       * @type string
       */
      currentPath: null,

      /**
       * Event handler called when "onReady"
       *
       * @method: onReady
       */
      onReady: function FolderActions_onReady()
      {
         var me = this;
         
         // Asset data
         this.recordData = this.options.folderDetails.item;
         this.doclistMetadata = this.options.folderDetails.metadata;
         this.currentPath = this.recordData.location.path;
         
         // Populate convenience property
         this.recordData.jsNode = new Alfresco.util.Node(this.recordData.node);
         
         var actionTypeMarkup =
         {
            "link": '<div class="{id}"><a title="{label}" class="simple-link" href="{href}" {target}><span>{label}</span></a></div>',
            "pagelink": '<div class="{id}"><a title="{label}" class="simple-link" href="{pageUrl}"><span>{label}</span></a></div>',
            "javascript": '<div class="{id}" title="{jsfunction}"><a title="{label}" class="action-link" href="#"><span>{label}</span></a></div>'
         };
         
         var fnRenderAction = function DA_renderAction(p_action, p_record)
         {
            // Store quick look-up for client-side actions
            p_record.actionParams[p_action.id] = p_action.params;
            
            var markupParams =
            {
               "id": p_action.id,
               "label": me.msg(p_action.label)
            };
            
            // Parameter substitution for each action type
            if (p_action.type === "link")
            {
               if (p_action.params.href)
               {
                  markupParams.href = YAHOO.lang.substitute(p_action.params.href, p_record, function DL_renderAction_href(p_key, p_value, p_meta)
                  {
                     return Alfresco.util.findValueByDotNotation(p_record, p_key);
                  });
                  markupParams.target = p_action.params.target ? "target=\"" + p_action.params.target + "\"" : "";
               }
               else
               {
                  Alfresco.logger.warn("Action configuration error: Missing 'href' parameter for actionId: ", p_action.id);
               }
            }
            else if (p_action.type === "pagelink")
            {
               if (p_action.params.page)
               {
                  markupParams.pageUrl = YAHOO.lang.substitute(p_action.params.page, p_record, function DL_renderAction_pageUrl(p_key, p_value, p_meta)
                  {
                     return Alfresco.util.findValueByDotNotation(p_record, p_key);
                  });
               }
               else
               {
                  Alfresco.logger.warn("Action configuration error: Missing 'page' parameter for actionId: ", p_action.id);
               }
            }
            else if (p_action.type === "javascript")
            {
               if (p_action.params["function"])
               {
                  markupParams.jsfunction = p_action.params["function"];
               }
               else
               {
                  Alfresco.logger.warn("Action configuration error: Missing 'function' parameter for actionId: ", p_action.id);
               }
            }

            return YAHOO.lang.substitute(actionTypeMarkup[p_action.type], markupParams);
         };

         // Retrieve the actionSet for this record
         var record = this.recordData,
            node = record.node,
            actions = record.actions,
            actionsEl = Dom.get(this.id + "-actionSet"),
            actionHTML = "",
            actionsSel;

         record.actionParams = {};
         for (var i = 0, ii = actions.length; i < ii; i++)
         {
            actionHTML += fnRenderAction(actions[i], record);
         }

         // Token replacement
         var actionUrls = this.getActionUrls(record);
         actionsEl.innerHTML = YAHOO.lang.substitute(actionHTML, actionUrls);

         Dom.addClass(actionsEl, "action-set");
         Dom.setStyle(actionsEl, "visibility", "visible");
         
         // Hook action events
         var fnActionHandler = function FolderActions_fnActionHandler(layer, args)
         {
            var owner = YAHOO.Bubbling.getOwnerByTagName(args[1].anchor, "div");
            if (owner !== null)
            {
               var action = owner.className;
               if (typeof me[action] == "function")
               {
                  args[1].stop = true;
                  me[action].call(me, me.recordData, owner);
               }
            }
            return true;
         };
         
         YAHOO.Bubbling.addDefaultAction("action-link", fnActionHandler);
         
         // DocLib Actions module
         this.modules.actions = new Alfresco.module.DoclibActions();
      },

      /**
       * Delete Asset confirmed.
       *
       * @override
       * @method _onActionDeleteConfirm
       * @param asset {object} Object literal representing file or folder to be actioned
       * @private
       */
      _onActionDeleteConfirm: function FolderActions__onActionDeleteConfirm(asset)
      {
         var path = asset.location.path,
            displayName = asset.displayName,
            nodeRef = new Alfresco.util.NodeRef(asset.nodeRef),
            callbackUrl = Alfresco.util.isValueSet(this.options.siteId) ? "documentlibrary" : "repository",
            encodedPath = path.length > 1 ? "?path=" + encodeURIComponent(path) : "";
         
         this.modules.actions.genericAction(
         {
            success:
            {
               callback:
               {
                  fn: function FolderActions_oADC_success(data)
                  {
                     window.location = $siteURL(callbackUrl + encodedPath);
                  }
               }
            },
            failure:
            {
               message: this.msg("message.delete.failure", displayName)
            },
            webscript:
            {
               method: Alfresco.util.Ajax.DELETE,
               name: "file/node/{nodeRef}",
               params:
               {
                  nodeRef: nodeRef.uri
               }
            }
         });
      },

      /**
       * Refresh component in response to metadataRefresh event
       *
       * @method doRefresh
       */
      doRefresh: function FolderActions_doRefresh()
      {
         YAHOO.Bubbling.unsubscribe("filesPermissionsUpdated", this.doRefresh);
         this.refresh('components/folder-details/folder-actions?nodeRef={nodeRef}' + (this.options.siteId ? '&site={siteId}' : ''));
      }
   }, true);
})();
