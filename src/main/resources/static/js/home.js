angular.module('photoalbum')
    .controller('home', function($rootScope, $scope, $filter, $http, FileUploader, $cookies) {
        if($rootScope.authenticated) {
            var uploader = $scope.uploader = new FileUploader({
                url: 'upload',
                headers: {
                    'X-XSRF-TOKEN' : $cookies.get('XSRF-TOKEN')
                }
            });

            uploader.onBeforeUploadItem = function(item) {
                var tags = $scope.tags[item.file.name] === undefined ? [] : $scope.tags[item.file.name];
                item.formData.push({
                    tags: JSON.stringify(tags)
                });
            };

            $scope.tags = {};

            $http.get('tags')
                .then(function(tags){
                    $scope.allTags = tags.data;
                });

            $scope.loadTags = function(query) {
                return $filter('searchTags')($scope.allTags, query);
            };

            $scope.tagAdded = function(tag) {
                var filtered = $filter('searchTags')($scope.allTags, tag.text);
                if(!filtered.length) {
                    $http.post('tags', tag)
                        .then(function(tag) {
                            $scope.allTags.push(tag.data);
                        });

                }
            };
        }
    });