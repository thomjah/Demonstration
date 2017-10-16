'use strict';

// Namespace
var BookList = BookList || {};

BookList.AppName = "BookListApp";


(function() {

	var appModule = angular.module(BookList.AppName, []);
	
	/**
	* Run part to put a safeApply method on the scope.
	*/
	appModule.run(['$rootScope', function safeApplyRunner($rootScope) {
		if (!$rootScope.safeApply) {
			$rootScope.safeApply = function() {
				var phase = this.$$phase;
				if (phase) {
					this.$eval.apply(this, arguments);
				} else {
					this.$apply.apply(this, arguments);
				}
			};
		}
	}]);

}) ();
