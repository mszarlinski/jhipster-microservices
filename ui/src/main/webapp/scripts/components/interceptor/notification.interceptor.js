 'use strict';

angular.module('oauthJhipsterApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-oauthJhipsterApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-oauthJhipsterApp-params')});
                }
                return response;
            }
        };
    });
