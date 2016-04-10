app.factory('CarMakeResource', function ($resource) {
    return $resource('/citation/car/manufacturer');
});
app.controller('FilterCtrl', function (NgMap, $resource, $scope, CarMakeResource) {
    $scope.selection = {
        ids: {"50d5ad": true}
    };

    var results = CarMakeResource.query(function () {
        console.log('FilterCtrl')
        console.log(results)
        var cars = new Array();
        for (var i=0; i<results.length; i++) {
            cars.push({"name": results[i], "id": results[i]});
        }
        $scope.cars = cars;
    });
});