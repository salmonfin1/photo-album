angular.module('photoalbum')
  .controller('photoAlbum',

    function($scope, $http, $location, $route) {
      var self = this;
      $http.get('album-images').then(function(response) {
        $scope.imageUrl = response.data.encodedImage;
      });
    });
