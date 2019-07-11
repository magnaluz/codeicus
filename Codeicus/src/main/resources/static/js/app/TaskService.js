'use strict';

angular.module('crudApp').factory('TaskService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllTasks: loadAllTasks,
                getAllTasks: getAllTasks,
                getTask: getTask,
                createTask: createTask,
                updateTask: updateTask,
                removeTask: removeTask
            };

            return factory;

            function loadAllTasks() {
                var deferred = $q.defer();
                $http.get(urls.TASK_SERVICE_API + "/get-all")
                    .then(
                        function (response) {
                            $localStorage.tasks = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllTasks(){
                return $localStorage.tasks;
            }

            function getTask(id) {
                var deferred = $q.defer();
                $http.get(urls.TASK_SERVICE_API + "/get/" + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createTask(task) {
                var deferred = $q.defer();
                $http.post(urls.TASK_SERVICE_API + "/create", task)
                    .then(
                        function (response) {
                            loadAllTasks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateTask(task, id) {
                var deferred = $q.defer();
                $http.put(urls.TASK_SERVICE_API + "/update/" + id, task)
                    .then(
                        function (response) {
                            loadAllTasks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeTask(id) {
                var deferred = $q.defer();
                $http.delete(urls.TASK_SERVICE_API + "/delete/" + id)
                    .then(
                        function (response) {
                            loadAllTasks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);