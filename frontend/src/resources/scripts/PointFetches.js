export const pointFetches = {
    postPointFetch: function (request) {
        return fetch('http://localhost:9080/Lab4/points',
            {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(request)
            }
        )
    },
    getUserPointsFetch: function (request) {
        return fetch('http://localhost:9080/Lab4/users/login',
            {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "GET",
                body: JSON.stringify(request)
            }
        )
    }
}