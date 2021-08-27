load(false)

function validate() {
    if($('#description').val() == '') {
        alert($('#description').attr('title'));
        return false
    }
    return true 
}

function getCheck(check) {
    if(check == true) {
        return 'checked'
    }
    return ''
}

function chengeAll() {
    let all = allItems.checked
    console.log(all)
    load(all)
}

function load(all) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/todo/index?all=' + all,
            dataType: 'json'
        }).done(function (data) {
            console.log(data)
            let html = '<tbody id="dataTable">'
            for(let item of data)
            {
                html += '<tr>'
                    html += '<td>'
                    html += '<input type="checkbox" id=' + item.id + ' ' + getCheck(item.done) +' onclick="update('+ item.id + ', checked)">'
                    html += '</td>' 
                    html += '<td>'
                    html += item.createdTime
                    html += '</td>'
                    html += '<td>'
                    html += item.description
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

function addItem() {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/todo/index',
        data: JSON.stringify({
            id: 0,
            description: $('#description').val(),
            createdTime: null,
            done: false
        })
    }).done(
        chengeAll()
    ).fail(function (err) {
        console.log(err);
    });
}

function send() {
    console.log('SEND')
    if(validate()) {
        console.log('SEND - true')
        addItem()
    }
}

function update(i, c) {
    console.log(i)
    console.log(c)
    $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/todo/index',
    data: JSON.stringify({
    id: i,
    description: null,
    createdTime: null,
    done: c
})
}).done(
).fail(function (err) {
console.log(err);
});
}