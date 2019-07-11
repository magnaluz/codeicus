'use strict';

angular.module('crudApp').controller('TaskController',
    ['TaskService', '$scope',  function( TaskService, $scope) {

        var self = this;
        self.task = {};
        self.tasks=[];

        self.submit = submit;
        self.getAllTasks = getAllTasks;
        self.createTask = createTask;
        self.updateTask = updateTask;
        self.removeTask = removeTask;
        self.editTask = editTask;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        // crear
        function submit() {
            if (self.task.id === undefined || self.task.id === null) {
                createTask(self.task);
            } else {
                updateTask(self.task, self.task.id);
            }
        }

        function createTask(task) {
            TaskService.createTask(task)
                .then(
                    function (response) {
                        self.successMessage = 'Tarea creada exitosamente';
                        self.errorMessage='';
                        self.done = true;
                        self.task={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        self.errorMessage = 'Error al crear la tarea' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateTask(task, id){
            TaskService.updateTask(task, id)
                .then(
                    function (response){
                        self.successMessage='Tarea actualizada exitosamente';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        self.errorMessage='Error al actualizar la tarea '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeTask(id){
            TaskService.removeTask(id)
                .then(
                    function(){
                    	self.successMessage='Tarea eliminada exitoosamente';
                        self.errorMessage='';
                    },
                    function(errResponse){
                    	self.successMessage='';
                        self.errorMessage='Error al eliminar la tarea seleccionada' + errResponse.data.errorMessage;
                    }
                );
        }


        function getAllTasks(){
            return TaskService.getAllTasks();
        }

        function editTask(id) {
            self.successMessage='';
            self.errorMessage='';
            TaskService.getTask(id).then(
                function (task) {
                    self.task = task;
                },
                function (errResponse) {
                    // error, se escribe mas arriba
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.task={};
            $scope.myForm.$setPristine(); //reset All
        }
    }


    ]);