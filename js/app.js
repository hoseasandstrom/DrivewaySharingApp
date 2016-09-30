let app = angular.module('DrivewaySharingApp', ['ngRoute']);

//controllers
require('./controllers/loginController.js')(app);
require('./controllers/mapController.js')(app);
require('./controllers/newSpotController.js')(app);

//services
require('./services/loginService.js')(app);
require('./services/mapServices.js')(app);
require('./services/newSpotService.js')(app);

app.config(['$routeProvider', function($routeProvider){
  $routeProvider
    .when('/', {
    redirectTo: '/home',
    })
    .when('/register', {
      controller: 'loginController',
      templateUrl: 'templates/register.html',
    })
    .when('/login', {
      controller: 'loginController',
      templateUrl: 'templates/logIn.html',
    })
    .when('/home',{
      controller: 'mapController',
      templateUrl: 'templates/map.html'
    })
    .when('/addspot',{
      controller: 'newSpotController',
      templateUrl:'templates/addSpot.html'
    })
}])