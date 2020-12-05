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
    getUserPointsFetch: function (username) {
        return fetch('http://localhost:9080/Lab4/points?username=1',
            {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "GET",
            }
        )
    }
}