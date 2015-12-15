'use strict';

angular.module('oauthJhipsterApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


