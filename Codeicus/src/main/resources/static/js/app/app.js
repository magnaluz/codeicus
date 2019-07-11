var app = angular.module('crudApp', [ 'ui.router', 'ngStorage' ]);

app.constant('urls', {
	BASE : 'http://localhost:8080/SpringBootCRUDApp',
	TASK_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/task'
});

app.config([
		'$stateProvider',
		'$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$stateProvider.state('home', {
				url : '/',
				templateUrl : 'partials/task',
				controller:'TaskController',
				controllerAs:'ctrl',
				resolve : {
					tasks : function($q, TaskService) {
						var deferred = $q.defer();
						TaskService.loadAllTasks().then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
			});

			$urlRouterProvider.otherwise('/');
		} ]);
