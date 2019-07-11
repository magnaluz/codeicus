<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Tareas </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.task.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nombre</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.task.name" id="uname" class="taskname form-control input-sm" placeholder="Nombre de la tarea" required ng-minlength="3" maxlength="63"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="salary">Prioridad</label>
	                        <div class="col-md-7">
	                        <select ng-model="ctrl.task.priority" id="priority" class="form-control input-sm">
								  <option value="Critico">Critico</option>
								  <option value="Alto">Alto</option>
								  <option value="Normal">Normal</option>
								  <option value="Bajo">Bajo</option>
								</select>
							</div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="estimate">Estimacion</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.task.estimate" id="estimate" class="form-control input-sm" placeholder="Estimacion en dias" required ng-pattern="ctrl.onlyNumbers" maxlength="4"/>	
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.task.id ? 'Crear' : 'Actualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reiniciar Formulario</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Tareas </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOMBRE</th>
		                <th>PRIORIDAD</th>
		                <th>ESTIMACION EN DIAS</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllTasks()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                <td>{{u.priority}}</td>
		                <td>{{u.estimate}}</td>
		                <td><button type="button" ng-click="ctrl.editTask(u.id)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.removeTask(u.id)" class="btn btn-danger custom-width">Eliminar</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>