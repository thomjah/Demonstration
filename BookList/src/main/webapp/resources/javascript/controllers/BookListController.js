/**
 * Controller for the filter input and result list.
 */
angular.module(BookList.AppName)
	.controller("BookListController", ["$scope", "$q", "$log", "BookListLoaderService", function ($scope, $q, $log, BookListLoaderService) {
		
		var self = this;
		
		$scope.filterVal = "";
		
		$scope.filterBtnClicked = function () {
			$log.log("Filter Button clicked with value " + $scope.filterVal);
			self.populateBookList();
			$scope.safeApply();
		};
		
		this.populateBookList = function () {
			var promise = BookListLoaderService.getBookList($scope.filterVal);
			promise.then(
				function successCallback(data) {
					$log.log("Setting bookList variable to array of " + data.length + " books");
					$scope.bookList = data;
				});
		};
		
		// Start call
		(function () {
			self.populateBookList();
		}) ();
	}]);
