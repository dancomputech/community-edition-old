/**
 * Copyright (C) 2005-2013 Alfresco Software Limited.
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
 * @module alfresco/core/CoreXhr
 * @author Dave Draper
 */
define(["dojo/_base/declare",
        "service/constants/Default",
        "dijit/registry",
        "dojo/topic",
        "dojo/_base/array",
        "dojo/_base/lang",
        "dojo/dom-construct",
        "dojox/uuid/generateRandomUuid",
        "dojo/request/xhr",
        "dojo/json",
        "dojo/date/stamp",
        "dojo/cookie"], 
        function(declare, AlfConstants, registry, pubSub, array, lang, domConstruct, uuid, xhr, JSON, stamp, dojoCookie) {
   
   return declare(null, {
      
      /**
       * Ensures that the csrfProperties are retrieved from the Alfresco constants provided by Surf.
       * 
       * @instance
       * @param {object} args The constructor arguments.
       */
      constructor: function(args){
         lang.mixin(this, args);
         this.csrfProperties = AlfConstants.CSRF_POLICY.properties || {};
         this.serviceRequests = {};
      },

      /**
       * This function can be used to clean up JSON responses to remove any superfluous whitespace characters and
       * remove any trailing commas in arrays/objects. This function is particularly handy since Dojo can be very
       * fussy about JSON.
       * 
       * @instance
       * @param {string} input
       * @returns {string} A cleaned up JSON response.
       */
      cleanupJSONResponse: function alfresco_core_CoreXhr__cleanupJSONResponse(input) {
         var r = input;
         if (typeof input == "string")
         {
            r = input.replace(/,}/g, "}");
         }
         return r;
      },
      
      /**
       * This method handles XHR requests. As well as providing default callback handlers for the success, failure and
       * progress responses it also performs some additional JSON cleanup of responses (where required) which is useful
       * when REST APIs return invalid code (this is especially useful as Dojo can be quite particular about parsing
       * JSON).  
       * 
       * The function takes a single object as an argument that will allow updates to be made to include additional
       * data and provide defaults when it is not provided.
       * 
       * By default this function will issue a POST method
       * 
       * @instance
       * @param {Object} config The configuration for the request
       * @todo List the available config object attributes
       */
      serviceXhr: function alfresco_core_CoreXhr__serviceXhr(config) {
         
         var _this = this;
         
         if (config)
         {
            if (typeof config.url == "undefined")
            {
               this.alfLog("error", "An XHR request was made but no URL was provided", config);
            }
            else
            {
               var headers = (config.headers) ? config.headers : { 'Content-Type': 'application/json' };
               if (this.isCsrfFilterEnabled())
               {
                  headers[this.getCsrfHeader()] = this.getCsrfToken();
               }
               
               var request = xhr(config.url, {
                  handleAs: (config.handleAs) ? config.handleAs : "text",
                  method: (config.method) ? config.method : "POST",
                  data: (config.data) ? JSON.stringify(config.data) : null,
                  query: (config.query) ? config.query : null,
                  headers: headers
               }).then(function(response){
                  
                  var id = lang.getObject("requestId", false, config)
                  if (id != null)
                  {
                     delete _this.serviceRequests[id];
                  }

                  // HANDLE SUCCESS...
                  if (typeof response == "string")
                  {
                     try
                     {
                        response = JSON.parse(_this.cleanupJSONResponse(response));
                     }
                     catch (e)
                     {
                        _this.alfLog("error", "An error occurred parsing an XHR JSON success response", response, this);
                     }
                     
                  }
                  if (typeof config.successCallback == "function")
                  {
                     var callbackScope = (config.successCallbackScope ? config.successCallbackScope : (config.callbackScope ? config.callbackScope : _this)); 
                     config.successCallback.call(callbackScope, response, config)
                  }
                  else
                  {
                     _this.defaultSuccessCallback(response, config);
                  }
                  
               }, function(response){
                  
                  // HANDLE FAILURE...
                  var id = lang.getObject("requestId", false, config)
                  if (id != null)
                  {
                     delete _this.serviceRequests[id];
                  }
                  if (typeof response == "string")
                  {
                     try
                     {
                        response = JSON.parse(_this.cleanupJSONResponse(response));
                     }
                     catch (e)
                     {
                        _this.alfLog("error", "An error occurred parsing an XHR JSON failure response", response, this);
                     }
                  }
                  if (typeof config.failureCallback == "function")
                  {
                     var callbackScope = (config.failureCallbackScope ? config.failureCallbackScope : (config.callbackScope ? config.callbackScope : _this)); 
                     config.failureCallback.call(callbackScope, response, config)
                  }
                  else
                  {
                     _this.defaultFailureCallback(response, config);
                  }

               }, function(response){
                  
                  // HANDLE PROGRESS...
                  
                  if (typeof response == "string")
                  {
                     try
                     {
                        response = JSON.parse(_this.cleanupJSONResponse(response));
                     }
                     catch (e)
                     {
                        _this.alfLog("error", "An error occurred parsing an XHR JSON progress response", response, this);
                     }
                  }
                  if (typeof config.progressCallback == "function")
                  {
                     var callbackScope = (config.progressCallbackScope ? config.progressCallbackScope : (config.callbackScope ? config.callbackScope : _this)); 
                     config.progressCallback.call(callbackScope, response, config);
                  }
                  else
                  {
                     _this.defaultProgressCallback(response, config);
                  }
               });

               // If a request ID has been provided then store it in a map so that we can kill the
               // request when asked. This entry should be cleaned up on success or failure request
               // responses to prevent memory leakage...
               if (config.requestId)
               {
                  this.serviceRequests[config.requestId] = request;
               }
               return request;
            }
         }
         else
         {
            this.alfLog("error", "A request was made to perform an XHR request, but no configuration for the request was provided");
         }
      },
      
      /**
       * This is the default success callback for XHR requests that will be used if no other is provided.
       * 
       * @instance
       * @param {object} response The object returned from the successful XHR request
       * @param {object} requestConfig The original configuration passed when the request was made
       */
      defaultSuccessCallback: function alfresco_core_CoreXhr__defaultSuccessCallback(response, requestConfig) {
         this.alfLog("log", "[DEFAULT CALLBACK] The following successful response was received", response, requestConfig);

         if (requestConfig.alfTopic)
         {
            this.alfPublish(requestConfig.alfTopic + "_SUCCESS", {
               requestConfig: requestConfig,
               response: response
            });
         }
      },
      
      /**
       * This is the default failure callback for XHR requests that will be used if no other is provided.
       * 
       * @instance
       * @param {object} response The object returned from the failed XHR request
       * @param {object} requestConfig The original configuration passed when the request was made
       */
      defaultFailureCallback: function alfresco_core_CoreXhr__defaultFailureCallback(response, requestConfig) {
         this.alfLog("log", "[DEFAULT CALLBACK] The following failure response was received", response, requestConfig);
         if (requestConfig.alfTopic)
         {
            this.alfPublish(requestConfig.alfTopic + "_FAILURE", {
               requestConfig: requestConfig,
               response: response
            });
         }
         if (typeof this.displayMessage === "function" && response.response.text)
         {
            try
            {
               var responseObj = JSON.parse(response.response.text);
               if (responseObj.message)
               {
                  var msg = responseObj.message;
                  // generic exception message (from standard error template for REST APIs)
                  // attempt to strip out message text if in standard format:
                  // "org.alfresco.package.SpecificException: 12345678 Rest Of Message"
                  // - give up and display all if not
                  var eIndex = msg.indexOf("Exception: ");
                  if (eIndex !== -1 && msg.length > eIndex + 20)
                  {
                     msg = msg.substring(eIndex + 20);
                  }
                  this.displayMessage(msg);
               }
            }
            catch (e)
            {
               // Ignore failures here. The parsing was a best effort to get a message.
            }
         }
      },
      
      /**
       * This is the default progress callback for XHR requests that will be used if no other is provided.
       * 
       * @instance
       * @param {object} response The object returned from the progress update of the XHR request
       * @param {object} requestConfig The original configuration passed when the request was made
       */
      defaultProgressCallback: function alfresco_core_CoreXhr__defaultProgressCallback(response, requestConfig) {
         this.alfLog("log", "[DEFAULT CALLBACK] The following progress response was received", response, requestConfig);
         if (requestConfig.alfTopic)
         {
            this.alfPublish(requestConfig.alfTopic + "_PROGRESS", {
               requestConfig: requestConfig,
               response: response
            });
         }
      },

      /**
       * Handles requests to stop a previous XHR request.
       *
       * @instance
       * @param {object} payload An object that should contain a 'requestId' attribute
       */
      onStopSearchRequest: function alfresco_core_CoreXhr__onStopSearchRequest(payload) {
         var id = lang.getObject("requestId", false, payload)
         if (id != null && this.serviceRequests[id])
         {
            this.alfLog("info", "Stopping XHR request: " + id);
            this.serviceRequests[id].cancel();
            delete this.serviceRequests[id];
         }
      }

      /**
       * Use this method and check if the CSRF filter is enabled before trying to set the CSRF header or parameter.
       * Will be disabled if the filter contains no rules.
       *
       * @instance
       * @return {*}
       */
      isCsrfFilterEnabled: function alfresco_core_CoreXhr__isCsrfFilterEnabled()
      {
         return AlfConstants.CSRF_POLICY.enabled;
      },

      /**
       * Returns the name of the request header to put the token in when sending XMLHttpRequests.
       *
       * @instance
       * @return {String} The name of the request header to put the token in.
       */
      getCsrfHeader: function alfresco_core_CoreXhr__getCsrfHeader()
      {
         return this.csrfResolve(AlfConstants.CSRF_POLICY.header);
      },

      /**
       * Returns the name of the request parameter to put the token in when sending multipart form uploads.
       *
       * @instance
       * @return {String} The name of the request header to put the token in.
       */
      getCsrfParameter: function alfresco_core_CoreXhr__getCsrfParameter()
      {
         return this.csrfResolve(AlfConstants.CSRF_POLICY.parameter);
      },

      /**
       * Returns the name of the cookie that holds the value of the token.
       *
       * @instance
       * @return {String} The name of the request header to put the token in.
       */
      getCsrfCookie: function alfresco_core_CoreXhr__getCsrfCookie()
      {
         return this.csrfResolve(AlfConstants.CSRF_POLICY.cookie);
      },

      /**
       * Returns the token.
       *
       * Note! Make sure to use this method just before a request is made against the server since it might have been
       * updated in another browser tab or window.
       *
       * @instance
       * @returns {String} The name of the request header to put the token in.
       */
      getCsrfToken: function alfresco_core_CoreXhr__getCsrfToken()
      {
         var token = null;
         var cookieName = this.getCsrfCookie();
         if (cookieName)
         {
            token = dojoCookie(cookieName);
            if (token)
            {
               // remove quotes to support Jetty app-server - bug where it quotes a valid cookie value see ALF-18823
               token = token.replace(/"/g, '');
            }
         }
         return token;
      },

      /**
       * @instance
       */
      csrfResolve: function alfresco_core_CoreXhr__csrfResolve(str) {
         return lang.replace(str, this.csrfProperties);
      }
   });
});