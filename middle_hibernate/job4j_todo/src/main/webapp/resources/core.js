load(false)
logging()
loadCategory()

function loadCategory() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/category',
        dataType: 'json'
    }).done(function (data) {
        console.log(data)
        let html = '<select class="form-control" name="categ" id="categ" multiple>'
        for(let ctg of data)
        {
            console.log(ctg)
            html += '<option value="' + ctg.id + '">'
                html += ctg.name
            html += '</option>'   
        }
        html += '</select>'
        $('#categ').replaceWith(html)
        console.log('#categ')
    }).fail(function (err) {
        console.log(err)
    })
}

function validate() {
    if($('#description').val() == '') {
        alert($('#description').attr('title'));
        return false
    }
    if(validateSelects() == false)
    {
        alert('Выберите хотя бы одну категорию!')
        return false
    }
    return true
}

function validateSelects()
{
    var m = false;
    $.each($('select'),function()
    {
         if( $(this).val() != ''){  m=true; }
    });
    return(m);
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
    logging()
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
                console.log(item)
                html += '<tr>'
                    html += '<td>'
                    html += '<input type="checkbox" id=' + item.id + ' ' + getCheck(item.done) +' onclick="update('+ item.id + ', checked)">'
                    html += '</td>' 
                    html += '<td>'
                    html += item.createdTime
                    html += '</td>'
                    html += '<td>'
                    for(let c of item.categories) {
                        html += '<p>' + c.name + '</p>'
                    }
                    html += '</td>'
                    html += '<td>'
                    html += item.description
                    html += '</td>'
                    html += '<td>'
                    html += item.user.name
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
    done: c,
    user: null
})
}).done(
).fail(function (err) {
console.log(err);
});
}

function logging() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/user',
        dataType: 'json'
    }).done(function (data) {
        console.log(data)
        let html = '<b  id="logging">' + data.name + '</b>'
        $('#logging').replaceWith(html)
    }
    ).fail(function (err) {
    console.log(err);
    });
}