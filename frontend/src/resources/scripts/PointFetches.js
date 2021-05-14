export const pointFetches = {
    postPointFetch: function (request, token) {
        return fetch('http://localhost:9080/Lab4/points',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+token
                },
                method: "POST",
                body: JSON.stringify(request)
            }
        )
    },
    getUserPointsFetch: function (token) {
        return fetch('http://localhost:9080/Lab4/points',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+token
                },
                method: "GET",
            }
        )
    },
    deletePoints: function (token) {
        return fetch('http://localhost:9080/Lab4/points',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+token
                },
                method: "DELETE",
            }
        )
    }
}