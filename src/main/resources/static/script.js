let x , y;
x = 80;
y = 100- x;
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ['Maturity '+ x + '%', ''],
        datasets: [{
            data: [x, y],
            backgroundColor: [
                'rgb(234,7,7)',
                'rgb(255,255,255)'
            ],
            borderColor: [
                'rgb(234,7,7)',
                'rgb(255,255,255)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        cutoutPercentage: 75
    }
});