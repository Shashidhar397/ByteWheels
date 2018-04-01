'use strict';

angular
    .module('byteWheelsApp.routes', ['ui.router'])
    .config(config).run(['$rootScope', '$state', '$stateParams',
                         function($rootScope, $state, $stateParams) {
    	
    	
        
        	
      }
    ]);

function config($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/login');
	$stateProvider
	.state('login', {
		
        url: '/login',
        views:{
        'mainView@':{
        		templateUrl: 'views/login/login.html',
        		controller: 'LogInController'
            }
        }
    })
	
	
}