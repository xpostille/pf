var global_url = location.host;
var app = angular.module("myApp", [ "ngRoute", "ngSanitize"]);

app.config(function($routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		$routeProvider.when("/", {
			controller : 'mainController',
			templateUrl : "pf-main.html",
			resolve : { }
			}).when("/dreamers", {
				controller : 'dreamersController',
				templateUrl : "pf-dreamers.html",
				resolve : { }
			}).when("/dreamers-editor/:id", {
				controller : 'dreamerseditController',
				templateUrl : "pf-dreamers-editor.html",
				resolve : { }
			}).when("/treasuremap", {
				controller : 'treasuremapController',
				templateUrl : "pf-treasuremap.html",
				resolve : { }
			}).when("/treasuremap-au", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-au.html",
				resolve : { }
			}).when("/treasuremap-ca", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-ca.html",
				resolve : { }
			}).when("/treasuremap-jp", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-jp.html",
				resolve : { }
			}).when("/treasuremap-us", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-us.html",
				resolve : { }
			}).when("/chitchatpub", {
				controller : 'chitchatpubController',
				templateUrl : "pf-chitchatpub.html",
				resolve : { }
			}).when("/tortugaisland", {
				controller : 'tortugaislandController',
				templateUrl : "pf-tortugaisland.html",
				resolve : { }
			}).when("/portroyal", {
				controller : 'portroyalController',
				templateUrl : "pf-portroyal.html",
				resolve : { }
			}).when("/atworldsend", {
				controller : 'atworldsendController',
				templateUrl : "pf-atworldsend.html",
				resolve : { }
			}).when("/signin", {
				controller : 'userController',
				templateUrl : "/user/signin.html",
				resolve : { }
			}).when("/signup", {
				controller : 'userController',
				templateUrl : "/user/signup.html",
				resolve : { }
			}).when("/mypage", {
				controller : 'mypageController',
				templateUrl : "/pf-mypage.html",
				resolve : { }
			});
		});
		
		app.service('navigationService', ['$http', function($http){
			this.init = function(){
			}
			this.user = function(){
				return $http.get('/user/user');
			}
		}])
		app.controller('navigationController', [ '$rootScope', '$scope', '$location','$http', '$window', 'navigationService',
			function($rootScope, $scope, $location, $http, $window, navigationService) {
			$scope.logout = function() {
				  $http.get('/user/logout', {})
				  .then(function() {
				    $rootScope.authenticated = false;
				    $location.path("/");
				  },function(data) {
					$location.path("/");
				    $rootScope.authenticated = false;
				  });
			}
			$(".collapsebtn").click(function(event) {
				if($("#navbar-toggle").css("display") != "none" ){
					$("#navbar-toggle").click(); 
				}
			});
			$scope.user = function(){
				navigationService.user()
				.then(function(response) {
			    	  $rootScope.currentuser = response.data;
			      if ($rootScope.currentuser.username) {
			    	  $rootScope.username = $rootScope.currentuser.username;
			    	  $rootScope.authenticated = true;
			      } else {
			    	  $rootScope.authenticated = false;
			      }
			    }, function() {
			    	//alert("계정을 확인해주세요");
			    	$rootScope.authenticated = false;
			    });
			}
			$scope.init = function(){
				console.log("navi init");
				
				if($window.innerWidth < 768){
					$rootScope.isMobile = true;
			    } else{
			    	$rootScope.isMobile = false;
			    }
				angular.element($window).bind('resize', function(){
					if($window.innerWidth < 768){
						$rootScope.isMobile = true;
				    } else{
				    	$rootScope.isMobile = false;
				    }
					console.log($rootScope.isMobile);
				});
				
				/*
				if (window.location.hash && window.location.hash == '#_=_') {
			        window.location.hash = '';
			    }
				$scope.user();
				*/
			}
		}]);
		
		app.service('mainService', ['$http', function($http){
			this.init = function(){
			}
			this.user = function(){
				return $http.get('/user/user');
			}
		}])
		app.controller('mainController', [ '$rootScope', '$scope', 'mainService',
			function($rootScope, $scope, mainService) {
			$scope.user = function(){
				mainService.user()
				.then(function(response) {
			    	  $rootScope.currentuser = response.data;
			      if ($rootScope.currentuser.username) {
			    	  $rootScope.username = $rootScope.currentuser.username;
			    	  $rootScope.authenticated = true;
			      } else {
			    	  $rootScope.authenticated = false;
			      }
			    }, function() {
			    	//alert("계정을 확인해주세요");
			    	$rootScope.authenticated = false;
			    });
			}
			$scope.init = function(){
				console.log("main init");
				if (window.location.hash && window.location.hash == '#_=_') {
			        window.location.hash = '';
			    }
				$scope.user();
				//지워야함
				//$location.path("/signin");
			}
			$scope.init2 = function(){
				$.ajaxSetup({
					beforeSend : function(xhr, settings) {
					  if (settings.type == 'POST' || settings.type == 'PUT'
					      || settings.type == 'DELETE') {
					    if (!(/^http:.*/.test(settings.url) || /^https:.*/
					        .test(settings.url))) {
					      // Only send the token to relative URLs i.e. locally.
					      xhr.setRequestHeader("X-XSRF-TOKEN",
					          Cookies.get('XSRF-TOKEN'));
					    }
					  }
					}
				});
			}
			
			
		}]);
		
		app.controller('mypageController', [ '$rootScope', '$scope',
			function($rootScope, $scope) {
			$scope.init = function(){

			}
		}]);
		
		app.controller('tortugaislandController', [ '$rootScope', '$scope',
			function($rootScope, $scope) {
			
		}]);
		app.controller('portroyalController', [ '$rootScope', '$scope', '$location',
			function($rootScope, $scope, $location) {
			
		}]);
		
		
		