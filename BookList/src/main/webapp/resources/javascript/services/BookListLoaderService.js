/**
 * Service to load and process the BookList.
 */
angular.module(BookList.AppName).
	service("BookListLoaderService", ["$http", "$q", "$log", function ($http, $q, $log) {
		
		var self = this;
		
		var BOOKLIST_ENDPOINT = "BookListServlet";
		var XML_NAMESPACE = "http://thomjah-demo.local/BookList";
		
		this.getBookListXml = function (filter) {
			return $http({
				method: "GET",
				url: BOOKLIST_ENDPOINT,
				params: {
					"FILTER": filter
				}
			});
		};
		
		this.getBookList = function (filter) {
			var deferred = $q.defer();
			var promise = self.getBookListXml(filter);
			promise.then(
				function successCallback(response) {
					var bookArray = self.parseBookListXml(response.data);
					$log.log("BookList callback returns " + bookArray.length + " books");
					deferred.resolve(bookArray);
				},
				function errorCallback(response) {
					$log.error("Error from BookListServlet", response);
					deferred.reject("Error");
				}
			);
			return deferred.promise;
		};
		
		/**
		 * Transforms XML BookList to JavaScript objects.
		 * @param {String|Object} xmlDoc String or XML Document.
		 * @returns {Array} Array of Objects with attributes 'author' and 'title'.
		 */
		this.parseBookListXml = function (xmlDoc) {
			if (angular.isString(xmlDoc)) {
				xmlDoc = BookList.Util.createXmlDoc(xmlDoc);
			}
			var resultList = [];
			
			var bookNodes = xmlDoc.getElementsByTagNameNS(XML_NAMESPACE, "Book");
			var numNodes = bookNodes.length;
			$log.log("XML contains " + numNodes + " Book elements");
			for (var i = 0; i < numNodes; i++) {
				var ithBookNode = bookNodes[i];
				var author = null;
				var title = null;
				var authorNodes = ithBookNode.getElementsByTagNameNS(XML_NAMESPACE, "Author");
				if (authorNodes && authorNodes.length > 0) {
					author = authorNodes[0].textContent;
				}
				var titleNodes = ithBookNode.getElementsByTagNameNS(XML_NAMESPACE, "Title");
				if (titleNodes && titleNodes.length > 0) {
					title = titleNodes[0].textContent;
				}
				if (author || title) {
					resultList.push({
						"author": author,
						"title": title
					});
				}
			}
			return resultList;
		};
}]);
