const graph = document.getElementById('graph').getContext('2d');

let myChart = new Chart(graph, {
    type: "bar",
    data: {
        labels: [
            "van",
            "bus",
    ],
    database: [
        {
            label:"Typedes voitures dispo",
            data: [5, 9],
        }
    ]
    }
})