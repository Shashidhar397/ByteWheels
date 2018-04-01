angular
		.module('byteWheelsApp.controllers')
		.controller(
				'LogInController',['$scope','$http','$state','$mdDialog', function($scope,$http,$state,$mdDialog) {
					
					$scope.data = {};
					$scope.categories = [
						"Compact",
						"Large",
						"Luxury",
						"Full"
					]
					//$scope.data.rentingDateFrom = "2018-04-10";
					//$scope.data.rentingDateTo = "2018-04-11";
					$scope.getVehiclesData = function(){
						$scope.data.rentingDateFrom = moment($scope.dateFrom).format("YYYY-MM-DD");
						$scope.data.rentingDateTo = moment($scope.dateTo).format("YYYY-MM-DD");
						$http({
							  method: 'POST',
							  data: $scope.data,
							  url: '/ByteWheels/listVehicles.do'
								  
							}).then(function successCallback(response) {
							    //$scope.getVehicleResponse = response.data;
							    if(response.data.returnCode == 0){
							    	$scope.getVehicleResponse = response.data;
							    } else{
							    	alert(response.data.returnMessage);
							    	$scope.error(response.data.returnMessage);
							    }
							    
							  }, function errorCallback(response) {
								  var errorMessage;
								  var errorMessagePieces;
								  //parses error message to only show title which contains the real exception
								  errorMessagePieces = (response.data).split("</title>");
								  errorMessage = errorMessagePieces[0];
								  errorMessagePieces = errorMessage.split("<title>");
								  errorMessage = errorMessagePieces[1];
								  errorMessage = errorMessage.slice(errorMessage.lastIndexOf(":")+1,(errorMessage.length)-1);
								  $scope.loginError = errorMessage;
								  $scope.hideLoginError = false;
							  });
					}
					
					$scope.callGetVehicleDetails = 0;
					
					
					$scope.categoryChange = function(){
						//alert($scope.category);
						$scope.data.category = $scope.category;
						$scope.getVehiclesData();
					}
					
					$scope.clearValue = function() {
					    $scope.category = undefined;
					    $scope.data.category = "";
					    $scope.getVehiclesData();
					  };
					
					$scope.showAdvanced = function(ev, car) {
						car.fromDate = $scope.data.rentingDateFrom;
						car.toDate = $scope.data.rentingDateTo;
					    $mdDialog.show({
					      locals:{car: car},
					      controller: DialogController,
					      templateUrl: 'views/login/dialog.tmpl.html',
					      parent: angular.element(document.body),
					      targetEvent: ev,
					      clickOutsideToClose:true
					    })
					    .then(function(answer) {
					    	$scope.getVehiclesData();
					      $scope.status = 'You said the information was "' + answer + '".';
					    }, function() {
					      $scope.status = 'You cancelled the dialog.';
					    });
					  };

					  function DialogController($scope, $mdDialog, car) {
						  //console.log($scope.callGetVehicleDetails)
						$scope.car = car;
					    $scope.hide = function() {
					      $mdDialog.hide();
					    };

					    $scope.cancel = function() {
					      $mdDialog.cancel();
					    };

					    $scope.answer = function(answer) {
					    	var bookCar = {};
					    	bookCar.carName = $scope.car.carName;
					    	bookCar.fromDate = $scope.car.fromDate;
					    	bookCar.toDate = $scope.car.toDate;
					    	bookCar.userEmailId = $scope.emailId;
					    	$http({
								  method: 'POST',
								  data: bookCar,
								  url: '/ByteWheels/bookVehicle.do'
									  
								}).then(function successCallback(response) {
								    //$scope.bookingResponse = response.data;
								    if(response.data.returnCode == 0){
								    	$scope.bookingResponse = response.data;
								    	$scope.success(response.data);
								    } else{
								    	//alert(response.data.returnMessage);
								    	$scope.error(response.data.returnMessage);
								    }
								    $mdDialog.hide();
								  }, function errorCallback(response) {
									  var errorMessage;
									  var errorMessagePieces;
									  //parses error message to only show title which contains the real exception
									  errorMessagePieces = (response.data).split("</title>");
									  errorMessage = errorMessagePieces[0];
									  errorMessagePieces = errorMessage.split("<title>");
									  errorMessage = errorMessagePieces[1];
									  errorMessage = errorMessage.slice(errorMessage.lastIndexOf(":")+1,(errorMessage.length)-1);
									  $scope.loginError = errorMessage;
									  $scope.hideLoginError = false;
								  });
					    };
					    
					    $scope.error = function(message) {
							  alert(""+message)
								 $mdDialog.show({
							      locals:{message: message},
							      controller: BookingErrorController,
							      templateUrl: 'views/login/error.tmpl.html',
							      parent: angular.element(document.body),
							      clickOutsideToClose:true
							    })
							    .then(function(answer) {
							    	$scope.status = 'You said the information was "' + answer + '".';
							    }, function() {
							      $scope.status = 'You cancelled the dialog.';
							    });
							  };
							  function BookingErrorController($scope, $mdDialog, message) {
									$scope.message = message;
									$scope.hide = function() {
										$mdDialog.hide();
									};

									$scope.cancel = function() {
										$mdDialog.cancel();
									};

									$scope.answer = function(answer) {
										$mdDialog.hide();
									};
								}
							  
							  $scope.success = function(invoice) {
									 $mdDialog.show({
								      locals:{invoice: invoice},
								      controller: BookingSucessController,
								      templateUrl: 'views/login/sucess.tmpl.html',
								      parent: angular.element(document.body),
								      clickOutsideToClose:true
								    })
								    .then(function(answer) {
								    	$scope.status = 'You said the information was "' + answer + '".';
								    }, function() {
								      $scope.status = 'You cancelled the dialog.';
								    });
								  };
								  function BookingSucessController($scope, $mdDialog, invoice) {
										$scope.invoice = invoice;
										$scope.hide = function() {
											$mdDialog.hide();
										};

										$scope.cancel = function() {
											$mdDialog.cancel();
										};

										$scope.answer = function(answer) {
											$mdDialog.hide();
										};
									}
					  }
					  
					  $scope.error = function(message) {
						  alert(""+message)
							 $mdDialog.show({
						      locals:{message: message},
						      controller: ErrorController,
						      templateUrl: 'views/login/error.tmpl.html',
						      parent: angular.element(document.body),
						      clickOutsideToClose:true
						    })
						    .then(function(answer) {
						    	$scope.status = 'You said the information was "' + answer + '".';
						    }, function() {
						      $scope.status = 'You cancelled the dialog.';
						    });
						  };
						  function ErrorController($scope, $mdDialog, message) {
								$scope.message = message;
								$scope.hide = function() {
									$mdDialog.hide();
								};

								$scope.cancel = function() {
									$mdDialog.cancel();
								};

								$scope.answer = function(answer) {
									$mdDialog.hide();
								};
							}
						  
						  $scope.sucess = function(invoice) {
								 $mdDialog.show({
							      locals:{invoice: invoice},
							      controller: SucessController,
							      templateUrl: 'views/login/sucess.tmpl.html',
							      parent: angular.element(document.body),
							      clickOutsideToClose:true
							    })
							    .then(function(answer) {
							    	$scope.status = 'You said the information was "' + answer + '".';
							    }, function() {
							      $scope.status = 'You cancelled the dialog.';
							    });
							  };
							  function SucessController($scope, $mdDialog, invoice) {
									$scope.invoice = invoice;
									$scope.hide = function() {
										$mdDialog.hide();
									};

									$scope.cancel = function() {
										$mdDialog.cancel();
									};

									$scope.answer = function(answer) {
										$mdDialog.hide();
									};
								}

					
				}		
			]);