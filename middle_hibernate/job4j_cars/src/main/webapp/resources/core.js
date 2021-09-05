load()

function load() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car/models/carallactive',
        dataType: 'json'
    }).done(function (data) {
        console.log(data)
        let html = '<tbody id="dataTable">'
        for(let item of data)
        {
            console.log(item)
            html += '<tr>'
                html += '<td>'
                html += '<img src="http://localhost:8080/car/models/downloadphoto?photo=' + item.photo +'" width="135px" height="100px"/>'
                html += '</td>' 
                html += '<td>'
                html += item.create
                html += '</td>'
                html += '<td>'
                html += item.mark.name
                html += '</td>'
                html += '<td>'
                html += item.body.type
                html += '</td>'
                html += '<td>'
                html += item.description
                html += '</td>'
                html += '<td>'
                html += item.price
                html += '</td>'
                html += '<td>'
                html += '<p>' + item.user.name + '</p>' + '<p>' + item.user.phone + '</p>' 
                html += '</td>' 
            html += '</tr>'   
        }
        html += '</tbody>'
        $('#dataTable').replaceWith(html)
        console.log('#dataTable')
    }).fail(function (err) {
        console.log(err)
    })
}