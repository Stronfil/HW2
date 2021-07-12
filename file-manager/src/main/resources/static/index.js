angular.module('app', []).controller('fileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/file';
    $scope.fd = null;

    $scope.fillList = function () {
        $http({
            url: contextPath + '/getfiles',
            method: 'GET',
            params: {
                subtype: $scope.subtype ? $scope.subtype : null,
            }
        }).then(function (response) {
            $scope.FileList = response.data;
        });
    };

    $scope.upload = function () {
        $scope.fd.append("subtype", $scope.subtype);
        $http({
            url: contextPath + '/storefile',
            method: 'POST',
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity,
            data: $scope.fd,
        }).then(function (response) {
            console.log(response.data)
            $scope.fd = null;
            $scope.fillList();
        });
    }

    $scope.selectFile = function (files) {
        $scope.fd = new FormData();
        $scope.fd.append("file", files[0]);
    }

    $scope.fillList();
});