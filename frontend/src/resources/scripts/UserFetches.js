export const userFetches = {
    registerFetch: function (request) {
        return fetch('http://localhost:9080/Lab4/users',
            {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(request)
            }
        )
    },
    loginFetch: function (request) {
        return fetch('http://localhost:9080/Lab4/users/login',
            {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(request)
            }
        )
    }
}
