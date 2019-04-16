/**
 * 
 */

var CSRF_ParamName,CSRF_token;


/**
 * Set global session vars
 * @param res
 * @param jqXHR
 * @returns
 */
function setGlobalSessionInfo(res, jqXHR){
	CSRF_ParamName = jqXHR.getResponseHeader('X-CSRF-PARAM');
	CSRF_token = jqXHR.getResponseHeader('X-CSRF-TOKEN');
}

/**
 * Refresh the current login date
 * @param callback
 * @returns
 */
function refreshLogginInfo(callback){
	 $.ajax({
         url: 'feature/refreshLogin',
         dataType: 'text',
         type: 'post',
         contentType: 'application/x-www-form-urlencoded',
         data: CSRF_ParamName+"="+CSRF_token,
         success: function( data, textStatus, jQxhr ){
             callback(JSON.parse(data));
         },
         error: function( jqXhr, textStatus, errorThrown ){
        	 console.log( errorThrown );
         }
     });
}

/**
 * API call testers, this not send the CSRF token because is a GET request
 * @param path
 * @returns
 */
function getAndAlertResult(path){
	$.ajax({
        url: path,
        type: 'get',
        success: function( data, textStatus, jQxhr ){
            alert('['+textStatus + '] \n \n '+ data);
        },
        error: function( jqXhr, textStatus, errorThrown ){
        	alert(textStatus + " "+ jqXhr.status+ " " +errorThrown);
        }
    });
	
	
}