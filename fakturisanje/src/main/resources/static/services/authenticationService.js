app.service('authenticationService', function ($http, $window) {

    var LOCAL_STORAGE_KEY = 'companyUser';
    var LOCAL_STORAGE_INSTANCE = $window.localStorage;

    return {
    	login: function (user) {
            $http.post('/login', user, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                }
            });
        },
        logout: function (successCallback, errorCallback) {
            var loggedInUser = this.getUser();
            if (loggedInUser) {
                $http.post("/api/logout", loggedInUser.id).success(successCallback).error(errorCallback);
            }
        },
        getUser: function () {
            if (LOCAL_STORAGE_INSTANCE) {
                var loggedInUser = LOCAL_STORAGE_INSTANCE.getItem(LOCAL_STORAGE_KEY);
                if (loggedInUser) {
                    return JSON.parse(loggedInUser);
                }
            }
        },
        setUser: function (user) {
            if (LOCAL_STORAGE_INSTANCE && user) {
                LOCAL_STORAGE_INSTANCE.setItem(LOCAL_STORAGE_KEY, JSON.stringify(user));
            }
        },
        isWorker: function () {
            return this.getUser().role === 'WORKER';
        },
        isAdmin: function () {
            return this.getUser().role === 'ADMIN';
        },
        isEmployee : function() {
        	return this.isWorker() || this.isAdmin();
        }
    }
});